package dev.ikx.rt.compact;

import crafttweaker.mc1120.commands.CTChatCommand;
import dev.ikx.rt.impl.mods.crafttweaker.CraftTweakerExtension;
import dev.ikx.rt.impl.mods.crafttweaker.command.AttributeCommand;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class CraftTweakerCompactEvent implements ICompactEvent {
    @Override
    public String modid() {
        return "crafttweaker";
    }

    @Override
    public void onConstruct(FMLConstructionEvent event) {
        CraftTweakerExtension.registerAllClass();
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {
        CTChatCommand.registerCommand(new AttributeCommand());
    }
}
