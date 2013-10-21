package org.capaxit.imagegenerator.imageexporter.exporters;

import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.capaxit.imagegenerator.util.Validate;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Implementation of the ImageWriter which handles png exports.
 * User: Jamie Craane
 */
public final class PngImageWriter implements ImageWriter {
    private static final String PNG = "png";

    /**
     * {@inheritDoc}
     * @param image The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     */
    public void writeImageToOutputStream(final TextImage image, final OutputStream outputStream) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(outputStream, "The outputStream may not be null.");

        ImageIO.write(((TextImageImpl)image).getBufferedImage(), PNG, outputStream);
    }

    /**
     * {@inheritDoc}
     * @param image The image to write to the file.
     * @param file The outputStream to write the image to.
     */
    public void writeImageToFile(final TextImage image, final File file) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(file, "The file may not be null.");

        OutputStream os = new FileOutputStream(file);
        try {
            ImageIO.write(((TextImageImpl)image).getBufferedImage(), PNG, os);
        } finally {
            os.close();
        }
    }
}
