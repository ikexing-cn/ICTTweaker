package dev.ikx.rt.impl.internal.core;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * @author ikexing
 */
@IFMLLoadingPlugin.Name("RandomTweaker CoreMod Boot")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class CoreModBoot implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                "dev.ikx.rt.impl.internal.clazz.RandomTweakerClassTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}