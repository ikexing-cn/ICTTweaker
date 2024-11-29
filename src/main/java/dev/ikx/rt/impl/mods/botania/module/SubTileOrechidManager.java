package dev.ikx.rt.impl.mods.botania.module;

import com.google.common.collect.Lists;
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
        } else {
            oreWeights.put(state, new HashMap<String, Integer>() {{
                put(ore, weight);
            }});
        }

    }

    public static String[] getOres(IBlockState state) {
        return Objects.requireNonNull(Optional.ofNullable(oreWeights.get(state))
                        .map(Map::keySet)
                        .orElse(null))
                .toArray(new String[0]);
    }

    public static List<Integer> delOre(IBlockState state, String oreName) {
        List<Integer> weights = Lists.newArrayList();

        oreWeights.entrySet().stream()
                .filter(entry -> entry.getKey() == state)
                .map(Map.Entry::getValue)
                .filter(map -> map.containsKey(oreName))
                .peek(map -> weights.add(map.get(oreName)))
                .forEach(e -> e.remove(oreName));

        return weights;
    }

    public static boolean checkOreExist(IBlockState state, String oreName) {
        return Arrays.asList(getOres(state)).contains(oreName);
    }

}
