package dev.ikx.rt.impl.mods.crafttweaker.command;

import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import crafttweaker.mc1120.commands.CraftTweakerCommand;
import crafttweaker.mc1120.commands.SpecialMessagesChat;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

import static crafttweaker.mc1120.commands.SpecialMessagesChat.getClickableCommandText;
import static crafttweaker.mc1120.commands.SpecialMessagesChat.getNormalMessage;

/**
 * @author youyihj
 */
public class AttributeCommand extends CraftTweakerCommand {
    public AttributeCommand() {
        super("attribute");
    }

    @Override
    protected void init() {
        setDescription(getClickableCommandText("\u00A72/ct attribute", "/ct attribute", true), getNormalMessage(" \u00A73\u00A7bLists all attribute modifiers for the item in your hand"));
    }

    @Override
    public void executeCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            ItemStack item = ((EntityPlayer) sender).getHeldItemMainhand();
            for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
                Multimap<String, AttributeModifier> modifierMultimap = item.getAttributeModifiers(slot);
                if (!modifierMultimap.isEmpty()) {
                    sender.sendMessage(getNormalMessage("In " + slot.getName()));
                    modifierMultimap.asMap().forEach((attribute, modifiers) -> {
                        sender.sendMessage(getCopyMessage(indentationSpace(1) + "Attribute ", attribute));
                        modifiers.forEach(modifier -> {
                            sender.sendMessage(getNormalMessage(indentationSpace(2) + "{"));
                            sender.sendMessage(getCopyMessage(indentationSpace(3) + "Name: ", modifier.getName()));
                            sender.sendMessage(getCopyMessage(indentationSpace(3) + "ID: " , modifier.getID().toString()));
                            sender.sendMessage(getNormalMessage(indentationSpace(3) + "Amount: " + modifier.getAmount()));
                            sender.sendMessage(getNormalMessage(indentationSpace(3) + "Operation: " + modifier.getOperation()));
                            sender.sendMessage(getNormalMessage(indentationSpace(2) + "}"));
                        });
                    });
                }
            }
        }
    }

    private String indentationSpace(int layer) {
        return Strings.repeat(" ", layer * 4);
    }

    private ITextComponent getCopyMessage(String title, String content) {
        return SpecialMessagesChat.getCopyMessage(title + content, content);
    }
}
