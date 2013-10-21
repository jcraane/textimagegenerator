package org.capaxit.imagegenerator;

/**
 * top, left, right and bottom margins in pixels.
 *
 * Immutable.
 */
public final class Margin {
    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;

    public Margin(final int left, final int top) {
        this.left = left;
        this.top = top;
    }

    public Margin(final int left, final int top, final int right, final int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * @return The left margin.
     */
    public int getLeft() {
        return left;
    }

    /**
     * @return The right margin.
     */
    public int getRight() {
        return right;
    }

    /**
     * @return The top margin.
     */
    public int getTop() {
        return top;
    }

    /**
     * @return The bottom margin.
     */
    public int getBottom() {
        return bottom;
    }
}
