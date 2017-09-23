package slimeknights.skyblock.modifiers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public abstract class AbstractModBreaker extends ModifierTrait {

  public AbstractModBreaker(String identifier, int color) {
    super(identifier, color);
  }

  @Override
  public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
    boolean isPlayer = !(event.getEntityPlayer() == null || event.getEntityPlayer() instanceof FakePlayer);
    if(isPlayer && checkCondition(event.getState(), event.getEntityPlayer().getEntityWorld(), event.getPos())) {
      event.setNewSpeed(20);
    }
    else {
      event.setNewSpeed(0);
    }
    event.getEntityPlayer().getFoodStats().setFoodLevel(9);
    event.getEntityPlayer().getFoodStats().setFoodSaturationLevel(0);
  }

  protected abstract boolean checkCondition(IBlockState state, World entityWorld, BlockPos pos);
}
