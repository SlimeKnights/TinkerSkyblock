package slimeknights.skyblock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import slimeknights.mantle.network.NetworkWrapper;
import slimeknights.skyblock.command.CommandGiveTools;
import slimeknights.skyblock.config.Config;
import slimeknights.skyblock.config.ConfigSyncPacket;
import slimeknights.skyblock.item.ItemRations;
import slimeknights.skyblock.modifiers.ModCobbleBreaker;
import slimeknights.skyblock.modifiers.ModLeafBreaker;
import slimeknights.skyblock.modifiers.ModLogBreaker;
import slimeknights.skyblock.traits.TraitSkyblock;
import slimeknights.skyblock.traits.TraitUnbreakable;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

import static net.minecraftforge.fml.common.Mod.EventHandler;
import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;


@Mod(modid = TinkerSkyblock.MODID,
    version = TinkerSkyblock.VERSION,
    name = "Tinkers' Skyblock",
    dependencies = "required-after:forge@[14.21.0,);"
                   + "required-after:mantle@[1.12-1.3.1,);"
                   + "required-after:tconstruct@[1.12.2-2.11.0.+,)",
    acceptedMinecraftVersions = "[1.12, 1.13)"
)
@Mod.EventBusSubscriber
public class TinkerSkyblock {

  public static final String MODID = "tinkerskyblock";
  public static final String VERSION = "${version}";

  public static NetworkWrapper networkWrapper;

  public static final Material MATERIAL_SKYBLOCK = new Material("skyblock", 0xc845ab, true);
  public static final Material MATERIAL_SKYBLOCK2 = new Material("skyblock2", 0xc845ab, true);

  public static final TraitSkyblock TRAIT_SKYBLOCK = new TraitSkyblock();
  public static final TraitUnbreakable TRAIT_UNBREAKABLE = new TraitUnbreakable();

  public static final ModCobbleBreaker MOD_COBBLE_BREAKER = new ModCobbleBreaker();
  public static final ModLeafBreaker MOD_LEAF_BREAKER = new ModLeafBreaker();
  public static final ModLogBreaker MOD_LOG_BREAKER = new ModLogBreaker();

  public static final ItemRations itemRations = new ItemRations();

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    Config.INSTANCE.load(new File(event.getModConfigurationDirectory(), "TinkerSkyblock.cfg"));

    TinkerRegistry.addMaterial(MATERIAL_SKYBLOCK);
    TinkerRegistry.addMaterialStats(MATERIAL_SKYBLOCK,
                                    new HeadMaterialStats(1, 0f, 1f, STONE),
                                    new HandleMaterialStats(1f, 0),
                                    new ExtraMaterialStats(0));

    TinkerRegistry.addMaterial(MATERIAL_SKYBLOCK2);
    TinkerRegistry.addMaterialStats(MATERIAL_SKYBLOCK2,
                                    new HeadMaterialStats(1, 0f, 1f, STONE),
                                    new HandleMaterialStats(1f, 0),
                                    new ExtraMaterialStats(0));

    MATERIAL_SKYBLOCK.addTrait(TRAIT_SKYBLOCK);


    networkWrapper = new NetworkWrapper(MODID + ":sync");
    networkWrapper.registerPacketClient(ConfigSyncPacket.class);

    Config.INSTANCE.save();
  }

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
  }

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    itemRations.setRegistryName(MODID, "rations");
    itemRations.setUnlocalizedName(MODID + ".rations");
    event.getRegistry().register(itemRations);
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void registerModels(ModelRegistryEvent modelRegistryEvent) {
    ModelLoader.setCustomModelResourceLocation(itemRations, 0, new ModelResourceLocation(itemRations.getRegistryName(), "inventory"));
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
  }

  @EventHandler
  public void onServerStart(FMLServerStartingEvent event) {
    event.registerServerCommand(new CommandGiveTools());
  }
}
