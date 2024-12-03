package dev.ikx.rt.impl.mods.botania.module;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import dev.ikx.rt.api.mods.botania.ICocoon;
import dev.ikx.rt.api.mods.contenttweaker.subtile.CTSubTileEntityRepresentation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class BotaniaManager {

    public static final BotaniaManager INSTANCE = new BotaniaManager();

    private final Map<String, ICocoon> cocoonsSpawnMap = Maps.newHashMap();
    private final BiMap<String, Pair<SubtileEntityType, CTSubTileEntityRepresentation>> subTileEntityMap = HashBiMap.create();

    public void registerCocoonSpawn(String name, ICocoon cocoon) {
        cocoonsSpawnMap.put(name, cocoon);
    }

    public ICocoon getCocoonSpawn(String name) {
        return cocoonsSpawnMap.get(name);
    }

    public Map<String, ICocoon> getCocoonsSpawnMap() {
        return cocoonsSpawnMap;
    }

    public void registerSubtileEntity(String unlocalizedName, SubtileEntityType typeName, CTSubTileEntityRepresentation subTileEntity) {
        subTileEntityMap.put(unlocalizedName, Pair.of(typeName, subTileEntity));
    }

    public Pair<SubtileEntityType, CTSubTileEntityRepresentation> getSubTileEntity(String unlocalizedName) {
        return subTileEntityMap.get(unlocalizedName);
    }

    public BiMap<String, Pair<SubtileEntityType, CTSubTileEntityRepresentation>> getSubTileEntityMap() {
        return subTileEntityMap;
    }

    public enum SubtileEntityType {
        FUNCTIONAL,
        GENERATING,
    }

}
