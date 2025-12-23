package net.jt.tutorialmod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.jt.tutorialmod.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ModCombatEvents {

    private static final long COOLDOWN_MILLIS = 30L * 60L * 1000L;
    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    public static void register() {

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {

            if (!(source.getAttacker() instanceof ServerPlayerEntity killer)) return;
            if (!(entity instanceof ServerPlayerEntity victim)) return;
            if (!killer.getMainHandStack().isOf(ModItem.BAN_SWORD)) return;

            UUID killerId = killer.getUuid();
            long now = System.currentTimeMillis();

            if (cooldowns.containsKey(killerId)) {
                long last = cooldowns.get(killerId);
                if (now - last < COOLDOWN_MILLIS) {
                    long remaining = (COOLDOWN_MILLIS - (now - last)) / 1000;
                    killer.sendMessage(
                            Text.literal("Ban Sword recharging (" + remaining + "s)")
                                    .formatted(Formatting.GRAY),
                            true
                    );
                    return;
                }
            }

            MinecraftServer server = killer.getServer();
            if (server == null) return;

            String reason = "You were slain by the Ban Sword.";

            server.getPlayerManager().getUserBanList().add(
                    new BannedPlayerEntry(
                            victim.getGameProfile(),
                            new Date(),
                            killer.getName().getString(),
                            null,
                            reason
                    )
            );

            LightningEntity lightning =
                    EntityType.LIGHTNING_BOLT.create(server.getOverworld());

            if (lightning != null) {
                lightning.refreshPositionAfterTeleport(
                        victim.getX(), victim.getY(), victim.getZ()
                );
                server.getOverworld().spawnEntity(lightning);
            }

            ItemStack sword = killer.getMainHandStack();

            if (sword.isDamageable()) {
                sword.damage(
                        sword.getMaxDamage() / 2,
                        Objects.requireNonNull(killer.getServerWorld().getRandomAlivePlayer()),
                        EquipmentSlot.MAINHAND
                );
            }


            server.getPlayerManager().broadcast(
                    Text.literal("☠ ")
                            .formatted(Formatting.DARK_RED)
                            .append(victim.getName())
                            .append(Text.literal(" was BANISHED by "))
                            .append(killer.getName())
                            .formatted(Formatting.RED),
                    false
            );

            victim.networkHandler.disconnect(Text.literal(reason));

            cooldowns.put(killerId, now);
        });
    }
}
