package slimeknights.skyblock.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import static slimeknights.tconstruct.tools.modifiers.ModReinforced.TAG_UNBREAKABLE;

public class TraitUnbreakable extends AbstractTrait {

  public TraitUnbreakable() {
    super("skyblock_unbreakable", 0x502e83);
  }

  @Override
  public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
    super.applyEffect(rootCompound, modifierTag);

    rootCompound.setBoolean(TAG_UNBREAKABLE, true);
  }

  @Override
  public String getLocalizedName() {
    return Util.translate("modifier.%s.unbreakable", getIdentifier());
  }

  @Override
  public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
    return 0;
  }

  @Override
  public String getLocalizedDesc() {
    return super.getLocalizedDesc();
  }
}
