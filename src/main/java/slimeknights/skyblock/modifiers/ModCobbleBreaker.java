package slimeknights.skyblock.modifiers;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModCobbleBreaker extends AbstractModBreaker {

  public ModCobbleBreaker() {
    super("cobble_breaker", 0x989898);
  }

  @Override
  protected boolean checkCondition(IBlockState state, World entityWorld, BlockPos pos) {
    return state.getMaterial() == Material.ROCK && state.getBlock().getHarvestLevel(state) <= 0;
  }
}
