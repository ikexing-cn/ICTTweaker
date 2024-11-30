package dev.ikx.rt.impl.mods.contenttweaker.item;

import com.teamacronymcoders.contenttweaker.modules.vanilla.items.ItemContent;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemRepresentation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import youyihj.zenutils.api.cotx.annotation.ExpandContentTweakerEntry;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

/**
 * @author superhelo
 */
@ExpandContentTweakerEntry
public class CTManaItemContent extends ItemContent implements IManaItem, ICreativeManaProvider, IManaTooltipDisplay {

    private static final String TAG_MANA = "mana";
    private static final String TAG_CREATIVE = "creative";
    private static final int DEFAULT_MAX_ITEM_USE_DURATION = 40;
    private final CTManaItemRepresentation manaItem;

    public CTManaItemContent(CTManaItemRepresentation manaItem) {
        super(manaItem);
        this.manaItem = manaItem;
    }

    @ExpandContentTweakerEntry.RepresentationGetter
    public CTManaItemRepresentation getRepresentation() {
        return manaItem;
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    public static void setStackCreative(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, TAG_CREATIVE, true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stacks) {
        if (isInCreativeTab(tab)) {
            stacks.add(new ItemStack(this));

            if (this.manaItem.hasCreative()) {
                ItemStack creative = new ItemStack(this);
                setMana(creative, this.manaItem.getMaxMana());
                setStackCreative(creative);
                stacks.add(creative);
            }

            if (this.manaItem.hasFull()) {
                ItemStack fullPower = new ItemStack(this);
                setMana(fullPower, this.manaItem.getMaxMana());
                stacks.add(fullPower);
            }
        }
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return this.manaItem.getMaxMana();
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        if (!isCreative(stack)) {
            setMana(stack, Math.min(this.getMana(stack) + mana, this.manaItem.getMaxMana()));
        }
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ActionResult<ItemStack> actionResult = super.onItemRightClick(world, player, hand);
        if (actionResult.getType() == EnumActionResult.SUCCESS) {
            player.setActiveHand(hand);
        }
        return actionResult;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return (Objects.nonNull(manaItem.onItemUseFinish) && manaItem.maxItemUseDuration == 0) ? DEFAULT_MAX_ITEM_USE_DURATION : manaItem.maxItemUseDuration;
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return Objects.isNull(this.manaItem.canReceiveManaFromPool) || this.manaItem.canReceiveManaFromPool.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIWorld(pool.getWorld()),
            CraftTweakerMC.getIBlockPos(pool.getPos())
        );
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return !isCreative(stack) && (Objects.isNull(this.manaItem.canReceiveManaFromItem) || this.manaItem.canReceiveManaFromItem.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIItemStack(otherStack)
        ));
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return Objects.isNull(this.manaItem.canExportManaToPool) || this.manaItem.canExportManaToPool.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIWorld(pool.getWorld()),
            CraftTweakerMC.getIBlockPos(pool.getPos()));
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return Objects.isNull(this.manaItem.canExportManaToItem) || this.manaItem.canExportManaToItem.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIItemStack(otherStack)
        );
    }

    @Override
    public int getEntityLifespan(ItemStack stack, World world) {
        return Objects.isNull(this.manaItem.entityLifeSpan) ? super.getEntityLifespan(stack, world) : this.manaItem.entityLifeSpan.call(
            CraftTweakerMC.getIItemStack(stack),
            CraftTweakerMC.getIWorld(world)
        );
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return this.manaItem.isNoExport();
    }

    @Override
    public boolean isCreative(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CREATIVE, false);
    }

    @Override
    public float getManaFractionForDisplay(ItemStack stack) {
        return (float) this.getMana(stack) / (float) this.getMaxMana(stack);
    }

    @Override
    public int getDamage(ItemStack stack) {
        if (super.getDamage(stack) != 0) {
            super.setDamage(stack, 0);
        }
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, World world, List<String> stacks, ITooltipFlag flags) {
        if (this.isCreative(par1ItemStack)) {
            stacks.add(I18n.format("botaniamisc.creative"));
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !isCreative(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - this.getManaFractionForDisplay(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return MathHelper.hsvToRGB(this.getManaFractionForDisplay(stack) / 3.0F, 1.0F, 1.0F);
    }

}