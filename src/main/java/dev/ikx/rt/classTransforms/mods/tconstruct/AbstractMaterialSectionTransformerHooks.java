package dev.ikx.rt.classTransforms.mods.tconstruct;

import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.Main;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import slimeknights.mantle.client.gui.book.element.ElementItem;
import slimeknights.tconstruct.library.materials.Material;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AbstractMaterialSectionTransformerHooks {

    public static boolean isMaterialInHiddenItems(Material material) {
        return Main.HIDDEN_MATERIAL_LIST.contains(material.getIdentifier());
    }

    public static boolean isMaterialInShowItemMap(Material material) {
        return Main.MATERIAL_SHOW_ITEM_MAP.containsKey(material.getIdentifier());
    }

    public static ElementItem createElementItem(Material material) {
        ItemStack stack = Main.MATERIAL_SHOW_ITEM_MAP.get(material.getIdentifier());
        LogManager.getLogger()
                  .info("Create ElementItem for " + material.getIdentifier() + "->" + CraftTweakerMC.getIItemStack(stack).toString());
        return new ElementItem(0, 0, 1.0F, stack);
    }

    public static List<Material> sortMaterialList(List<Material> materialList) {
        return materialList.stream()
                           .sorted(Comparator.comparing(m -> Main.MATERIAL_PRIORITY_MAP.getOrDefault(m.getIdentifier(), 0)))
                           .collect(Collectors.toList());

    }

}
