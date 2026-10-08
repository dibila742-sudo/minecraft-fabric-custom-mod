package com.example.custommod;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class CustomKeybinds {
    public static KeyBinding settingsKey;

    public static void register() {
        settingsKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.custommod.settings", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.category.custommod")
        );
    }
}
