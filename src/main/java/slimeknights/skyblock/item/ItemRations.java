package slimeknights.skyblock.item;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import slimeknights.tconstruct.common.ClientProxy;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.tinkering.IRepairable;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class ItemRations extends ItemFood implements IRepairable {

  public static final String LOC_USES = "stat.spaghetti.uses.name";

  private static final int FOOD_AMOUNT = 2;
  private static final float SATURATION = 0.5f;

  public ItemRations() {
    super(FOOD_AMOUNT, SATURATION, false);

    this.setMaxDamage(50);
    this.setMaxStackSize(1);
    this.setNoRepair();
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    if(playerIn.getFoodStats().getFoodLevel() >= 18 - FOOD_AMOUNT) {
      return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
    return super.onItemRightClick(worldIn, playerIn, handIn);
  }

  @Override
  @Nonnull
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    stack.setItemDamage(stack.getItemDamage() + 1);

    if(entityLiving instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer) entityLiving;
      entityplayer.getFoodStats().addStats(this, stack);
      worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
      StatBase statBase = StatList.getObjectUseStats(this);
      assert statBase != null;
      entityplayer.addStat(statBase);
    }

    return stack;
  }

  /**
   * How long it takes to use or consume an item
   */
  @Override
  public int getMaxItemUseDuration(ItemStack stack) {
    return 10;
  }

  /**
   * returns the action that specifies what animation to play when the items is being used
   */
  @Nonnull
  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.EAT;
  }

  @Nonnull
  @Override
  public ItemStack repair(ItemStack repairable, NonNullList<ItemStack> repairItems) {
    if(repairable.getItemDamage() == 0) {
      // nothing to repair, full durability
      return ItemStack.EMPTY;
    }

    if(repairItems.stream().filter(stack -> !stack.isEmpty()).anyMatch(this::isNoFood)) {
      return ItemStack.EMPTY;
    }

    ItemStack stack = repairable.copy();
    boolean repaired = false;
    int index = 0;
    while(stack.getItemDamage() > 0 && index < repairItems.size()) {
      ItemStack repairItem = repairItems.get(index);
      if(!repairItem.isEmpty() && repairItem.getCount() > 0) {

        int change = ((ItemFood)repairItem.getItem()).getHealAmount(repairItem)/2;
        change = Math.max(1, change);
        stack.setItemDamage(stack.getItemDamage() - change);

        ToolHelper.healTool(stack, change, null);
        repairItem.shrink(1);
        repaired = true;
      }
      else {
        index++;
      }
    }

    if(!repaired) {
      return ItemStack.EMPTY;
    }

    return stack;
  }

  public int getUses(ItemStack stack) {
    return (stack.getMaxDamage() - stack.getItemDamage());
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    tooltip.add(String.format("%s: %s", Util.translate(LOC_USES),
                              CustomFontColor.formatPartialAmount(getUses(stack), getMaxDamage(stack))));
    tooltip.add(Util.translate("item.tinkerskyblock.rations.tooltip1"));
    tooltip.add(Util.translate("item.tinkerskyblock.rations.tooltip2"));
    tooltip.add(Util.translate("item.tinkerskyblock.rations.tooltip3"));
    tooltip.add(Util.translate("item.tinkerskyblock.rations.tooltip4"));
  }

  @Nonnull
  @SideOnly(Side.CLIENT)
  @Override
  public FontRenderer getFontRenderer(ItemStack stack) {
    return ClientProxy.fontRenderer;
  }

  private boolean isNoFood(ItemStack itemStack) {
    return !(itemStack.getItem() instanceof ItemFood) || itemStack.getItem() == Items.ROTTEN_FLESH;
  }
}
