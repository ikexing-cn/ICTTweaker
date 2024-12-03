package dev.ikx.rt.impl.mods.botania.subtile;

import dev.ikx.rt.impl.internal.config.RTConfig;
import dev.ikx.rt.impl.mods.botania.module.SubTileOrechidManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ModSounds;
import vazkii.botania.common.lexicon.LexiconData;

import java.util.*;
import java.util.function.Predicate;

public class SubTileOrechidModified extends SubTileFunctional {

    private static final int COST = RTConfig.Botania.OrechidCost;
    private static final int DELAY = RTConfig.Botania.OrechidDelay;
    private static final int MAX_MANA = RTConfig.Botania.OrechidMaxMana;

    private static final int RANGE_Y = 3;
    private static final int RANGE = 5;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (supertile.getWorld().isRemote || redstoneSignal > 0 || !canOperate())
            return;

        int cost = getCost();
        if (mana >= cost && ticksExisted % getDelay() == 0) {
            BlockPos coords = getCoordsToPut();
            if (coords != null) {
                ItemStack stack = getOreToPut(getWorld().getBlockState(coords));
                if (!stack.isEmpty()) {
                    Block block = Block.getBlockFromItem(stack.getItem());
                    int meta = stack.getItemDamage();
                    supertile.getWorld().setBlockState(coords, block.getStateFromMeta(meta), 1 | 2);
                    if (ConfigHandler.blockBreakParticles)
                        supertile.getWorld().playEvent(2001, coords, Block.getIdFromBlock(block) + (meta << 12));
                    supertile.getWorld().playSound(null, supertile.getPos(), ModSounds.orechid, SoundCategory.BLOCKS, 2F, 1F);

                    mana -= cost;
                    sync();
                }
            }
        }
    }

    public ItemStack getOreToPut(IBlockState state) {
        List<WeightedRandom.Item> values = new ArrayList<>();
        Map<IBlockState, Map<String, Integer>> map = getOreMap();
        map.get(state).forEach((k, v) -> values.add(new StringRandomItem(map.get(state).get(k), k)));

        String ore = ((StringRandomItem) WeightedRandom.getRandomItem(supertile.getWorld().rand, values)).s;

        List<ItemStack> ores = OreDictionary.getOres(ore);
        Collections.shuffle(ores); // Random Get

        for (ItemStack stack : ores) {
            Item item = stack.getItem();

            if (!(item instanceof ItemBlock))
                continue;

            return stack;
        }

        return getOreToPut(state);
    }

    private BlockPos getCoordsToPut() {
        List<BlockPos> possibleCoords = new ArrayList<>();

        for (BlockPos pos : BlockPos.getAllInBox(getPos().add(-RANGE, -RANGE_Y, -RANGE), getPos().add(RANGE, RANGE_Y, RANGE))) {
            IBlockState state = supertile.getWorld().getBlockState(pos);
            if (state.getBlock().isReplaceableOreGen(state, supertile.getWorld(), pos, getReplaceMatcher()::test))
                possibleCoords.add(pos);
        }

        if (possibleCoords.isEmpty())
            return null;
        return possibleCoords.get(supertile.getWorld().rand.nextInt(possibleCoords.size()));
    }

    public boolean canOperate() {
        return true;
    }

    public Map<IBlockState, Map<String, Integer>> getOreMap() {
        return SubTileOrechidManager.oreWeights;
    }

    public Predicate<IBlockState> getReplaceMatcher() {
        return (state -> Optional.of(SubTileOrechidManager.oreWeights).filter(map -> map.containsKey(state)).isPresent());
    }

    public int getCost() {
        return COST;
    }

    public int getDelay() {
        return DELAY;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), RANGE);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 0x818181;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.orechid;
    }

    private static class StringRandomItem extends WeightedRandom.Item {

        public final String s;

        public StringRandomItem(int par1, String s) {
            super(par1);
            this.s = s;
        }

    }

}