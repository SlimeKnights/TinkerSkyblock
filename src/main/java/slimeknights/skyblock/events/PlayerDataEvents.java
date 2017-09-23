package slimeknights.skyblock.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.function.Supplier;

import slimeknights.skyblock.TinkerSkyblock;
import slimeknights.skyblock.Tools;
import slimeknights.skyblock.config.Config;
import slimeknights.tconstruct.library.utils.TagUtil;

@Mod.EventBusSubscriber
public class PlayerDataEvents {

  public static final String TAG_HAS_PICK = TinkerSkyblock.MODID + ".pick";
  public static final String TAG_HAS_AXE = TinkerSkyblock.MODID + ".axe";
  public static final String TAG_HAS_SCYTHE = TinkerSkyblock.MODID + ".scythe";
  public static final String TAG_HAS_PAN = TinkerSkyblock.MODID + ".pan";
  public static final String TAG_HAS_RATIONS = TinkerSkyblock.MODID + ".rations";

  @SubscribeEvent
  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;

    if(Config.INSTANCE.configFile.enableBaneOfCobble) {
      getStackIfNeeded(player, TAG_HAS_PICK, Tools::buildCobblePick);
    }
    if(Config.INSTANCE.configFile.enableBaneOfLogs) {
      getStackIfNeeded(player, TAG_HAS_AXE, Tools::buildTreeAxe);
    }
    if(Config.INSTANCE.configFile.enableBaneOfLeaves) {
      getStackIfNeeded(player, TAG_HAS_SCYTHE, Tools::buildLeafScythe);
    }
    if(Config.INSTANCE.configFile.enablePersonalSpaceEnforcer) {
      getStackIfNeeded(player, TAG_HAS_PAN, Tools::buildFryPan);
    }
    if(Config.INSTANCE.configFile.enableRations) {
      getStackIfNeeded(player, TAG_HAS_RATIONS, () -> new ItemStack(TinkerSkyblock.itemRations));
    }
  }

  private static void getStackIfNeeded(EntityPlayer player, String tag, Supplier<ItemStack> getStack) {
    NBTTagCompound playerData = player.getEntityData();
    NBTTagCompound data = TagUtil.getTagSafe(playerData, EntityPlayer.PERSISTED_NBT_TAG);

    if(!data.getBoolean(tag)) {
      ItemHandlerHelper.giveItemToPlayer(player, getStack.get());
      data.setBoolean(tag, true);
      playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
    }
  }
}
