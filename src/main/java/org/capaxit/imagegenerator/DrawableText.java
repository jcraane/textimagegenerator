package org.capaxit.imagegenerator;

/**
 * (Immutable) represents text to draw on the given xpos.
 */
public final class DrawableText {
    private final String text;
    private final int xPos;

    public DrawableText(final String text, final int xPos) {
        this.text = text;
        this.xPos = xPos;
    }

    public String getText() {
        return text;
    }

    public int getXPos() {
        return xPos;
    }
}
