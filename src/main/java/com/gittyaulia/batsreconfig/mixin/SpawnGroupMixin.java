package com.gittyaulia.batsreconfig.mixin;

import com.gittyaulia.batsreconfig.config.ModConfigBuilder;
import net.minecraft.entity.SpawnGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(SpawnGroup.class)
public class SpawnGroupMixin {

    private static final Logger log = LoggerFactory.getLogger(SpawnGroupMixin.class);

    @Shadow
    int capacity;

    @Shadow
    String name;

    /**
     * Overwrites the cap for ambient entities
     * @author gittyaulia
     * @reason SpawnGroup.getCapacity does not return a variable but directly, an object's attribute
     *
     * Note:
     * - The only mobs classified as "ambient" are bats.
     */
    @Overwrite
    public int getCapacity() {

        int ambientSpawnCapacityDefault = 15;
        Integer ambientSpawnCapacityCustom = ModConfigBuilder.ambientSpawnCap.getValue();

        int spawnGroupCapacity = this.capacity;

        if (!this.name.equals("ambient")) {

            return spawnGroupCapacity;

        } else {

            return Objects.requireNonNullElse(ambientSpawnCapacityCustom, ambientSpawnCapacityDefault);
        }

    }
}
