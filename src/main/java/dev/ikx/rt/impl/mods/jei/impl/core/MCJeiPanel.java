package dev.ikx.rt.impl.mods.jei.impl.core;

import com.google.common.collect.Lists;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.mc1120.brackets.BracketHandlerItem;
import dev.ikx.rt.Constant;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.jei.core.IJeiBackground;
import dev.ikx.rt.api.mods.jei.core.IJeiPanel;
import dev.ikx.rt.api.mods.jei.core.IJeiTooltip;
import dev.ikx.rt.api.mods.jei.elements.IJeiElement;
import dev.ikx.rt.api.mods.jei.slots.IJeiSlot;
import org.apache.commons.lang3.StringUtils;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;

import java.util.Arrays;
import java.util.List;

public class MCJeiPanel implements IJeiPanel {

    public final List<IJeiSlot> slots = Lists.newArrayList();
    public final List<IJeiElement> elements = Lists.newArrayList();
    public final List<IItemStack> recipeCatalysts = Lists.newArrayList();
    public String uid;
    public String title;
    public IJeiTooltip tooltip;
    public IJeiBackground background;
    public String modid = Constant.MODID;
    public IItemStack icon = BracketHandlerItem.getItem("minecraft:bedrock", 0);

    public MCJeiPanel(String uid, String title) {
        this.uid = uid;
        this.title = title;
    }

    @Override
    public IJeiPanel setModid(String modid) {
        this.modid = modid;
        return this;
    }

    @Override
    public IJeiPanel setIcon(IItemStack icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public IJeiPanel addSlot(IJeiSlot slot) {
        this.slots.add(slot);
        return this;
    }

    @Override
    public IJeiPanel setSlots(IJeiSlot[] slots) {
        this.slots.clear();
        Arrays.stream(slots).forEach(this::addSlot);
        return this;
    }

    @Override
    public IJeiPanel onTooltip(IJeiTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public IJeiPanel addElement(IJeiElement element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public IJeiPanel setElements(IJeiElement[] elements) {
        this.elements.clear();
        Arrays.stream(elements).forEach(this::addElement);
        return this;
    }

    @Override
    public IJeiPanel addRecipeCatalyst(IItemStack stack) {
        this.recipeCatalysts.add(stack);
        return this;
    }

    @Override
    public IJeiPanel setRecipeCatalysts(IItemStack[] stacks) {
        this.recipeCatalysts.clear();
        Arrays.stream(stacks).forEach(this::addRecipeCatalyst);
        return this;
    }

    @Override
    public IJeiPanel setBackground(IJeiBackground background) {
        this.background = background;
        return this;
    }

    @Override
    public IJeiSlot[] getJeiSlots() {
        return this.slots.toArray(new IJeiSlot[0]);
    }

    @Override
    public IJeiSlot getJeiSlot(String slotName) {
        if (StringUtils.isBlank(slotName)) return null;
        return this.slots.stream().filter(s -> s.slotName.equals(slotName)).findFirst().orElse(null);
    }

    @Override
    public IJeiElement[] getJeiElements() {
        return this.elements.toArray(new IJeiElement[0]);
    }

    @Override
    public IJeiElement getJeiElement(String elementName) {
        if (StringUtils.isBlank(elementName)) return null;
        return this.elements.stream().filter(e -> e.elementName.equals(elementName)).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCJeiPanel that = (MCJeiPanel) o;

        return uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    @Override
    public void register() {
        CraftTweakerAPI.apply(new ActionAddJeiPanel(this));
    }

    @Override
    public void register_() {
        Main.JEI_PANEL_SET.add(this);
    }

    @Reloadable
    public static class ActionAddJeiPanel implements IAction {

        private final MCJeiPanel panel;

        public ActionAddJeiPanel(MCJeiPanel panel) {
            this.panel = panel;
        }

        @Override
        public void apply() {
            Main.JEI_PANEL_SET.add(panel);
        }

        @ReflectionInvoked
        public void undo() {
            Main.JEI_PANEL_SET.remove(panel);
        }

        @Override
        public String describe() {
            return "Adding JEIPanel for uid -> " + panel.uid;
        }

        @Override
        public boolean validate() {
            return !Main.JEI_PANEL_SET.contains(panel);
        }

        @Override
        public String describeInvalid() {
            return "The JeiPanel already exists, don't add it repeatedly.";
        }

    }

}
