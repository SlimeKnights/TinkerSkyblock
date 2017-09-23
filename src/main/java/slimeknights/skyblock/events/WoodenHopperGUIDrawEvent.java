package slimeknights.skyblock.events;

import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import slimeknights.skyblock.TinkerSkyblock;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.tools.common.client.module.GuiGeneric;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class WoodenHopperGUIDrawEvent {

  @SubscribeEvent
  public static void onWoodenHopperDrawGui(GuiScreenEvent.DrawScreenEvent drawScreenEvent) {

    if(drawScreenEvent.getGui() instanceof GuiHopper) {
      GuiHopper gui = (GuiHopper) drawScreenEvent.getGui();
      if(gui.hopperInventory.getSizeInventory() == 1) {
        int x = gui.getGuiLeft() + 61;
        int y = gui.getGuiTop() + 19;

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, .90F);
        gui.mc.getTextureManager().bindTexture(GuiGeneric.LOCATION);
        GuiGeneric.slotEmpty.drawScaledX(x, y, 18*4);
      }
    }
  }

  @SubscribeEvent
  public static void addTooltip(ItemTooltipEvent itemTooltipEvent) {
    if(itemTooltipEvent.getItemStack().getItem() == TinkerSkyblock.itemWoodenHopper) {
      itemTooltipEvent.getToolTip().add(Util.translate("item.tinkerskyblock.wooden_hopper.tooltip"));
    }
  }
}
