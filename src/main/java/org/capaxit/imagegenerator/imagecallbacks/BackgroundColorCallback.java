package org.capaxit.imagegenerator.imagecallbacks;

import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.TextImageCallback;
import org.capaxit.imagegenerator.util.Validate;

import java.awt.*;

/**
 * Implementation of the TextImageCallback interface to change the backgroundcolor of the TextImage.
 *
 * User: Jamie Craane
 */
public final class BackgroundColorCallback implements TextImageCallback {
    private final Color backgroundColor;
    private final Color textColor;
    private final TextImage textImage;

    /**
     * Creates a new instance of the BackgroundColorCallback.
     * @param backgroundColor The backgroundcolor to use for the TextImage.
     * @param textColor After the backgroundcolor is set, sets the color to this color for the text. The withColor
     * method can also be used to set the color back to the desired color.
     * @param textImage An instance of TextImage.
     */
    public BackgroundColorCallback(final Color backgroundColor, final Color textColor, final TextImage textImage) {
        Validate.notNull(backgroundColor, " The backgroundColor may not be null.");
        Validate.notNull(textColor, " The textColor may not be null.");
        Validate.notNull(textImage, " The textImage may not be null.");

        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.textImage = textImage;
    }

    public void doWithGraphics(final Graphics2D graphics) {
        // Set the color for the background
        graphics.setColor(backgroundColor);
        // Draw a rectangle to apply the given background
        graphics.fillRect(0, 0, textImage.getWidth(), textImage.getHeight());
        // set the color back to black for the text
        graphics.setColor(textColor);
    }
}
