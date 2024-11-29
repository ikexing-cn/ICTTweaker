package dev.ikx.rt.impl.mods.botania.module;

import net.minecraft.block.state.IBlockState;

import java.util.*;

public class SubTileOrechidManager {

    public static final Map<IBlockState, Map<String, Integer>> oreWeights = new HashMap<>();

    public static void addOreWeight(IBlockState state, String ore, Integer weight) {
        if (oreWeights.containsKey(state)) {
            Map<String, Integer> map = new HashMap<>();
            map.put(ore, weight);
            Objects.requireNonNull(map).putAll(oreWeights.get(state));
            oreWeights.put(state, map);
        } else
            oreWeights.put(state, new HashMap<String, Integer>() {{
                put(ore, weight);
            }});
    }

    public static String[] getOres(IBlockState state) {
        return Objects.requireNonNull(Optional.ofNullable(oreWeights.get(state))
                        .map(Map::keySet)
                        .orElse(null))
                .toArray(new String[0]);
    }

    public static void delOre(IBlockState state, String oreName) {
        oreWeights.entrySet().stream()
                .filter(entry -> entry.getKey() == state)
                .map(Map.Entry::getValue)
                .filter(map -> map.containsKey(oreName))
                .forEach(e -> e.remove(oreName));
    }

    public static boolean checkOreExist(IBlockState state, String oreName) {
        return Arrays.asList(getOres(state)).contains(oreName);
    }

}
