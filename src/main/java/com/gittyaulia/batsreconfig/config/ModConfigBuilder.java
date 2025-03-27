package com.gittyaulia.batsreconfig.config;

import com.craftjakob.configapi.config.ConfigValueTypes;
import com.craftjakob.configapi.config.IConfigurator;

public class ModConfigBuilder implements IConfigurator {

    public static ConfigValueTypes.IntegerValue batYLevelCap;
    public static ConfigValueTypes.IntegerValue ambientSpawnCap;

    @Override
    public void configure(com.craftjakob.configapi.config.ConfigBuilder builder) {
        batYLevelCap = builder.requiresWorldRestart().defineInRange("batYLevelCap", -32, -64, 320);
        ambientSpawnCap = builder.requiresWorldRestart().defineInRange("ambientSpawnCap", 8, 0, 70);
    }
}