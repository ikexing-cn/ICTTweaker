package dev.ikx.rt.impl.mods.botania.event;

import baubles.api.BaublesApi;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.api.internal.file.Props;
import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.CTManaBaubleContent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import vazkii.botania.api.BotaniaAPIClient;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CustomBotaniaEvent {

    public static String FLOWER_BLOCK_STATE = "{\n"
            + "  \"forge_marker\": 1,\n"
            + "  \"variants\": {\n"
            + "    \"normal\": [{\n"
            + "      \"model\": \"botania:shapes/cross_tinted\",\n"
            + "      \"textures\": {\n"
            + "        \"cross\": \"contenttweaker:blocks/${name}\"\n"
            + "      }\n"
            + "    }],\n"
            + "    \"inventory\": [{\n"
            + "      \"model\": \"builtin/generated\",\n"
            + "      \"transform\": \"forge:default-item\",\n"
            + "      \"textures\": {\n"
            + "        \"layer0\": \"contenttweaker:blocks/${name}\"\n"
            + "      }\n"
            + "    }]\n"
            + "  }\n"
            + "}";

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Map.Entry<String, Pair<BotaniaManager.SubtileEntityType, ISubTileEntityRepresentation>> entry : BotaniaManager.INSTANCE.getSubTileEntityMap().entrySet()) {
            createFlowerBlockState(entry.getKey());
            BotaniaAPIClient.registerSubtileModel(entry.getKey(), new ModelResourceLocation(ContentTweaker.MOD_ID + ":" + entry.getKey()));
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent evt) {
        if (!evt.getEntityLiving().world.isRemote
                && evt.getEntityLiving() instanceof EntityPlayer
                && !evt.getEntityLiving().world.getGameRules().getBoolean("keepInventory")
                && !((EntityPlayer) evt.getEntityLiving()).isSpectator()) {
            IItemHandler inv = BaublesApi.getBaublesHandler((EntityPlayer) evt.getEntityLiving());
            for (int i = 0; i < inv.getSlots(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty() && stack.getItem() instanceof CTManaBaubleContent) {
                    ((CTManaBaubleContent) stack.getItem()).onUnequipped(stack, evt.getEntityLiving());
                }
            }
        }
    }

    private static void createFlowerBlockState(String name) {
        String nameL = name.toLowerCase();
        String path = Props.getPath(System.getProperty("user.dir"), "resources", "contenttweaker", "blockstates", nameL + ".json");
        File file = new File(path);
        if (!file.exists()) {
            if (nameL.contains("chibi")) {
                nameL = nameL.replace("chibi", "_chibi");
            }
            try {
                FileUtils.writeStringToFile(file, FLOWER_BLOCK_STATE.replace("${name}", nameL), "UTF-8", false);
            } catch (IOException e) {
                CraftTweakerAPI.logError("Failed to create blockstate for " + name, e);
            }
        }
    }

}
