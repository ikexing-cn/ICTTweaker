package dev.ikx.rt.mixins.botania;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.data.DataMap;
import crafttweaker.api.data.IData;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import crafttweaker.mc1120.data.NBTConverter;
import dev.ikx.rt.api.mods.botania.ITileAlfPortal;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import dev.ikx.rt.impl.internal.utils.ItemStackList;
import dev.ikx.rt.impl.mods.botania.event.AlfPortalDroppedEvent;
import dev.ikx.rt.impl.mods.botania.event.ElvenTradeEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.api.recipe.ElvenPortalUpdateEvent;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.state.enums.AlfPortalState;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.block.tile.TileMod;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Pseudo
@Mixin(value = TileAlfPortal.class, remap = false)
public abstract class MixinTileAlfPortal extends TileMod implements ITileAlfPortal {

    @Shadow
    @Final
    private static String TAG_PORTAL_FLAG;
    private final ItemStackList inputList = new ItemStackList();
    @Shadow
    @Final
    private List<ItemStack> stacksIn;
    private ItemStack stacksCopy;
    private boolean eventExec;

    @Shadow
    protected abstract void spawnItem(ItemStack stack);

    @Shadow
    public abstract boolean consumeMana(List<BlockPos> pylons, int totalCost, boolean close);


    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/tile/TileAlfPortal;validateItemUsage(Lnet/minecraft/item/ItemStack;)Z", remap = false), remap = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectUpdate(CallbackInfo ci, IBlockState iBlockState, AlfPortalState state, AlfPortalState newState,
                              AxisAlignedBB aabb, boolean open, ElvenPortalUpdateEvent eventU, List<?> items, Iterator<?> var8,
                              EntityItem item, ItemStack stack, boolean consume) { // validateItemUsage
        AlfPortalDroppedEvent event = new AlfPortalDroppedEvent(item, this);
        eventExec = event.post();
        stacksCopy = event.getInput().getItem();
        if (!eventExec) {
            addInput(CraftTweakerMC.getIItemStack(stacksCopy));
            item.isDead = event.isDead();
            if (!item.isDead)
                item.getEntityData().setBoolean(TAG_PORTAL_FLAG, true);
        } else {
            if (event.getOutput() != null) {
                event.getOutput().forEach(this::spawnItem);
            } else {
                if (item.isDead)
                    spawnItem(stacksCopy);
            }
        }
    }

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/tile/TileAlfPortal;addItem(Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER, remap = false), remap = true)
    private void injectUpdate(CallbackInfo ci) {
        AtomicInteger count = new AtomicInteger(1);
        if (eventExec) {
            stacksIn.removeIf(a -> {
                if (count.get() <= stacksCopy.getCount()) {
                    InternalUtils.areItemStacksEqual(stacksCopy, a);
                    count.getAndIncrement();
                    return true;
                }
                return false;
            });
        }
    }

    @Inject(method = "resolveRecipes", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/recipe/RecipeElvenTrade;matches(Ljava/util/List;Z)Z", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void injectResolveRecipes(CallbackInfo ci, int i, Iterator<?> var2, RecipeElvenTrade recipe) {
        ElvenTradeEvent event = new ElvenTradeEvent(this, stacksIn, recipe.getOutputs());
        recipe.matches(stacksIn, true);
        if (!event.post() && Objects.nonNull(event.getOutput())) {
            event.getOutput().stream().filter(Objects::nonNull).forEach(this::spawnItem);
        }
        ci.cancel();
    }

    @Override
    public IItemStack[] getInputList() {
        return inputList.getItemStackList().stream().map(CraftTweakerMC::getIItemStack).toArray(IItemStack[]::new);
    }

    @Override
    public void setInputList(IItemStack[] newList) {
        inputList.setItemStackList(Arrays.stream(newList).map(CraftTweakerMC::getItemStack).collect(Collectors.toList()));
    }

    @Override
    public void delInput(IItemStack stack) {
        inputList.remove(CraftTweakerMC.getItemStack(stack));
    }

    @Override
    public void addInput(IItemStack stack) {
        inputList.add(CraftTweakerMC.getItemStack(stack));
    }

    @Override
    public void clearInputList() {
        inputList.clear();
    }

    @Override
    public boolean consumeMana(int totalCost) {
        return this.consumeMana(null, totalCost, false);
    }

    @Override
    public void spawnItemStack(IItemStack stack) {
        spawnItem(CraftTweakerMC.getItemStack(stack));
    }

    @Override
    public IBlockPos getBlockPos() {
        return CraftTweakerMC.getIBlockPos(this.getPos());
    }

    @Override
    public IWorld getIWorld() {
        return CraftTweakerMC.getIWorld(this.getWorld());
    }

    @Override
    public boolean isEmpty() {
        return this.getTileData().isEmpty();
    }

    @Override
    public IData getData() {
        return NBTConverter.from(this.getTileData(), false);
    }

    @Override
    public void setData(IData data) {
        if (data instanceof DataMap) {
            this.getTileData().merge((NBTTagCompound) NBTConverter.from(data));
            this.markDirty();
        } else {
            CraftTweakerAPI.logError("data argument must be DataMap", new IllegalArgumentException());
        }
    }

    @Override
    public void updateData(IData data) {
        this.setData(this.getData().add(data));
    }

}