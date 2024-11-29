package dev.ikx.rt.impl.mods.jei.impl.elemenet;

import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import dev.ikx.rt.impl.mods.jei.JeiPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class MCJeiElements {

    public static class MCJeiElementItemInput extends IJeiElements.IJeiElementItemInput {

        public MCJeiElementItemInput(int x, int y) {
            super(x, y);
        }

        public MCJeiElementItemInput(String elementName, int x, int y) {
            super(elementName, x, y);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JeiPlugin.DEFAULT_TEXTURE));
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, 256, 256);
        }

    }

    public static class MCJeiElementItemOutput extends IJeiElements.IJeiElementItemOutput {

        public MCJeiElementItemOutput(int x, int y) {
            super(x, y);
        }

        public MCJeiElementItemOutput(String elementName, int x, int y) {
            super(elementName, x, y);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JeiPlugin.DEFAULT_TEXTURE));
            Gui.drawModalRectWithCustomSizedTexture(x - 4, y - 4, u, v, width, height, 256, 256);
        }

    }

    public static class MCJeiElementFontInfo extends IJeiElements.IJeiElementFontInfo {

        public MCJeiElementFontInfo(int x, int y, int width, int height, int color, String info) {
            super(x, y, width, height, color, info);
        }

        public MCJeiElementFontInfo(String elementName, int x, int y, int width, int height, int color, String info) {
            super(elementName, x, y, width, height, color, info);
        }

        @Override
        public void render(Minecraft minecraft) {
            FontRenderer fontRenderer = minecraft.fontRenderer;
            fontRenderer.drawString(this.info, this.x, this.y, this.color);
        }

    }

    public static class MCJeiElementImage extends IJeiElements.IJeiElementImage {


        public MCJeiElementImage(int u, int v, int x, int y, int width, int height,
                                 String texture, int textureWidth, int textureHeight) {
            super(u, v, x, y, width, height, texture, textureWidth, textureHeight);
        }

        public MCJeiElementImage(String elementName, int u, int v, int x, int y, int width, int height,
                                 String texture, int textureWidth, int textureHeight) {
            super(elementName, u, v, x, y, width, height, texture, textureWidth, textureHeight);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(texture));
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
        }

    }

}
