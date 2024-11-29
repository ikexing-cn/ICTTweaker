package dev.ikx.rt.impl.mods.jei.impl.elemenet;

import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import dev.ikx.rt.impl.mods.jei.JeiPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class MCJeiElementArrow extends IJeiElements.IJeiElementArrow {

    public MCJeiElementArrow(int x, int y, int direction) {
        super(x, y, direction);
    }

    public MCJeiElementArrow(String elementName, int x, int y, int direction) {
        super(elementName, x, y, direction);
    }

    @Override
    public void render(Minecraft minecraft) {
        minecraft.getTextureManager().bindTexture(new ResourceLocation(JeiPlugin.DEFAULT_TEXTURE));
        switch (direction) {
            case 0:
                render(36, 0, 22, 15);
                break;
            case 1:
                render(36, 16, 22, 15);
                break;
            case 2:
                render(64, 0, 15, 22);
                break;
            case 3:
                render(64, 32, 15, 22);
                break;
            default:
                CraftTweakerAPI.getLogger().logError("The direction isn't exist.");
                break;
        }
    }

    private void render(int u, int v, int width, int height) {
        Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, 256, 256);
    }

}