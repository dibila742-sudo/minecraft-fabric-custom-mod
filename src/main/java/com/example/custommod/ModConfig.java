package com.example.custommod;

public final class ModConfig {
    public static final ModConfig INSTANCE = new ModConfig();

    public boolean enabled = true;
    public boolean customTimeEnabled = true;
    public int customTime = 6000;
    public boolean customFog = true;
    public float fogScale = 1.0F;
    public boolean useRightClickConfig = true;
}
