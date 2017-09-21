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

  public ConfigFile() {
  }

  public ConfigFile(File configFile) {
    super(configFile);
  }

  @Override
  public void insertDefaults() {
    clearNeedsSaving();
  }

  @Override
  protected int getConfigVersion() {
    return CONFIG_VERSION;
  }
}
