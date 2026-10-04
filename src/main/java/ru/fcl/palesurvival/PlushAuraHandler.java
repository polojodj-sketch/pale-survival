package ru.fcl.palesurvival;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import ru.fcl.palesurvival.block.ModBlocks;

import java.util.Optional;

public class PlushAuraHandler {
    private static final int RADIUS = 6;
    private static final int CHECK_EVERY_TICKS = 100;
    private static int counter = 0;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (++counter < CHECK_EVERY_TICKS) {
                return;
            }
            counter = 0;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (player.getHealth() >= player.getMaxHealth()) {
                    continue;
                }
                if (player.hasStatusEffect(StatusEffects.REGENERATION)) {
                    continue;
                }
                BlockPos pos = player.getBlockPos();
                Optional<BlockPos> found = BlockPos.findClosest(pos, RADIUS, RADIUS,
                        near -> player.getWorld().getBlockState(near).isOf(ModBlocks.CREAKING_PLUSH));
                if (found.isPresent()) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0));
                }
            }
        });
    }
}
