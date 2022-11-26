package com.crispytwig.sweet_n_savory.block.properties;

import net.minecraft.util.StringRepresentable;

public enum ShutterType implements StringRepresentable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    NONE("none");

    private final String name;

    private ShutterType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}