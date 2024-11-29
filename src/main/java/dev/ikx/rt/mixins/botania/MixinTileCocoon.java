package dev.ikx.rt.mixins.botania;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.data.DataMap;
import crafttweaker.api.data.IData;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import crafttweaker.mc1120.data.NBTConverter;
import dev.ikx.rt.api.mods.botania.ICocoon;
import dev.ikx.rt.api.mods.botania.function.ICocoonTileEntity;
import dev.ikx.rt.impl.mods.botania.cocoon.IMixinTileCocoon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.tile.TileCocoon;
import vazkii.botania.common.block.tile.TileMod;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Pseudo
@Mixin(value = TileCocoon.class, remap = false)
public abstract class MixinTileCocoon extends TileMod implements IMixinTileCocoon, ICocoonTileEntity {

    @Unique
    private static final String CUSTOM_TAB = "CustomTab";

    @Unique
    private final Map<String, Integer> randomTweaker$customMap = new HashMap<>();

    @Override
    public int randomTweaker$getMapSize() {
        return randomTweaker$customMap.size();
    }

    @Override
    public IData getData() {
        return NBTConverter.from(this.getTileData(), false);
    }

    @Override
    public void updateData(IData data) {
        if (data instanceof DataMap) {
            this.getTileData().merge(CraftTweakerMC.getNBTCompound(data));
            this.markDirty();
        } else {
            CraftTweakerAPI.logError("data argument must be DataMap", new IllegalArgumentException());
        }
    }

    @Override
    public IWorld getIWorld() {
        return CraftTweakerMC.getIWorld(this.world);
    }

    @Override
    public IBlockPos getIBlockPos() {
        return CraftTweakerMC.getIBlockPos(this.pos);
    }

    @Override
    public int randomTweaker$getAmount(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return this.randomTweaker$getDynamicResult(stack, player).map(randomTweaker$customMap::get).orElse(0);
    }

    @Override
    public void randomTweaker$setAmount(World world, BlockPos pos, ItemStack stack, EntityPlayer player, int amount) {
        this.randomTweaker$getDynamicResult(stack, player).ifPresent(name -> this.randomTweaker$customMap.put(name, amount));
    }

    @Unique
    private Optional<String> randomTweaker$getDynamicResult(ItemStack stack, EntityPlayer player) {
        ICocoon cocoon = ICocoon.getInstanceByStack(stack);

        if (cocoon != null) {
            return Optional.ofNullable(cocoon.getDynamicResult(stack, player, this));
        }
        return Optional.empty();
    }

    @Unique
    private String randomTweaker$getTabName() {
        String name = null;

        for (Entry<String, Integer> entry : randomTweaker$customMap.entrySet()) {
            if (name == null) {
                name = entry.getKey();
            } else if (entry.getValue() > randomTweaker$customMap.get(name)) {
                name = entry.getKey();
            }
        }

        return name;
    }

    @Unique
    private void randomTweaker$spawnEntityLiving(EntityLiving entityLiving) {
        entityLiving.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        if (entityLiving instanceof EntityAgeable) {
            ((EntityAgeable) entityLiving).setGrowingAge(-24000);
        }
        entityLiving.onInitialSpawn(world.getDifficultyForLocation(pos), null);
        world.spawnEntity(entityLiving);
        entityLiving.spawnExplosionParticle();
    }

    @Inject(method = "readPacketNBT", at = @At(value = "HEAD"))
    private void injectReadPacketNBT(NBTTagCompound cmp, CallbackInfo ci) {
        NBTTagCompound data = cmp.getCompoundTag(CUSTOM_TAB);
        data.getKeySet().forEach(name ->
                this.randomTweaker$customMap.put(name, data.getInteger(name)));
    }

    @Inject(method = "writePacketNBT", at = @At(value = "HEAD"))
    private void injectWritePacketNBT(NBTTagCompound cmp, CallbackInfo ci) {
        if (!cmp.hasKey(CUSTOM_TAB)) {
            cmp.setTag(CUSTOM_TAB, new NBTTagCompound());
        }

        randomTweaker$customMap.forEach((name, amount) -> cmp.getCompoundTag(CUSTOM_TAB).setInteger(name, amount));
    }

    @Inject(method = "hatch", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLiving;setPosition(DDD)V", remap = true), cancellable = true)
    private void injectHatch(CallbackInfo ci) {
        ICocoon cocoon = ICocoon.getInstanceByName(this.randomTweaker$getTabName());

        if (cocoon != null) {
            for (EntityEntry entityEntry : cocoon.getSpawnTab().keySet()) {
                double probability = cocoon.getProbabilityByEntity(entityEntry);
                if (Math.random() < probability) {
                    Entity entity = entityEntry.newInstance(this.world);
                    if (entity instanceof EntityLiving) {
                        this.randomTweaker$spawnEntityLiving((EntityLiving) entity);
                        ci.cancel();
                        break;
                    }

                    CraftTweakerAPI.logWarning(entity.getName() + " doesn't extend EntityLiving");
                }
            }
        }
    }

}
