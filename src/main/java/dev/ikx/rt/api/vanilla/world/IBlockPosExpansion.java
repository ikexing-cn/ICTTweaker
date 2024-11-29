package dev.ikx.rt.api.vanilla.world;

import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import net.minecraft.util.math.BlockPos;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenMethodStatic;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.stream.StreamSupport;


@SidedZenRegister
@ZenExpansion("crafttweaker.world.IBlockPos")
@ZenClass("mods.randomtweaker.vanilla.IBlockPos")
public abstract class IBlockPosExpansion {

    @ZenMethodStatic
    public static IBlockPos[] getAllInBox(IBlockPos from, IBlockPos to) {
        Iterable<BlockPos> allInBox = BlockPos.getAllInBox(CraftTweakerMC.getBlockPos(from), CraftTweakerMC.getBlockPos(to));
        return StreamSupport.stream(allInBox.spliterator(), false)
                .map(CraftTweakerMC::getIBlockPos)
                .toArray(IBlockPos[]::new);
    }

    @ZenMethod
    public static IBlockPos add(IBlockPos pos, int x, int y, int z) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).add(x, y, z));
    }

    @ZenMethod
    public static IBlockPos up(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).up(n));
    }

    @ZenMethod
    public static IBlockPos down(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).down(n));
    }

    @ZenMethod
    public static IBlockPos north(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).north(n));
    }

    @ZenMethod
    public static IBlockPos south(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).south(n));
    }

    @ZenMethod
    public static IBlockPos west(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).west(n));
    }

    @ZenMethod
    public static IBlockPos east(IBlockPos pos, @Optional(valueLong = 1) int n) {
        return CraftTweakerMC.getIBlockPos(CraftTweakerMC.getBlockPos(pos).east(n));
    }

}
