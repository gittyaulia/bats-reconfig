package com.gittyaulia.batsreconfig.command;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BatsReconfigCommands implements ModInitializer {

    public static final String MOD_ID = "batsreconfig";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        /**
         *  Registers a command called with: /bats
         *
         *  Counts and outputs the number of bats around a 128x128x128 area around the player that executed the command
         *
         *  Note:
         *  - Bats immediately despawn 128 blocks away from the player
         */
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("bats").executes(context -> {

                ServerWorld serverWorld = context.getSource().getWorld();

                Vec3d playerPos = context.getSource().getPosition();
                double playerX = playerPos.getX();
                double playerY = playerPos.getY();
                double playerZ = playerPos.getZ();

                ArrayList<BatEntity> batEntities;
                batEntities = (ArrayList<BatEntity>) serverWorld.getEntitiesByType(TypeFilter.instanceOf(BatEntity.class), EntityPredicates.VALID_ENTITY);

                int batCount = 0;

                //LOGGER.info(String.valueOf(playerPos));

                for (BatEntity bat : batEntities) {

                    double batX = bat.getX();
                    double batY = bat.getY();
                    double batZ = bat.getZ();
                    boolean batWithinNonDespawnAreaOfPlayer = (batX <= playerX+128) && (batY <= playerY+128) && (batZ <= playerZ+128);

                    if (batWithinNonDespawnAreaOfPlayer) {
                        batCount++;
                        LOGGER.info(String.valueOf(batCount) + " " + bat.toString());
                    }
                }

                final int batCountFinal = batCount;

                context.getSource().sendFeedback(() -> Text.literal("There are " + String.valueOf(batCountFinal) + " bats around you."), false);
                return 1;
            }));
        });

    }
}
