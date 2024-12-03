package dev.ikx.rt.api.mods.jei.elements;

import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.impl.mods.jei.JEIPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.tile.mana.TilePool;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

public class JEIElements {

    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.JEIElementItemInput")
    public static class JEIElementItemInput extends JEIElement {

        public JEIElementItemInput(int x, int y) {
            super(x, y, 18, 18);
        }

        public JEIElementItemInput(String elementName, int x, int y) {
            super(elementName, x, y, 18, 18);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JEIPlugin.DEFAULT_TEXTURE));
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, 256, 256);
        }
    }


    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.JEIElementItemOutput")
    public static class JEIElementItemOutput extends JEIElement {

        public JEIElementItemOutput(int x, int y) {
            super(80, 0, x, y, 27, 27);
        }

        public JEIElementItemOutput(String elementName, int x, int y) {
            super(elementName, 80, 0, x, y, 27, 27);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JEIPlugin.DEFAULT_TEXTURE));
            Gui.drawModalRectWithCustomSizedTexture(x - 4, y - 4, u, v, width, height, 256, 256);
        }

    }


    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.JEIElementLiquid")
    public static class JEIElementLiquid extends JEIElement {

        public JEIElementLiquid(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        public JEIElementLiquid(String elementName, int x, int y, int width, int height) {
            super(elementName, x, y, width, height);
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JEIPlugin.DEFAULT_TEXTURE));
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


    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJEIElementArrow")
    public static class JEIElementArrow extends JEIElement {

        @ZenProperty
        public int direction;

        public JEIElementArrow(int x, int y, int direction) {
            super(x, y, 0, 0);
            this.direction = direction;
        }

        public JEIElementArrow(String elementName, int x, int y, int direction) {
            super(elementName, x, y, 0, 0);
            this.direction = direction;
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(JEIPlugin.DEFAULT_TEXTURE));
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


    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJEIElementFontInfo")
    public static class JEIElementFontInfo extends JEIElement {

        @ZenProperty
        public int color;

        @ZenProperty
        public String info;

        public JEIElementFontInfo(int x, int y, int width, int height, int color, String info) {
            super(x, y, width, height);
            this.info = info;
            this.color = color;
        }

        public JEIElementFontInfo(String elementName, int x, int y, int width, int height, int color, String info) {
            super(elementName, x, y, width, height);
            this.info = info;
            this.color = color;
        }

        @Override
        public void render(Minecraft minecraft) {
            FontRenderer fontRenderer = minecraft.fontRenderer;
            fontRenderer.drawString(this.info, this.x, this.y, this.color);
        }
    }


    @SidedZenRegister(modDeps = {"jei", "botania"})
    @ZenClass("mods.randomtweaker.jei.JEIElementManaBar")
    public static class JEIElementManaBar extends JEIElement {

        @ZenProperty
        public int mode;

        @ZenProperty
        public int mana;

        private int multiplesLog = 0;
        private int manaMax = TilePool.MAX_MANA_DILLUTED;

        public JEIElementManaBar(int x, int y, int mode, int mana) {
            super(x, y, 0, 0);
            this.mode = mode;
            this.mana = mana;
        }

        public JEIElementManaBar(String elementName, int x, int y, int mode, int mana) {
            super(elementName, x, y, 0, 0);
            this.mode = mode;
            this.mana = mana;
        }


        @Override
        public void render(Minecraft minecraft) {
            handlerMode(mode);
            HUDHandler.renderManaBar(x, y, 0x0000FF, 0.75F, mana, manaMax);
            String sign = "x";
            if (multiplesLog < 0)
                sign = "/";
            if (multiplesLog != 0)
                minecraft.fontRenderer.drawString(sign + Math.pow(10, Math.abs(multiplesLog)), x + 103, y - 2, 0x8B8B8B);
        }

        private void handlerMode(int mode) {
            if (mode == 1) {
                for (; mana > manaMax; manaMax *= 10)
                    multiplesLog += 1;
                for (; (manaMax / mana > 50); manaMax /= 10)
                    multiplesLog -= 1;
            } else {
                manaMax = TilePool.MAX_MANA / 10;
                multiplesLog = 0;
            }
        }

    }


    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.JEIElementImage")
    public static class JEIElementImage extends JEIElement {

        @ZenProperty
        public String texture;

        @ZenProperty
        public int textureHeight;

        @ZenProperty
        public int textureWidth;

        public JEIElementImage(int u, int v, int x, int y, int width, int height,
                               String texture, int textureWidth, int textureHeight) {
            super(u, v, x, y, width, height);
            this.texture = texture;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
        }

        public JEIElementImage(String elementName, int u, int v, int x, int y, int width, int height,
                               String texture, int textureWidth, int textureHeight) {
            super(elementName, u, v, x, y, width, height);
            this.texture = texture;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
        }

        @Override
        public void render(Minecraft minecraft) {
            minecraft.getTextureManager().bindTexture(new ResourceLocation(texture));
            Gui.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
        }

    }

}
