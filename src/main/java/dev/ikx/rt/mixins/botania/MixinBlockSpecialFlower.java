package dev.ikx.rt.mixins.botania;

import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityInGame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;

@Pseudo
@Mixin(value = BlockSpecialFlower.class, remap = false)
public abstract class MixinBlockSpecialFlower {

    @Inject(method = "getExtendedState*", at = @At(value = "HEAD"), cancellable = true)
    private void injectGetSubTileStringMapping(IBlockState state, IBlockAccess world, BlockPos pos, CallbackInfoReturnable<IExtendedBlockState> cir) {
        TileEntity te = world instanceof ChunkCache ? ((ChunkCache) world).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos);
        if (te instanceof TileSpecialFlower && ((TileSpecialFlower) te).getSubTile() != null) {
            Class<? extends SubTileEntity> clazz = ((TileSpecialFlower) te).getSubTile().getClass();
            String id = BotaniaAPI.getSubTileStringMapping(clazz);
            if (StringUtils.isBlank(id) && ISubTileEntityInGame.class.isAssignableFrom(clazz)) {
                id = ((TileSpecialFlower) te).getSubTile().getUnlocalizedName();
            }
            cir.setReturnValue(((IExtendedBlockState) state).withProperty(BotaniaStateProps.SUBTILE_ID, id));
        } else {
            cir.setReturnValue((IExtendedBlockState) state);
        }
    }

}
