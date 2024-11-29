package dev.ikx.rt.impl.mods.contenttweaker.aspect;

import dev.ikx.rt.api.mods.contenttweaker.aspect.IAspectRepresentation;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import thaumcraft.api.aspects.Aspect;

public class MCAspectRepresentation extends IAspectRepresentation {

    public MCAspectRepresentation(String tag, int color) {
        this.setTag(tag);
        this.setColor(color);
        this.setImage("contenttweaker:textures/aspects/" + tag.toLowerCase() + ".png");
    }

    @Override
    public void register() {
        try {
            Aspect aspect = new Aspect(this.tag, this.color, this.asAspects(), new ResourceLocation(this.image), this.blend);
            aspect.setChatcolor(this.chatcolor);
        } catch (IllegalArgumentException e) {
            LogManager.getLogger("randomtweaker").error("Registering " + this.tag + " aspect failed");
        }
    }

}
