package slimeknights.skyblock.modifiers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModLogBreaker extends AbstractModBreaker {

  public ModLogBreaker() {
    super("log_breaker", 0x93632a);
  }

  @Override
  protected boolean checkCondition(IBlockState state, World entityWorld, BlockPos pos) {
    return state.getBlock().isWood(entityWorld, pos);
  }
}
