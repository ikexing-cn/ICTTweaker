package dev.ikx.rt.impl.mods.jei.impl.elemenet;

import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import net.minecraft.client.Minecraft;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.tile.mana.TilePool;

public class MCJeiElementManaBar extends IJeiElements.IJeiElementManaBar {

    public int multiplesLog = 0;
    public int manaMax = TilePool.MAX_MANA_DILLUTED;

    public MCJeiElementManaBar(int x, int y, int mode, int mana) {
        super(x, y, mode, mana);
    }

    public MCJeiElementManaBar(String elementName, int x, int y, int mode, int mana) {
        super(elementName, x, y, mode, mana);
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
