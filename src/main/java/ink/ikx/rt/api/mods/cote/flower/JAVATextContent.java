package ink.ikx.rt.api.mods.cote.flower;

public class JAVATextContent {

    public static final String GENERATING = "package ink.ikx.rt.api.mods.cote.flower.generating;\n"
        + "\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.EntityHelper;\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.player.CTPlayer;\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.enums.Hand;\n"
        + "import crafttweaker.api.data.IData;\n"
        + "import crafttweaker.api.minecraft.CraftTweakerMC;\n"
        + "import crafttweaker.mc1120.data.NBTConverter;\n"
        + "import ink.ikx.rt.RandomTweaker;\n"
        + "import ink.ikx.rt.api.mods.cote.flower.SubTileRepresentation;\n"
        + "import ink.ikx.rt.impl.utils.TileData;\n"
        + "import java.util.List;\n"
        + "import java.util.Objects;\n"
        + "import net.minecraft.block.state.IBlockState;\n"
        + "import net.minecraft.entity.EntityLivingBase;\n"
        + "import net.minecraft.entity.player.EntityPlayer;\n"
        + "import net.minecraft.item.ItemStack;\n"
        + "import net.minecraft.nbt.NBTTagCompound;\n"
        + "import net.minecraft.util.EnumFacing;\n"
        + "import net.minecraft.util.EnumHand;\n"
        + "import net.minecraft.util.math.BlockPos;\n"
        + "import net.minecraft.world.World;\n"
        + "import vazkii.botania.api.subtile.RadiusDescriptor;\n"
        + "\n"
        + "public class CustomSubTileGeneratingContent_${name} extends SubTileGeneratingContent {\n"
        + "\n"
        + "    private static final String TAG_NAME = \"SubTileName\";\n"
        + "    private final TileData customData = new TileData();\n"
        + "    public static final String TAG_CUSTOM_DATA = \"CustomData\";\n"
        + "    public final SubTileRepresentation subtile = RandomTweaker.subTileGeneratingMap.get(\"${name}\").getValue();\n"
        + "\n"
        + "    public String name;\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean canGeneratePassively() {\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        return Objects.nonNull(subtile.canGeneratePassively) && subtile.canGeneratePassively.call(CraftTweakerMC.getIBlockPos(getPos()), CraftTweakerMC.getIWorld(getWorld()));\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getColor() {\n"
        + "        return subtile.getColor();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public RadiusDescriptor getRadius() {\n"
        + "        return new RadiusDescriptor.Square(toBlockPos(), subtile.getRange());\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getMaxMana() {\n"
        + "        return subtile.getMaxMana();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean isPassiveFlower() {\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        return subtile.isPassiveFlower();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean acceptsRedstone() {\n"
        + "        return subtile.isAcceptsRedstone();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void readFromPacketNBT(NBTTagCompound compound) {\n"
        + "        customData.readFromNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));\n"
        + "        this.name = compound.getString(TAG_NAME);\n"
        + "        super.readFromPacketNBT(compound);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void writeToPacketNBT(NBTTagCompound compound) {\n"
        + "        if (!compound.hasKey(TAG_CUSTOM_DATA)) {\n"
        + "            compound.setTag(TAG_CUSTOM_DATA, new NBTTagCompound());\n"
        + "        }\n"
        + "        customData.writeToNBT(compound.getCompoundTag(TAG_NAME));\n"
        + "        customData.writeToNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));\n"
        + "        super.writeToPacketNBT(compound);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public IData getCustomData() {\n"
        + "        return customData.getData();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void setCustomData(IData data) {\n"
        + "        customData.readFromNBT((NBTTagCompound) NBTConverter.from(data));\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void updateCustomData(IData data) {\n"
        + "        TileData.checkDataMap(data);\n"
        + "        setCustomData(getCustomData().add(data));\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getDelayBetweenPassiveGeneration() {\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        return subtile.getDelayBetweenPassiveGeneration();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getValueForPassiveGeneration() {\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        return subtile.getValueForPassiveGeneration();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void addMana(int mana) {\n"
        + "        super.addMana(mana);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean isOvergrowthAffected() {\n"
        + "        return subtile.isOvergrowthAffected();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean shouldSyncPassiveGeneration() {\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        return subtile.isShouldSyncPassiveGeneration();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean canSelect(EntityPlayer player, ItemStack wand, BlockPos pos, EnumFacing side) {\n"
        + "        if (Objects.nonNull(subtile.canSelect)) {\n"
        + "            return subtile.canSelect.call(new CTPlayer(player), CraftTweakerMC.getIItemStack(wand), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getIFacing(side));\n"
        + "        }\n"
        + "        return true;\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onUpdate() {\n"
        + "        super.onUpdate();\n"
        + "        if (Objects.nonNull(subtile.onUpdate)) {\n"
        + "            subtile.onUpdate.call(this, CraftTweakerMC.getIWorld(getWorld()), CraftTweakerMC.getIBlockPos(getPos()));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {\n"
        + "        return !Objects.nonNull(subtile.onBlockActivated) || subtile.onBlockActivated.call(\n"
        + "            CraftTweakerMC.getIWorld(world),\n"
        + "            CraftTweakerMC.getIBlockPos(pos),\n"
        + "            CraftTweakerMC.getBlockState(state),\n"
        + "            new CTPlayer(player),\n"
        + "            Hand.of(hand),\n"
        + "            CraftTweakerMC.getIFacing(side), hitX, hitY, hitZ);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {\n"
        + "        if (Objects.nonNull(subtile.onBlockAdded)) {\n"
        + "            subtile.onBlockAdded.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {\n"
        + "        if (Objects.nonNull(subtile.onBlockHarvested)) {\n"
        + "            subtile.onBlockHarvested.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state), new CTPlayer(player));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {\n"
        + "        super.onBlockPlacedBy(world, pos, state, entity, stack);\n"
        + "        if (Objects.nonNull(subtile.onBlockPlaceBy)) {\n"
        + "            subtile.onBlockPlaceBy.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state), EntityHelper.getIEntityLivingBase(entity), CraftTweakerMC.getIItemStack(stack));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void populateDropStackNBTs(List<ItemStack> drops) {\n"
        + "        super.populateDropStackNBTs(drops);\n"
        + "        SubTileGeneratingRepresentation subtile = (SubTileGeneratingRepresentation) this.subtile;\n"
        + "        if (Objects.nonNull(subtile.populateDropStackNBTs)) {\n"
        + "            subtile.populateDropStackNBTs.call(CraftTweakerMC.getIItemStacks(drops.toArray(new ItemStack[0])));\n"
        + "        }\n"
        + "    }\n"
        + "}\n";

    public static final String FUNCTIONAL = "package ink.ikx.rt.api.mods.cote.flower.functional;\n"
        + "\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.EntityHelper;\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.player.CTPlayer;\n"
        + "import com.teamacronymcoders.contenttweaker.api.ctobjects.enums.Hand;\n"
        + "import crafttweaker.api.data.IData;\n"
        + "import crafttweaker.api.minecraft.CraftTweakerMC;\n"
        + "import crafttweaker.mc1120.data.NBTConverter;\n"
        + "import ink.ikx.rt.RandomTweaker;\n"
        + "import ink.ikx.rt.api.mods.cote.flower.SubTileRepresentation;\n"
        + "import ink.ikx.rt.impl.utils.TileData;\n"
        + "import java.util.Objects;\n"
        + "import net.minecraft.block.state.IBlockState;\n"
        + "import net.minecraft.entity.EntityLivingBase;\n"
        + "import net.minecraft.entity.player.EntityPlayer;\n"
        + "import net.minecraft.item.ItemStack;\n"
        + "import net.minecraft.nbt.NBTTagCompound;\n"
        + "import net.minecraft.util.EnumFacing;\n"
        + "import net.minecraft.util.EnumHand;\n"
        + "import net.minecraft.util.math.BlockPos;\n"
        + "import net.minecraft.world.World;\n"
        + "import vazkii.botania.api.subtile.RadiusDescriptor;\n"
        + "\n"
        + "public class SubTileFunctionalContentContent_${name} extends SubTileFunctionalContent {\n"
        + "\n"
        + "    private static final String TAG_NAME = \"SubTileName\";\n"
        + "    private final TileData customData = new TileData();\n"
        + "    public static final String TAG_CUSTOM_DATA = \"CustomData\";\n"
        + "    public final SubTileRepresentation subtile = RandomTweaker.subTileGeneratingMap.get(\"${name}\").getValue();\n"
        + "\n"
        + "    public String name;\n"
        + "\n"
        + "    @Override\n"
        + "    public RadiusDescriptor getRadius() {\n"
        + "        return new RadiusDescriptor.Square(toBlockPos(), subtile.getRange());\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {\n"
        + "        super.onBlockPlacedBy(world, pos, state, entity, stack);\n"
        + "        if (Objects.nonNull(subtile.onBlockPlaceBy)) {\n"
        + "            subtile.onBlockPlaceBy.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state), EntityHelper.getIEntityLivingBase(entity), CraftTweakerMC.getIItemStack(stack));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {\n"
        + "        return !Objects.nonNull(subtile.onBlockActivated) || subtile.onBlockActivated.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state), new CTPlayer(player), Hand.of(hand), CraftTweakerMC.getIFacing(side), hitX, hitY, hitZ);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {\n"
        + "        if (Objects.nonNull(subtile.onBlockAdded)) {\n"
        + "            subtile.onBlockAdded.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {\n"
        + "        if (Objects.nonNull(subtile.onBlockHarvested)) {\n"
        + "            subtile.onBlockHarvested.call(CraftTweakerMC.getIWorld(world), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getBlockState(state), new CTPlayer(player));\n"
        + "        }\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean isOvergrowthAffected() {\n"
        + "        return subtile.isOvergrowthAffected();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getMaxMana() {\n"
        + "        return subtile.getMaxMana();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public int getColor() {\n"
        + "        return subtile.getColor();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public boolean canSelect(EntityPlayer player, ItemStack wand, BlockPos pos, EnumFacing side) {\n"
        + "        if (Objects.nonNull(subtile.canSelect)) {\n"
        + "            return subtile.canSelect.call(new CTPlayer(player), CraftTweakerMC.getIItemStack(wand), CraftTweakerMC.getIBlockPos(pos), CraftTweakerMC.getIFacing(side));\n"
        + "        }\n"
        + "        return true;\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public IData getCustomData() {\n"
        + "        return customData.getData();\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void setCustomData(IData data) {\n"
        + "        customData.readFromNBT((NBTTagCompound) NBTConverter.from(data));\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void updateCustomData(IData data) {\n"
        + "        TileData.checkDataMap(data);\n"
        + "        setCustomData(getCustomData().add(data));\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void readFromPacketNBT(NBTTagCompound compound) {\n"
        + "        customData.readFromNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));\n"
        + "        this.name = compound.getString(TAG_NAME);\n"
        + "        super.readFromPacketNBT(compound);\n"
        + "    }\n"
        + "\n"
        + "    @Override\n"
        + "    public void writeToPacketNBT(NBTTagCompound compound) {\n"
        + "        if (!compound.hasKey(TAG_CUSTOM_DATA)) {\n"
        + "            compound.setTag(TAG_CUSTOM_DATA, new NBTTagCompound());\n"
        + "        }\n"
        + "        customData.writeToNBT(compound.getCompoundTag(TAG_NAME));\n"
        + "        customData.writeToNBT(compound.getCompoundTag(TAG_CUSTOM_DATA));\n"
        + "        super.writeToPacketNBT(compound);\n"
        + "    }\n"
        + "\n"
        + "    public static class Mini extends SubTileFunctionalContentContent_${name} {\n"
        + "        @Override\n"
        + "        public RadiusDescriptor getRadius() {\n"
        + "            return new RadiusDescriptor.Square(toBlockPos(), ((SubTileFunctionalRepresentation) subtile).getMiniRange());\n"
        + "        }\n"
        + "    }\n"
        + "}\n";
}