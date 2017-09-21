package slimeknights.skyblock.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import slimeknights.skyblock.TinkerSkyblock;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.tools.traits.InfiTool;

public class TraitSkyblock extends AbstractTrait {

  public TraitSkyblock() {
    super("skyblock", 0xffffff);
  }

  @Override
  public boolean canApplyTogether(IToolMod otherModifier) {
    return otherModifier == TinkerSkyblock.TRAIT_UNBREAKABLE;
  }

  @Override
  public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
    super.applyEffect(rootCompound, modifierTag);

    ToolNBT stats = TagUtil.getToolStats(rootCompound);
    stats.durability = 1;
    stats.attack = 0f;
    stats.speed = 0f;
    stats.modifiers = 0;
    TagUtil.setToolTag(rootCompound, stats.get());
  }

  @Override
  public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
    return 0f;
  }

  @Override
  public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
    // doesn't take damage at all
    return 0;
  }
}
