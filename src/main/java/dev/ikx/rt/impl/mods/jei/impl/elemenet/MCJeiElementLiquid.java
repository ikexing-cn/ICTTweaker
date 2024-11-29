package dev.ikx.rt.impl.mods.jei.impl.elemenet;

import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import dev.ikx.rt.impl.mods.jei.JeiPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class MCJeiElementLiquid extends IJeiElements.IJeiElementLiquid {

    public MCJeiElementLiquid(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public MCJeiElementLiquid(String elementName, int x, int y, int width, int height) {
        super(elementName, x, y, width, height);
    }

    @Override
    public void render(Minecraft minecraft) {
        minecraft.getTextureManager().bindTexture(new ResourceLocation(JeiPlugin.DEFAULT_TEXTURE));
        if (width == 16 && height == 16) {
            render(18, 0);
        } else if (width == 34 && height == 16) {
            render(0, 18);
        } else if (width == 16 && height == 34) {
            render(0, 36);
        }
    }

    private void render(int u, int v) {
        Gui.drawModalRectWithCustomSizedTexture(x - 1, y - 1, u, v, width + 2, height + 2, 256, 256);
    }

}