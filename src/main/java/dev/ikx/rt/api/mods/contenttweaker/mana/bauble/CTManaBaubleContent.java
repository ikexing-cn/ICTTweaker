package dev.ikx.rt.api.mods.contenttweaker.mana.bauble;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemContent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.PlayerHelper;
import vazkii.botania.common.entity.EntityDoppleganger;
import youyihj.zenutils.api.cotx.annotation.ExpandContentTweakerEntry;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author superhelo
 */
@ExpandContentTweakerEntry
public class CTManaBaubleContent extends CTManaItemContent implements IBauble, ICosmeticAttachable, IPhantomInkable, IBaubleRender {

    private static final String TAG_PHANTOM_INK = "phantomInk";
    private static final String TAG_COSMETIC_ITEM = "cosmeticItem";
    private final CTManaBaubleRepresentation manaBauble;

    public CTManaBaubleContent(CTManaBaubleRepresentation manaBauble) {
        super(manaBauble);
        this.manaBauble = manaBauble;
    }

    @ExpandContentTweakerEntry.RepresentationGetter
    public CTManaBaubleRepresentation getRepresentation() {
        return manaBauble;
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) {
        if (Objects.nonNull(this.manaBauble.onPlayerBaubleRender)) {
            Main.proxy.onPlayerBaubleRender(
                this.manaBauble.onPlayerBaubleRender,
                CraftTweakerMC.getIItemStack(stack),
                CraftTweakerMC.getIPlayer(player),
                type.toString(),
                partialTicks
            );
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        if (Objects.nonNull(this.manaBauble.getBaubleType)) {
            return BaubleType.valueOf(this.manaBauble.getBaubleType.call(CraftTweakerMC.getIItemStack(stack)));
        }
        return BaubleType.valueOf(this.manaBauble.baubleType);
    }

    @Override
    public void onWornTick(ItemStack baubleItem, EntityLivingBase wearer) {
        if (Objects.nonNull(this.manaBauble.onWornTick)) {
            this.manaBauble.onWornTick.call(CraftTweakerMC.getIItemStack(baubleItem), CraftTweakerMC.getIEntityLivingBase(wearer));
        }
    }

    @Override
    public void onEquipped(ItemStack baubleItem, EntityLivingBase wearer) {
        if (Objects.nonNull(this.manaBauble.onEquipped)) {
            this.manaBauble.onEquipped.call(CraftTweakerMC.getIItemStack(baubleItem), CraftTweakerMC.getIEntityLivingBase(wearer));
        }
    }

    @Override
    public void onUnequipped(ItemStack baubleItem, EntityLivingBase wearer) {
        if (Objects.nonNull(this.manaBauble.onUnequipped)) {
            this.manaBauble.onUnequipped.call(CraftTweakerMC.getIItemStack(baubleItem), CraftTweakerMC.getIEntityLivingBase(wearer));
        }
    }

    @Override
    public boolean canEquip(ItemStack baubleItem, EntityLivingBase wearer) {
        return Objects.isNull(this.manaBauble.canEquip) || this.manaBauble.canEquip.call(
            CraftTweakerMC.getIItemStack(baubleItem),
            CraftTweakerMC.getIEntityLivingBase(wearer)
        );
    }

    @Override
    public boolean canUnequip(ItemStack baubleItem, EntityLivingBase wearer) {
        return Objects.isNull(this.manaBauble.canUnEquip) || this.manaBauble.canUnEquip.call(
            CraftTweakerMC.getIItemStack(baubleItem),
            CraftTweakerMC.getIEntityLivingBase(wearer)
        );
    }

    @Override
    public boolean willAutoSync(ItemStack baubleItem, EntityLivingBase wearer) {
        return Objects.nonNull(this.manaBauble.willAutoSync) && this.manaBauble.willAutoSync.call(
            CraftTweakerMC.getIItemStack(baubleItem),
            CraftTweakerMC.getIEntityLivingBase(wearer)
        );
    }

    @Override
    public boolean hasPhantomInk(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
    }

    @Override
    public void setPhantomInk(ItemStack stack, boolean ink) {
        ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, ink);
    }

    @Override
    public ItemStack getCosmeticItem(ItemStack stack) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_COSMETIC_ITEM, true);
        if (cmp == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(cmp);
    }

    @Override
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        NBTTagCompound cmp = new NBTTagCompound();
        if (!cosmetic.isEmpty()) {
            cosmetic.writeToNBT(cmp);
        }
        ItemNBTHelper.setCompound(stack, TAG_COSMETIC_ITEM, cmp);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return !getContainerItem(stack).isEmpty();
    }

    @Nonnull
    @Override
    public ItemStack getContainerItem(@Nonnull ItemStack itemStack) {
        return getCosmeticItem(itemStack);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
        ItemStack stack = player.getHeldItem(hand);
        if (!EntityDoppleganger.isTruePlayer(player)) {
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }

        ItemStack toEquip = stack.copy();
        toEquip.setCount(1);

        if (canEquip(toEquip, player)) {
            if (world.isRemote) {
                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            }

            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for (int i = 0; i < baubles.getSlots(); i++) {
                if (baubles.isItemValidForSlot(i, toEquip, player)) {
                    ItemStack stackInSlot = baubles.getStackInSlot(i);
                    IBauble baubleInSlot = stackInSlot.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null);
                    if (stackInSlot.isEmpty() || baubleInSlot == null || baubleInSlot.canUnequip(stackInSlot, player)) {
                        baubles.setStackInSlot(i, ItemStack.EMPTY);

                        baubles.setStackInSlot(i, toEquip);
                        ((IBauble) toEquip.getItem()).onEquipped(toEquip, player);

                        stack.shrink(1);

                        PlayerHelper.grantCriterion((EntityPlayerMP) player, new ResourceLocation("botania", "main/bauble_wear"), "code_triggered");

                        if (!stackInSlot.isEmpty()) {
                            if (baubleInSlot != null) {
                                baubleInSlot.onUnequipped(stackInSlot, player);
                            }

                            if (stack.isEmpty()) {
                                return ActionResult.newResult(EnumActionResult.SUCCESS, stackInSlot);
                            } else {
                                ItemHandlerHelper.giveItemToPlayer(player, stackInSlot);
                            }
                        }

                        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
                    }
                }
            }
        }
        return result;
    }

}