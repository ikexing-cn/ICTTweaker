package dev.ikx.rt.impl.internal.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import dev.ikx.rt.Constant;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = Constant.MODID, name = Constant.MODID)
public class RTConfig {

    public static final Botania Botania = new Botania();
    public static final FTBUltimine FTBUltimine = new FTBUltimine();
    public static final Thaumcraft Thaumcraft = new Thaumcraft();
    public static final RandomTweaker RandomTweaker = new RandomTweaker();
    public static final Astralsorcery Astralsorcery = new Astralsorcery();
    public static final Tconstruct Tconstruct = new Tconstruct();

    public static class Tconstruct {

        @RequiresMcRestart
        @Comment("If true, enable the extension of tconstruct. [default: false]")
        public boolean iconModification = false;

    }

    public static class Astralsorcery {

        @RequiresMcRestart
        @Comment("If true, Modify attunement and add related events. [default: false]")
        public boolean attunementModification = false;

    }

    public static class RandomTweaker {

        @RequiresMcRestart
        @Comment("If true, Allow CrT to manipulate the file \"rt.properties\". [default: false]")
        public boolean Prop = false;

        @RequiresMcRestart
        @Comment({"If true, Allow CrT to modify item attribute modifiers. [default: false]"})
        public boolean itemAttributeModification = false;

    }

    public static class Thaumcraft {

        @Comment("If true, The original DreamJournal will be no longer available. [default: false]")
        public boolean DreamJournal = false;

    }

    public static class Botania {

        @RequiresMcRestart
        @Comment("If true, the hydroangeas will be modified. [default: false]")
        public boolean HydroangeasModified = false;

        @RequiresMcRestart
        @Comment("If true, the hydroangeas will be decay. [Valid only for modified results | default: false]")
        public boolean HydroangeasDecay = false;

        @RequiresMcRestart
        @Comment("How much mana is max of hydroangeas? [Valid only for modified results | default: 18000]")
        public int HydroangeasMaxMana = 18000;

        @RequiresMcRestart
        @Comment("If true, the Orechid will be modified. [default: false]")
        public boolean OrechidModified = false;

        @RequiresMcRestart
        @Comment("How much the interval of each work of Orechid.[Valid only for modified results | default: 20]")
        public int OrechidDelay = 20;

        @RequiresMcRestart
        @Comment("How much mana is consumed per work of Orechid.[Valid only for modified results | default: 700]")
        public int OrechidCost = 700;

        @RequiresMcRestart
        @Comment("How much mana is max of Orechid? [Valid only for modified results | default: 18000]")
        public int OrechidMaxMana = 18000;

        @RequiresMcRestart
        @Comment("If true, the Orechid will keep the Botania recipes. [Valid only for modified results | default: true]")
        public boolean OrechidHasDefault = true;

    }

    public static class FTBUltimine {

        @RequiresMcRestart
        @Comment("If true, Allow Crt to Control the FTBUltimine enable")
        public boolean AllowCrTControl = false;

    }


    static {
        ConfigAnytime.register(RTConfig.class);
    }

}
