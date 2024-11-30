package dev.ikx.rt.api.mods.contenttweaker.mana.bauble;

import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleFunction;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleFunctionWithReturn;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleRender;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IGetBaubleType;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemRepresentation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author superhelo
 */

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ManaBauble")
public class CTManaBaubleRepresentation extends CTManaItemRepresentation {

    @ZenProperty
    public String baubleType;
    @ZenProperty
    public IBaubleFunction onWornTick;
    @ZenProperty
    public IBaubleFunction onEquipped;
    @ZenProperty
    public IBaubleFunction onUnequipped;
    @ZenProperty
    public IGetBaubleType getBaubleType;
    @ZenProperty
    public IBaubleRender onPlayerBaubleRender;
    @ZenProperty
    public IBaubleFunctionWithReturn canEquip;
    @ZenProperty
    public IBaubleFunctionWithReturn canUnEquip;
    @ZenProperty
    public IBaubleFunctionWithReturn willAutoSync;

    public CTManaBaubleRepresentation(String unlocalizedName, int maxMana, String baubleType) {
        super(unlocalizedName, maxMana);
        this.setMaxStackSize(1);
        this.setBaubleType(baubleType);
    }

    @ZenMethod
    public String getBaubleType() {
        return baubleType;
    }

    @ZenMethod
    public void setBaubleType(String baubleType) {
        this.baubleType = baubleType;
    }

    @Override
    public String getTypeName() {
        return "ManaBauble";
    }

    @Override
    public void register() {
        if (baubleType.equals("TRINKET")) {
            ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new CTManaTrinketContent(this));
        } else {
            ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new CTManaBaubleContent(this));
        }
    }

}
