package org.capaxit.imagegenerator.impl;

import org.capaxit.imagegenerator.*;
import org.capaxit.imagegenerator.textalign.*;
import org.capaxit.imagegenerator.util.Validate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link org.capaxit.imagegenerator.TextImage} interface. The default font is
 * SansSerif, PLAIN, 12. The default color is black. Wrapping of text at the
 * right margin defaults to false.
 * The {@link GreedyTextWrapper} is used as the default text wrapping algorithm.
 * <p/>
 * Note: At the moment text wrapping is only implemented for the write methods, not the writeLine methods.
 * The default text aligment is Alignment.LEFT.
 * <p/>
 * This class is NOT threadsafe.
 */
public final class TextImageImpl implements TextImage {
    private static final String JPEG = "jpeg";
    private static final int MAX_COMPRESSION = 1;
    private static final String PNG = "png";
    private final int width;

    private final int height;

    private int xPos = 0;

    private int yPos = 0;

    private Style style = Style.PLAIN;

    private Alignment alignment = Alignment.LEFT;

    private Margin margin = new Margin(0, 0, 0, 0);

    private boolean wrap = false;

    private TextWrapper wrapper = new GreedyTextWrapper();

    private final BufferedImage image;

    private final Graphics2D graphics;

    private Font previouslyUsedFont = new Font("SansSerif", Font.PLAIN, 12);

    private Color previouslyUsedColor = Color.BLACK;

    // Map containing the aligment algorithms
    private final Map<Alignment, Align> alignments;

    {
        alignments = new HashMap<Alignment, Align>();
        alignments.put(Alignment.LEFT, new LeftAlign());
        alignments.put(Alignment.RIGHT, new RightAlign());
        alignments.put(Alignment.CENTER, new Center());
        alignments.put(Alignment.JUSTIFY, new Justify());
    }

    /**
     * {@inheritDoc}
     */
    public TextImageImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();

        graphics.setBackground(new Color(255, 255, 255));
        graphics.setColor(new Color(0, 0, 0));

        graphics.clearRect(0, 0, width, height);
    }

    /**
     * {@inheritDoc}
     */
    public TextImageImpl(final int width, final int height, final Margin margin) {
        this(width, height);

        this.margin = Validate.notNull(margin, "The margin may not be null.");

        this.yPos = this.margin.getTop();
        this.xPos = this.margin.getLeft();
    }

    /**
     * {@inheritDoc}
     */
    public TextImage useTextWrapper(final TextWrapper wrapper) {
        this.wrapper = Validate.notNull(wrapper, "The wrapper may not be null.");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage performAction(final TextImageCallback callback) {
        Validate.notNull(callback, "The callback may not be null.");

        callback.doWithGraphics(this.graphics);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage addHSpace(final int space) {
        this.xPos += space;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage addVSpace(final int space) {
        this.yPos += space;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage wrap(final boolean enable) {
        this.wrap = enable;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage write(final String text) {
        Validate.notNull(text, "The text may not be null.");

        FontMetrics fm = getFontMetrics();
        if (this.wrap) {
            int lineWidth = this.width - this.margin.getLeft() - this.margin.getRight();
            List<String> lines = this.wrapper.doWrap(text, lineWidth, fm);
            for (String line : lines) {
                this.writeText(fm, line);
                this.applyStyle(fm, line);
                this.newLine();
            }
        } else {
            this.writeText(fm, text);
            this.applyStyle(fm, text);
        }

        return this;
    }

    private void applyStyle(final FontMetrics fm, final String line) {
        if (this.style.equals(Style.UNDERLINED)) {
            int y = this.yPos + fm.getAscent();
            this.graphics.drawLine(this.xPos - fm.stringWidth(line), y, this.xPos, y);
        }
    }

    /**
     * Writes the given text on the Graphics object.
     *
     * @param fm   FontMetrics used to calculate the width of the text based on
     *             the font.
     * @param text The text to draw.
     */
    private void writeText(final FontMetrics fm, final String text) {
        int linewidth = this.width - this.margin.getLeft() - this.margin.getRight();
        List<DrawableText> words = this.alignments.get(this.alignment).align(text, fm, linewidth);
        for (DrawableText word : words) {
            graphics.drawString(word.getText(), this.xPos + word.getXPos(), this.yPos + this.getFontMetrics().getAscent() - this.getFontMetrics().getDescent());
        }
        this.xPos = this.xPos + fm.stringWidth(text);
    }

    /**
     * {@inheritDoc}
     */
    public TextImage write(final String text, final int yOffset) {
        Validate.notNull(text, "The text may not be null.");

        int oldY = this.yPos;
        this.yPos += yOffset;
        this.write(text);

        // The specified yOffset is only temporary
        this.yPos = oldY;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage writeLine(final String text) {
        graphics.drawString(text, this.xPos, this.yPos + this.getFontMetrics().getAscent() - this.getFontMetrics().getDescent());
        this.newLine();

        return this;
    }

    private FontMetrics getFontMetrics() {
        return this.graphics.getFontMetrics(this.previouslyUsedFont);
    }

    /**
     * {@inheritDoc}
     */
    public TextImage newLine() {
        this.yPos = this.yPos + getFontMetrics().getHeight();
        this.xPos = this.margin.getLeft();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage newLine(final int times) {
        this.yPos = this.yPos + (times * getFontMetrics().getHeight());
        this.xPos = this.margin.getLeft();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage withFont(final Font font) {
        this.previouslyUsedFont = Validate.notNull(font, "The font may not be null.");
        this.graphics.setFont(font);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage setTextAligment(final Alignment alignment) {
        this.alignment = Validate.notNull(alignment, "The alignment may not be null.");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage withFontStyle(final Style style) {
        this.style = Validate.notNull(style, "The style may not be null.");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage withColor(final Color color) {
        this.previouslyUsedColor = Validate.notNull(color, "The color may not be null.");
        this.graphics.setColor(color);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage write(final Image image) {
        Validate.notNull(image, "The image may not be null.");

        int iWidth = ((BufferedImage) image).getWidth();
        int iHeight = ((BufferedImage) image).getHeight();

        int y = this.yPos + getFontMetrics().getAscent() - iHeight;

        this.graphics.drawImage(image, this.xPos, y, null);
        this.xPos = this.xPos + iWidth;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage write(final Image image, final int yOffset) {
        Validate.notNull(image, "The image may not be null.");

        int oldY = this.yPos;
        this.yPos += yOffset;
        this.write(image);

        // The specified yOffset is only temporary
        this.yPos = oldY;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage writeLine(final Image image) {
        Validate.notNull(image, "The image may not be null.");

        int iHeight = ((BufferedImage) image).getHeight();

        int y = this.yPos + getFontMetrics().getAscent() - iHeight;

        this.graphics.drawImage(image, this.xPos, y, null);
        this.newLine();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TextImage write(final Image image, final int x, final int y) {
        Validate.notNull(image, "The image may not be null.");

        this.graphics.drawImage(image, x, y, null);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void setMargin(final Margin margin) {
        Validate.notNull(margin, "The margin may not be null.");

        this.xPos = margin.getTop();
        this.yPos = margin.getLeft();
        this.margin = margin;
    }

    /**
     * {@inheritDoc}
     */
    public Font getCurrentFont() {
        return this.previouslyUsedFont;
    }

    /**
     * {@inheritDoc}
     */
    public Color getCurrentColor() {
        return this.previouslyUsedColor;
    }

    // TODO Find a better way to get to the buffered image
    public BufferedImage getBufferedImage() {
        return image;
    }

    /**
     * {@inheritDoc}
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    public int getWidth() {
        return this.width;
    }
}
