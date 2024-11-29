package dev.ikx.rt.impl.mods.botania.event;

import dev.ikx.rt.impl.internal.event.BaseEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class PoolTradeEvent extends BaseEvent {

    private final int currentMana;
    private final EntityItem input;
    private final boolean isAlchemy;
    private final boolean isConjuration;
    private final World world;
    private final BlockPos blockPos;
    private int mana;
    private ItemStack output;
    private boolean isAllowExceed;

    public PoolTradeEvent(int mana, int currentMana, ItemStack output, EntityItem input,
                          boolean isAlchemy, boolean isConjuration, World world, BlockPos blockPos) {
        this.mana = mana;
        this.output = output;
        this.currentMana = currentMana;
        this.input = input;
        this.isAlchemy = isAlchemy;
        this.isConjuration = isConjuration;
        this.world = world;
        this.blockPos = blockPos;
    }

    public boolean isAlchemy() {
        return isAlchemy;
    }

    public boolean isConjuration() {
        return isConjuration;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public boolean isAllowExceed() {
        return isAllowExceed;
    }

    public void setAllowExceed(boolean allowExceed) {
        isAllowExceed = allowExceed;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public EntityItem getInput() {
        return input;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

}
