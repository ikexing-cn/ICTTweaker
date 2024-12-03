package dev.ikx.rt.impl.mods.contenttweaker.bauble;

import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.CTManaBaubleRepresentation;
import vazkii.botania.api.item.ICosmeticBauble;
import youyihj.zenutils.api.cotx.annotation.ExpandContentTweakerEntry;

/**
 * @author superhelo
 */
@ExpandContentTweakerEntry
public class CTManaTrinketContent extends CTManaBaubleContent implements ICosmeticBauble {

    public CTManaTrinketContent(CTManaBaubleRepresentation manaBauble) {
        super(manaBauble);
    }

}
