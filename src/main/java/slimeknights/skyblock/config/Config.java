package slimeknights.skyblock.config;

import java.io.File;

import slimeknights.mantle.config.AbstractConfig;

public class Config extends AbstractConfig {

  public static Config INSTANCE = new Config();

  public ConfigFile configFile;

  public void load(File file) {
    ConfigFile.init();

    configFile = this.load(new ConfigFile(file), ConfigFile.class);
  }

}
