package slimeknights.skyblock.modifiers;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

@Mod.EventBusSubscriber
public class ModCobbleBreaker extends AbstractModBreaker {

  public static final String IDENTIFIER = "cobble_breaker";

  public ModCobbleBreaker() {
    super(IDENTIFIER, 0x989898);
  }

  @Override
  protected boolean checkCondition(IBlockState state, World entityWorld, BlockPos pos) {
    return state.getMaterial() == Material.ROCK && state.getBlock().getHarvestLevel(state) <= 0;
  }

  @SubscribeEvent
  public static void onBlockBreak(BlockEvent.BreakEvent breakEvent) {
    EntityPlayer player = breakEvent.getPlayer();
    if(player == null || breakEvent.getState().getBlock() != Blocks.COBBLESTONE) {
      return;
    }

    ItemStack itemStack = player.getHeldItemMainhand();
    if(!TinkerUtil.hasModifier(TagUtil.getTagSafe(itemStack), IDENTIFIER)) {
      return;
    }

    if(!breakEvent.getWorld().isRemote && isCobbleGen(breakEvent.getWorld(), breakEvent.getPos())) {
      breakEvent.setCanceled(true);
      ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Blocks.COBBLESTONE));
    }
  }

  private static boolean isCobbleGen(World world, BlockPos pos) {
    boolean hasWater = false;
    boolean hasLava = false;

    for(EnumFacing enumFacing : EnumFacing.values()) {
      IBlockState adjacentState = world.getBlockState(pos.offset(enumFacing));
      hasWater |= adjacentState.getBlock() == Blocks.WATER;
      hasWater |= adjacentState.getBlock() == Blocks.FLOWING_WATER;
      hasLava |= adjacentState.getBlock() == Blocks.LAVA;
      hasLava |= adjacentState.getBlock() == Blocks.FLOWING_LAVA;
    }

    return hasLava && hasWater;
  }
}
