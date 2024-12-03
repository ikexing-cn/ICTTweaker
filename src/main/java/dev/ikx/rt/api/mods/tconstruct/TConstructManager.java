package dev.ikx.rt.api.mods.tconstruct;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TConstructManager {

    public static TConstructManager INSTANCE = new TConstructManager();

    private final List<String> hiddenMaterialList = Lists.newArrayList();
    private final Map<String, Integer> materialPriorityMap = new HashMap<>();
    private final Map<String, ItemStack> materialShowItemMap = new HashMap<>();

    public List<String> getHiddenMaterialList() {
        return hiddenMaterialList;
    }

    public void registerPriority(String material, int priority) {
        materialPriorityMap.put(material, priority);
    }

    public void unregisterPriority(String material, int priority) {
        materialPriorityMap.remove(material, priority);
    }

    public Map<String, Integer> getMaterialPriorityMap() {
        return materialPriorityMap;
    }

    public void registerShowItem(String material, ItemStack item) {
        materialShowItemMap.put(material, item);
    }

    public void unregisterShowItem(String material, ItemStack item) {
        materialShowItemMap.remove(material, item);
    }

    public Map<String, ItemStack> getMaterialShowItemMap() {
        return materialShowItemMap;
    }
}
