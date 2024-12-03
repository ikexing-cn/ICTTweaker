package dev.ikx.rt.impl.mods.jei.impl.core;

import com.google.common.collect.Lists;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.mc1120.brackets.BracketHandlerItem;
import dev.ikx.rt.Constant;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.jei.core.JEIBackground;
import dev.ikx.rt.api.mods.jei.core.JEIPanel;
import dev.ikx.rt.api.mods.jei.core.JEITooltip;
import dev.ikx.rt.api.mods.jei.elements.JEIElement;
import dev.ikx.rt.api.mods.jei.slots.JEISlot;
import org.apache.commons.lang3.StringUtils;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;

import java.util.Arrays;
import java.util.List;

// TODO
public class MCJeiPanel implements JEIPanel {

    public final List<JEISlot> slots = Lists.newArrayList();
    public final List<JEIElement> elements = Lists.newArrayList();
    public final List<IItemStack> recipeCatalysts = Lists.newArrayList();
    public String uid;
    public String title;
    public JEITooltip tooltip;
    public JEIBackground background;
    public String modid = Constant.MODID;
    public IItemStack icon = BracketHandlerItem.getItem("minecraft:bedrock", 0);

    public MCJeiPanel(String uid, String title) {
        this.uid = uid;
        this.title = title;
    }

    @Override
    public JEIPanel setModid(String modid) {
        this.modid = modid;
        return this;
    }

    @Override
    public JEIPanel setIcon(IItemStack icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public JEIPanel addSlot(JEISlot slot) {
        this.slots.add(slot);
        return this;
    }

    @Override
    public JEIPanel setSlots(JEISlot[] slots) {
        this.slots.clear();
        Arrays.stream(slots).forEach(this::addSlot);
        return this;
    }

    @Override
    public JEIPanel onTooltip(JEITooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public JEIPanel addElement(JEIElement element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public JEIPanel setElements(JEIElement[] elements) {
        this.elements.clear();
        Arrays.stream(elements).forEach(this::addElement);
        return this;
    }

    @Override
    public JEIPanel addRecipeCatalyst(IItemStack stack) {
        this.recipeCatalysts.add(stack);
        return this;
    }

    @Override
    public JEIPanel setRecipeCatalysts(IItemStack[] stacks) {
        this.recipeCatalysts.clear();
        Arrays.stream(stacks).forEach(this::addRecipeCatalyst);
        return this;
    }

    @Override
    public JEIPanel setBackground(JEIBackground background) {
        this.background = background;
        return this;
    }

    @Override
    public JEISlot[] getJeiSlots() {
        return this.slots.toArray(new JEISlot[0]);
    }

    @Override
    public JEISlot getJeiSlot(String slotName) {
        if (StringUtils.isBlank(slotName)) return null;
        return this.slots.stream().filter(s -> s.slotName.equals(slotName)).findFirst().orElse(null);
    }

    @Override
    public JEIElement[] getJeiElements() {
        return this.elements.toArray(new JEIElement[0]);
    }

    @Override
    public JEIElement getJeiElement(String elementName) {
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
    public void registerDirect() {
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
