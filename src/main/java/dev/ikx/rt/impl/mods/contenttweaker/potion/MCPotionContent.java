package dev.ikx.rt.impl.mods.contenttweaker.potion;

import com.teamacronymcoders.contenttweaker.ContentTweaker;
import com.teamacronymcoders.contenttweaker.api.ctobjects.entity.EntityHelper;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionRepresentation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class MCPotionContent extends Potion {

    public final IPotionRepresentation potionRepresentation;
    public ResourceLocation textureResourceLocation;

    protected MCPotionContent(IPotionRepresentation potionRepresentation) {
        super(potionRepresentation.badEffectIn, potionRepresentation.liquidColor);
        this.potionRepresentation = potionRepresentation;
        setField();
    }

    private void setField() {
        this.textureResourceLocation = new ResourceLocation(ContentTweaker.MOD_ID,
                "textures/gui/" + potionRepresentation.unlocalizedName + ".png");
        this.setPotionName("effect." + ContentTweaker.MOD_ID + "." + potionRepresentation.unlocalizedName);
        this.setRegistryName(ContentTweaker.MOD_ID, potionRepresentation.unlocalizedName);
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    public boolean isInstant() {
        return potionRepresentation.instant;
    }

    @Override
    public boolean isBeneficial() {
        return potionRepresentation.beneficial;
    }

    @Override
    public boolean isBadEffect() {
        return potionRepresentation.badEffectIn;
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return potionRepresentation.shouldRender;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return potionRepresentation.shouldRenderHUD;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(textureResourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(textureResourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (Objects.nonNull(potionRepresentation.isReady)) {
            return potionRepresentation.isReady.call(duration, amplifier);
        } else {
            return Objects.nonNull(potionRepresentation.performEffect);
        }
    }

    @Override
    public void performEffect(EntityLivingBase living, int amplifier) {
        if (Objects.nonNull(potionRepresentation.performEffect)) {
            potionRepresentation.performEffect.call(EntityHelper.getIEntityLivingBase(living), amplifier);
        }
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase living, int amplifier, double health) {
        if (Objects.nonNull(potionRepresentation.affectEntity)) {
            potionRepresentation.affectEntity.call(EntityHelper.getIEntityLivingBase(living), amplifier);
        }
    }


}
