package dev.ikx.rt.impl.mods.botania.event;

import dev.ikx.rt.api.mods.botania.ITileAlfPortal;
import dev.ikx.rt.impl.internal.event.BaseEvent;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

import java.util.List;

@Cancelable
public class ElvenTradeEvent extends BaseEvent {

    private final ITileAlfPortal alfPortal;
    private final List<ItemStack> input;

    private List<ItemStack> output;

    public ElvenTradeEvent(ITileAlfPortal alfPortal, List<ItemStack> input, List<ItemStack> output) {
        this.alfPortal = alfPortal;
        this.input = InternalUtils.getItemStackListCopy(input);
        this.output = InternalUtils.getItemStackListCopy(output);
    }

    public ITileAlfPortal getAlfPortal() {
        return alfPortal;
    }

    public List<ItemStack> getInput() {
        return input;
    }

    public List<ItemStack> getOutput() {
        return output;
    }

    public void setOutput(List<ItemStack> stacks) {
        this.output = stacks;
    }

    public void addOutput(ItemStack stack) {
        this.output.add(stack);
    }

}
