package com.example.custommod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public final class CustomSettingsScreen extends Screen {
    private final ModConfig config;

    public CustomSettingsScreen() {
        super(Text.literal("Custom Settings"));
        this.config = ModConfig.INSTANCE;
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int y = this.height / 2 - 80;

        this.addDrawableChild(
                Button.builder(Text.literal("Toggle Mod: " + (config.enabled ? "ON" : "OFF")), button -> {
                    config.enabled = !config.enabled;
                    button.setMessage(Text.literal("Toggle Mod: " + (config.enabled ? "ON" : "OFF")));
                }).dimensions(centerX - 100, y, 200, 20).build()
        );

        this.addDrawableChild(
                Button.builder(Text.literal("Custom Time: " + config.customTime), button -> {
                    config.customTime = (config.customTime + 1000) % 24000;
                    button.setMessage(Text.literal("Custom Time: " + config.customTime));
                }).dimensions(centerX - 100, y + 30, 200, 20).build()
        );

        this.addDrawableChild(
                Button.builder(Text.literal("Fog: " + (config.customFog ? "ON" : "OFF")), button -> {
                    config.customFog = !config.customFog;
                    button.setMessage(Text.literal("Fog: " + (config.customFog ? "ON" : "OFF")));
                }).dimensions(centerX - 100, y + 60, 200, 20).build()
        );

        this.addDrawableChild(
                Button.builder(Text.literal("Fog Scale: " + config.fogScale), button -> {
                    config.fogScale = Math.round((config.fogScale + 0.25F) * 100.0F) / 100.0F;
                    if (config.fogScale > 2.5F) {
                        config.fogScale = 0.5F;
                    }
                    button.setMessage(Text.literal("Fog Scale: " + config.fogScale));
                }).dimensions(centerX - 100, y + 90, 200, 20).build()
        );

        this.addDrawableChild(
                Button.builder(Text.literal("Right Click: " + (config.useRightClickConfig ? "ON" : "OFF")), button -> {
                    config.useRightClickConfig = !config.useRightClickConfig;
                    button.setMessage(Text.literal("Right Click: " + (config.useRightClickConfig ? "ON" : "OFF")));
                }).dimensions(centerX - 100, y + 120, 200, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float deltaTicks) {
        this.renderBackground(graphics, mouseX, mouseY, deltaTicks);

        int panelX = this.width / 2 - 180;
        int panelY = this.height / 2 - 120;
        int panelW = 360;
        int panelH = 220;
        drawRoundedRect(graphics, panelX, panelY, panelW, panelH, 22, 0xAA101010);
        drawRoundedRect(graphics, panelX + 10, panelY + 10, panelW - 20, panelH - 20, 18, 0xAA1B1B1B);

        graphics.drawCenteredString(this.textRenderer, "Custom Settings", this.width / 2, panelY + 18, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, deltaTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void drawRoundedRect(GuiGraphics graphics, int x, int y, int width, int height, int radius, int color) {
        if (radius <= 0) {
            graphics.fill(x, y, x + width, y + height, color);
            return;
        }

        graphics.fill(x + radius, y, x + width - radius, y + height, color);
        graphics.fill(x, y + radius, x + width, y + height - radius, color);

        for (int dy = 0; dy <= radius; dy++) {
            for (int dx = 0; dx <= radius; dx++) {
                if ((dx * dx) + (dy * dy) <= (radius * radius)) {
                    graphics.fill(x + radius - dx, y + radius - dy, x + radius - dx + 1, y + radius - dy + 1, color);
                    graphics.fill(x + width - radius + dx, y + radius - dy, x + width - radius + dx + 1, y + radius - dy + 1, color);
                    graphics.fill(x + radius - dx, y + height - radius + dy, x + radius - dx + 1, y + height - radius + dy + 1, color);
                    graphics.fill(x + width - radius + dx, y + height - radius + dy, x + width - radius + dx + 1, y + height - radius + dy + 1, color);
                }
            }
        }
    }
}
