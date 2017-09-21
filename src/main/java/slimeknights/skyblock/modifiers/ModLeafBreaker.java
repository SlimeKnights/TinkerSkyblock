package slimeknights.skyblock.modifiers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModLeafBreaker extends AbstractModBreaker {

  public ModLeafBreaker() {
    super("leaf_breaker", 0x00c900);
  }

  @Override
  protected boolean checkCondition(IBlockState state, World entityWorld, BlockPos pos) {
    return state.getBlock().isLeaves(state, entityWorld, pos);
  }
}
