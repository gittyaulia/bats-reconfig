package com.gittyaulia.batsreconfig.mixin;

import com.gittyaulia.batsreconfig.config.ModConfigBuilder;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BatEntity.class)
public class BatEntityMixin {

    private static final Logger log = LoggerFactory.getLogger(BatEntityMixin.class);

	/**
	 * Instead of enabling bat spawn below ground only (default),
	 * now enable bat spawn only below a custom Y level
	 *
	 * Note:
	 * - If there is an error, set the Y level cap to Y 62 (sea level)
	 */
	@Redirect( method="canSpawn", at=@At(value="INVOKE", ordinal=1, target="net/minecraft/util/math/BlockPos.getY()I") )
	 static private int spawnAtCustomLevelCap(BlockPos spawnPos) {

		int seaYLevel = 62;
		Integer yLevelCapCustom = ModConfigBuilder.batYLevelCap.getValue();

		if (yLevelCapCustom != null) {

			return yLevelCapCustom;
		} else {
			log.error("Bats y level set as null in config file");
			return seaYLevel;
		}

	}

}

