package slimeknights.skyblock.config;

import java.io.File;

import slimeknights.mantle.config.AbstractConfigFile;
import slimeknights.mantle.configurate.objectmapping.Setting;
import slimeknights.mantle.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfigFile extends AbstractConfigFile {

  private final static int CONFIG_VERSION = 1;

  @Setting(comment = "Gives the player an indestructible Pickaxe that can only harvest stone")
  public boolean enableBaneOfCobble = true;

  @Setting(comment = "Gives the player an indestructible Scythe that can only harvest leaves")
  public boolean enableBaneOfLeaves = true;

  @Setting(comment = "Gives the player an indestructible Lumberaxe that can only harvest tree logs")
  public boolean enableBaneOfLogs = true;

  @Setting(comment = "Gives the player an indestructible Frying Pan with large knockback that deals no damage")
  public boolean enablePersonalSpaceEnforcer = true;

  @Setting(comment = "Gives the player a small edible item with higher food efficiency, that can be restocked with any food")
  public boolean enableRations = true;

  public ConfigFile() {
  }

  public ConfigFile(File configFile) {
    super(configFile);
  }

  @Override
  public void insertDefaults() {

  }

  @Override
  protected int getConfigVersion() {
    return CONFIG_VERSION;
  }
}
