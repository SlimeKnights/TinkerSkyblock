package slimeknights.skyblock;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.minecraftforge.fml.common.Mod.EventHandler;


@Mod(modid = TinkerSkyblock.MODID,
    version = TinkerSkyblock.VERSION,
    name = "Tinkers' Skyblock",
    dependencies = "required-after:forge@[14.21.0,);"
                   + "required-after:mantle@[1.12-1.3.1,);"
                   + "required-after:tconstruct@[1.12-2.7.2.19,)",
    acceptedMinecraftVersions = "[1.12, 1.13)"
)
public class TinkerSkyblock {

  public static final String MODID = "tinkerskyblock";
  public static final String VERSION = "${version}";

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {

  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {

  }
}
