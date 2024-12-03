package dev.ikx.rt.impl.internal.clazz.mods.tconstruct;

import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.tconstruct.TConstructManager;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import slimeknights.mantle.client.gui.book.element.ElementItem;
import slimeknights.tconstruct.library.materials.Material;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AbstractMaterialSectionTransformerHooks {

    public static boolean isMaterialInHiddenItems(Material material) {
        return TConstructManager.INSTANCE.getHiddenMaterialList().contains(material.getIdentifier());
    }

    public static boolean isMaterialInShowItemMap(Material material) {
        return TConstructManager.INSTANCE.getMaterialShowItemMap().containsKey(material.getIdentifier());
    }

    public static ElementItem createElementItem(Material material) {
        ItemStack stack = TConstructManager.INSTANCE.getMaterialShowItemMap().get(material.getIdentifier());
        LogManager.getLogger()
                .info("Create ElementItem for {}->{}", material.getIdentifier(), CraftTweakerMC.getIItemStack(stack).toString());
        return new ElementItem(0, 0, 1.0F, stack);
    }

    public static List<Material> sortMaterialList(List<Material> materialList) {
        return materialList.stream()
                .sorted(Comparator.comparing(m -> TConstructManager.INSTANCE.getMaterialPriorityMap().getOrDefault(m.getIdentifier(), 0)))
                .collect(Collectors.toList());

    }

}
