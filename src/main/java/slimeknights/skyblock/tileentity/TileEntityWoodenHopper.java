package slimeknights.skyblock.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.NonNullList;

public class TileEntityWoodenHopper extends TileEntityHopper {

  public TileEntityWoodenHopper() {
    this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
  }

  @Override
  public void setTransferCooldown(int ticks) {
    super.setTransferCooldown(ticks*2);
  }
}
