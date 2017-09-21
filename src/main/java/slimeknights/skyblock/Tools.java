package slimeknights.skyblock;

import com.google.common.collect.ImmutableList;

import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.stream.IntStream;

import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.harvest.TinkerHarvestTools;
import slimeknights.tconstruct.tools.melee.TinkerMeleeWeapons;

import static slimeknights.skyblock.TinkerSkyblock.MATERIAL_SKYBLOCK;
import static slimeknights.skyblock.TinkerSkyblock.MATERIAL_SKYBLOCK2;

public final class Tools {

  public static ItemStack buildCobblePick() {
    ItemStack itemStack = TinkerHarvestTools.pickaxe.buildItem(ImmutableList.of(MATERIAL_SKYBLOCK2, MATERIAL_SKYBLOCK, MATERIAL_SKYBLOCK));
    TinkerSkyblock.MOD_COBBLE_BREAKER.apply(itemStack);
    IntStream.range(0, 5).forEach(i -> TinkerModifiers.modReinforced.apply(itemStack));
    TinkerModifiers.modSoulbound.apply(itemStack);

    itemStack.setTranslatableName("item.tinkerskyblock.pickaxe");

    return itemStack;
  }

  public static ItemStack buildTreeAxe() {
    ItemStack itemStack = TinkerHarvestTools.lumberAxe.buildItem(ImmutableList.of(MATERIAL_SKYBLOCK2, MATERIAL_SKYBLOCK, MATERIAL_SKYBLOCK, MATERIAL_SKYBLOCK));
    TinkerSkyblock.MOD_LOG_BREAKER.apply(itemStack);
    IntStream.range(0, 5).forEach(i -> TinkerModifiers.modReinforced.apply(itemStack));
    TinkerModifiers.modSoulbound.apply(itemStack);

    itemStack.setTranslatableName("item.tinkerskyblock.axe");

    return itemStack;
  }

  public static ItemStack buildLeafScythe() {
    ItemStack itemStack = TinkerHarvestTools.scythe.buildItem(ImmutableList.of(MATERIAL_SKYBLOCK2, MATERIAL_SKYBLOCK, MATERIAL_SKYBLOCK, MATERIAL_SKYBLOCK2));
    TinkerSkyblock.MOD_LEAF_BREAKER.apply(itemStack);
    IntStream.range(0, 5).forEach(i -> TinkerModifiers.modReinforced.apply(itemStack));
    TinkerModifiers.modSoulbound.apply(itemStack);
    TinkerModifiers.modHarvestWidth.apply(itemStack);
    TinkerModifiers.modHarvestHeight.apply(itemStack);

    NBTTagCompound tagCompound = TagUtil.getTagSafe(itemStack);
    ToolBuilder.addEnchantment(tagCompound, Enchantments.SILK_TOUCH);
    itemStack.setTagCompound(tagCompound);

    itemStack.setTranslatableName("item.tinkerskyblock.scythe");


    return itemStack;
  }

  public static ItemStack buildFryPan() {
    ItemStack itemStack = TinkerMeleeWeapons.fryPan.buildItem(ImmutableList.of(MATERIAL_SKYBLOCK, TinkerMaterials.sponge));
    IntStream.range(0, 100).forEach(i -> TinkerModifiers.modKnockback.apply(itemStack));
    IntStream.range(0, 5).forEach(i -> TinkerModifiers.modReinforced.apply(itemStack));
    TinkerModifiers.modSoulbound.apply(itemStack);

    itemStack.setTranslatableName("item.tinkerskyblock.frypan");

    return itemStack;
  }

  private Tools() {}
}
