package com.crispytwig.sweet_n_savory.block.properties;

import net.minecraft.util.StringRepresentable;

public enum HorizontalConnectionType implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    private HorizontalConnectionType(String type) {
        this.name = type;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}