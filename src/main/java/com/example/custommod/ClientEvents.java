package com.example.custommod;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ActionResult;

public final class ClientEvents {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.level == null) {
                return;
            }

            if (CustomKeybinds.settingsKey != null && CustomKeybinds.settingsKey.wasPressed()) {
                client.execute(() -> client.setScreen(new CustomSettingsScreen()));
            }

            if (ModConfig.INSTANCE.enabled && ModConfig.INSTANCE.customTimeEnabled) {
                long currentDayTime = client.level.getTimeOfDay();
                long targetDayTime = ModConfig.INSTANCE.customTime;
                if (currentDayTime != targetDayTime) {
                    client.level.setDayTime(targetDayTime);
                }
            }
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!ModConfig.INSTANCE.enabled || !ModConfig.INSTANCE.useRightClickConfig) {
                return ActionResult.PASS;
            }

            if (player.isSneaking() && player.getStackInHand(hand).isEmpty()) {
                Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(new CustomSettingsScreen()));
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });

        WorldRenderEvents.END.register(context -> {
            if (!ModConfig.INSTANCE.enabled || !ModConfig.INSTANCE.customFog) {
                return;
            }

            net.minecraft.client.render.RenderSystem.setShaderFogStart(1.0F);
            net.minecraft.client.render.RenderSystem.setShaderFogEnd(8.0F / Math.max(0.2F, ModConfig.INSTANCE.fogScale));
        });
    }
}
