package slimeknights.skyblock.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import slimeknights.skyblock.events.PlayerDataEvents;

public class CommandGiveTools extends CommandBase {

  @Override
  public String getName() {
    return "give-skyblock-tools";
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 2;
  }

  @Override
  public String getUsage(ICommandSender sender) {
    return "commands.give-skyblock-tools.usage";
  }

  @Override
  public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    if (args.length < 1)
    {
      throw new WrongUsageException("commands.give-skyblock-tools.usage");
    }
    else
    {
      EntityPlayer entityplayer = getPlayer(server, sender, args[0]);
      PlayerDataEvents.giveItemsToPlayer(entityplayer, true);

      notifyCommandListener(sender, this, "commands.give-skyblock-tools.success", entityplayer.getName());
    }
  }

  @Override
  public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
  {
    if (args.length == 1)
    {
      return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
    }
    return super.getTabCompletions(server, sender, args, targetPos);
  }

  @Override
  public boolean isUsernameIndex(String[] args, int index)
  {
    return index == 0;
  }
}
