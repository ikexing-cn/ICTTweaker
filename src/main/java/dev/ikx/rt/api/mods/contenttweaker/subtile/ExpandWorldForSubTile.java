package dev.ikx.rt.api.mods.contenttweaker.subtile;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import net.minecraft.tileentity.TileEntity;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.SubTileEntity;


@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenExpansion("crafttweaker.world.IWorld")
public abstract class ExpandWorldForSubTile {

    @ZenMethod
    public static ISubTileEntityInGame getSubTileEntityInGame(IWorld world, IBlockPos pos) {
        TileEntity te = CraftTweakerMC.getWorld(world).getTileEntity(CraftTweakerMC.getBlockPos(pos));
        if (te instanceof ISubTileContainer) {
            SubTileEntity subtile = ((ISubTileContainer) te).getSubTile();
            if (subtile instanceof ISubTileEntityInGame) {
                return (ISubTileEntityInGame) subtile;
            }
        }
        return null;
    }

}
