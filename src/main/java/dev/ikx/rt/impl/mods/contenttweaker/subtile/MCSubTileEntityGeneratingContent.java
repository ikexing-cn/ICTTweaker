package dev.ikx.rt.impl.mods.contenttweaker.subtile;

import com.teamacronymcoders.contenttweaker.api.ctobjects.blockpos.MCBlockPos;
import com.teamacronymcoders.contenttweaker.api.ctobjects.blockstate.MCBlockState;
import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.EntityHelper;
import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.player.CTPlayer;
import com.teamacronymcoders.contenttweaker.api.ctobjects.enums.Hand;
import com.teamacronymcoders.contenttweaker.api.ctobjects.world.MCWorld;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityInGame;
import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.generating.ISubTileEntityGeneratingRepresentation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import youyihj.zenutils.api.cotx.tile.TileData;

import java.util.List;
import java.util.Objects;

public class MCSubTileEntityGeneratingContent extends SubTileGenerating implements ISubTileEntityInGame {

    public final ISubTileEntityRepresentation subtile;
    private final TileData customData = new TileData();

    public MCSubTileEntityGeneratingContent(ISubTileEntityRepresentation subtile) {
        this.subtile = subtile;
    }

    @Override
    public TileData getTileData() {
        return customData;
    }

    @Override
    public boolean canGeneratePassively() {
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        return Objects.nonNull(subtile.canGeneratePassively) && subtile.canGeneratePassively.call(new MCBlockPos(getPos()), new MCWorld(getWorld()));
    }

    @Override
    public int getColor() {
        return subtile.color;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), subtile.range);
    }

    @Override
    public int getTicksExisted() {
        return ticksExisted;
    }

    @Override
    public void consumeMana(int mana) {
        this.addMana(-mana);
    }

    @Override
    public int getMaxMana() {
        return subtile.maxMana;
    }

    @Override
    public boolean isPassiveFlower() {
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        return subtile.passiveFlower;
    }

    @Override
    public boolean acceptsRedstone() {
        return subtile.acceptsRedstone;
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound compound) {
        customData.readFromNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));
        super.readFromPacketNBT(compound);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound compound) {
        if (!compound.hasKey(TAG_CUSTOM_DATA)) {
            compound.setTag(TAG_CUSTOM_DATA, new NBTTagCompound());
        }
        customData.writeToNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));
        super.writeToPacketNBT(compound);
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        return subtile.delayBetweenPassiveGeneration;
    }

    @Override
    public int getValueForPassiveGeneration() {
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        return subtile.valueForPassiveGeneration;
    }

    @Override
    public void addMana(int mana) {
        super.addMana(mana);

        if (this.mana < 0) {
            this.mana = 0;
        }
    }

    @Override
    public boolean isOvergrowthAffected() {
        return subtile.overgrowthAffected;
    }

    @Override
    public boolean shouldSyncPassiveGeneration() {
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        return subtile.shouldSyncPassiveGeneration;
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, BlockPos pos, EnumFacing side) {
        if (Objects.nonNull(subtile.canSelect)) {
            return subtile.canSelect.call(new CTPlayer(player), CraftTweakerMC.getIItemStack(wand), new MCBlockPos(pos), CraftTweakerMC.getIFacing(side));
        }
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (Objects.nonNull(subtile.onUpdate)) {
            subtile.onUpdate.call(this, new MCWorld(getWorld()), new MCBlockPos(getPos()));
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return Objects.nonNull(subtile.onBlockActivated) && subtile.onBlockActivated.call(new MCWorld(world), new MCBlockPos(pos), new MCBlockState(state), new CTPlayer(player), Hand.of(hand), CraftTweakerMC.getIFacing(side), hitX, hitY, hitZ);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (Objects.nonNull(subtile.onBlockAdded)) {
            subtile.onBlockAdded.call(new MCWorld(world), new MCBlockPos(pos), new MCBlockState(state));
        }
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (Objects.nonNull(subtile.onBlockHarvested)) {
            subtile.onBlockHarvested.call(new MCWorld(world), new MCBlockPos(pos), new MCBlockState(state), new CTPlayer(player));
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entity, stack);
        if (Objects.nonNull(subtile.onBlockPlaceBy)) {
            subtile.onBlockPlaceBy.call(new MCWorld(world), new MCBlockPos(pos), new MCBlockState(state), EntityHelper.getIEntityLivingBase(entity), CraftTweakerMC.getIItemStack(stack));
        }
    }

    @Override
    public void populateDropStackNBTs(List<ItemStack> drops) {
        super.populateDropStackNBTs(drops);
        ISubTileEntityGeneratingRepresentation subtile = (ISubTileEntityGeneratingRepresentation) this.subtile;
        if (Objects.nonNull(subtile.populateDropStackNBTs)) {
            subtile.populateDropStackNBTs.call(CraftTweakerMC.getIItemStacks(drops.toArray(new ItemStack[0])));
        }
    }

    @Override
    public void sync() {
        super.sync();
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        if (mana < 0) {
            mana = 0;
        } else if (mana > this.getMaxMana()) {
            mana = this.getMaxMana();
        }
        this.mana = mana;
    }

    @Override
    public int getRedstoneSignal() {
        return this.redstoneSignal;
    }

    @Override
    public int getPassiveDecayTicks() {
        return this.passiveDecayTicks;
    }

    @Override
    public boolean isValidBinding() {
        return super.isValidBinding();
    }

    @Override
    public IBlockPos getBindingForCrT() {
        return CraftTweakerMC.getIBlockPos(super.getBinding());
    }

    @Override
    public String typeOf() {
        return "generating";
    }

    @Override
    public String getUnlocalizedName() {
        return subtile.unlocalizedName;
    }

    @Override
    public Object getInstance() {
        return subtile;
    }
}
