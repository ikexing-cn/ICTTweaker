package dev.ikx.rt.impl.internal.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import dev.ikx.rt.api.internal.utils.IInputPattern;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class MCInputPattern implements IInputPattern {

    private final String[] pattern;
    private final Map<Character, IIngredient> mapping = Maps.newHashMap();
    private final Map<Integer, List<IIngredient>> grip = Maps.newLinkedHashMap();

    public MCInputPattern(String... pattern) {
        this.pattern = pattern;
    }

    private IIngredient[][] mapToArray2D() {
        IIngredient[][] toReturn = new IIngredient[grip.size()][];
        for (Map.Entry<Integer, List<IIngredient>> entry : grip.entrySet()) {
            toReturn[entry.getKey()] = entry.getValue().toArray(new IIngredient[0]);
        }
        return toReturn;
    }

    @Override
    public IInputPattern transform(Map<String, IIngredient> mapping) {
        mapping.forEach(this::with);
        return this;
    }

    @Override
    public IInputPattern with(String character, IIngredient ingredient) {
        if (character.length() != 1) {
            CraftTweakerAPI.getLogger().logError("The Mapping key must be one single character",
                    new IllegalArgumentException());
        } else {
            char char_ = character.charAt(0);
            if (Character.isWhitespace(char_)) {
                CraftTweakerAPI.getLogger().logError("The Mapping key must not be a whitespace character",
                        new IllegalArgumentException());
            } else {
                this.mapping.put(char_, ingredient);
            }
        }
        return this;
    }

    @Override
    public IIngredient[][] get() {
        for (int i = 0; i < pattern.length; i++) {
            String rowStr = pattern[i];
            List<IIngredient> row = Lists.newArrayList();
            for (char c : rowStr.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    row.add(null);
                } else {
                    if (mapping.containsKey(c)) {
                        row.add(mapping.get(c));
                    } else {
                        CraftTweakerAPI.getLogger().logWarning(MessageFormat.format(
                                "The character ''{0}'' is not found in the mapping.", c));
                    }
                }
            }
            this.grip.put(i, row);
        }
        return mapToArray2D();
    }

    @Override
    public IIngredient[] getWithShapeless() {
        return get()[0];
    }
}
