package dev.ikx.rt.impl.mods.jei.recipe;

import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiRecipe;

public class JEIAttunementRecipe extends MCJeiRecipe {

    private IConstellation constellation;

    public JEIAttunementRecipe(String uid) {
        super(uid);
    }

    public IConstellation getConstellation() {
        return this.constellation;
    }

    public JEIAttunementRecipe setConstellation(IConstellation constellation) {
        this.constellation = constellation;
        return this;
    }
}