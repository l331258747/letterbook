package com.csxs.core.imageloader.listener;

public final class ImageSize {
    private final static String SEPARATOR = "x";

    private final int width;
    private final int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(width).append(SEPARATOR).append(height).toString();
    }
}
