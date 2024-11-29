package dev.ikx.rt.api.mods.jei.elements;

import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

public abstract class IJeiElements {

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementItemInput")
    public abstract static class IJeiElementItemInput extends IJeiElement {

        protected IJeiElementItemInput(int x, int y) {
            super(x, y, 18, 18);
        }

        protected IJeiElementItemInput(String elementName, int x, int y) {
            super(elementName, x, y, 18, 18);
        }
    }

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementItemOutput")
    public abstract static class IJeiElementItemOutput extends IJeiElement {

        protected IJeiElementItemOutput(int x, int y) {
            super(80, 0, x, y, 27, 27);
        }

        protected IJeiElementItemOutput(String elementName, int x, int y) {
            super(elementName, 80, 0, x, y, 27, 27);
        }

    }

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementLiquid")
    public abstract static class IJeiElementLiquid extends IJeiElement {

        protected IJeiElementLiquid(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        protected IJeiElementLiquid(String elementName, int x, int y, int width, int height) {
            super(elementName, x, y, width, height);
        }

    }

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementArrow")
    public abstract static class IJeiElementArrow extends IJeiElement {

        @ZenProperty
        public int direction;

        protected IJeiElementArrow(int x, int y, int direction) {
            super(x, y, 0, 0);
            this.direction = direction;
        }

        protected IJeiElementArrow(String elementName, int x, int y, int direction) {
            super(elementName, x, y, 0, 0);
            this.direction = direction;
        }

    }

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementFontInfo")
    public abstract static class IJeiElementFontInfo extends IJeiElement {

        @ZenProperty
        public int color;

        @ZenProperty
        public String info;

        protected IJeiElementFontInfo(int x, int y, int width, int height, int color, String info) {
            super(x, y, width, height);
            this.info = info;
            this.color = color;
        }

        protected IJeiElementFontInfo(String elementName, int x, int y, int width, int height, int color, String info) {
            super(elementName, x, y, width, height);
            this.info = info;
            this.color = color;
        }

    }

    
    @SidedZenRegister(modDeps = {"jei", "botania"})
    @ZenClass("mods.randomtweaker.jei.IJeiElementManaBar")
    public abstract static class IJeiElementManaBar extends IJeiElement {

        @ZenProperty
        public int mode;

        @ZenProperty
        public int mana;

        protected IJeiElementManaBar(int x, int y, int mode, int mana) {
            super(x, y, 0, 0);
            this.mode = mode;
            this.mana = mana;
        }

        protected IJeiElementManaBar(String elementName, int x, int y, int mode, int mana) {
            super(elementName, x, y, 0, 0);
            this.mode = mode;
            this.mana = mana;
        }

    }

    
    @SidedZenRegister(modDeps = "jei")
    @ZenClass("mods.randomtweaker.jei.IJeiElementImage")
    public abstract static class IJeiElementImage extends IJeiElement {

        @ZenProperty
        public String texture;

        @ZenProperty
        public int textureHeight;

        @ZenProperty
        public int textureWidth;

        protected IJeiElementImage(int u, int v, int x, int y, int width, int height,
                                   String texture, int textureWidth, int textureHeight) {
            super(u, v, x, y, width, height);
            this.texture = texture;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
        }

        protected IJeiElementImage(String elementName, int u, int v, int x, int y, int width, int height,
                                   String texture, int textureWidth, int textureHeight) {
            super(elementName, u, v, x, y, width, height);
            this.texture = texture;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
        }

    }

}
