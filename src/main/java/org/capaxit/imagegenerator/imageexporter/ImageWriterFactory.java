package org.capaxit.imagegenerator.imageexporter;

import org.capaxit.imagegenerator.imageexporter.exporters.JpegImageWriter;
import org.capaxit.imagegenerator.imageexporter.exporters.PngImageWriter;

/**
 * Factory which creates an ImageWriter based on the ImageType.
 *
 * User: Jamie Craane
 */
public final class ImageWriterFactory {
    private static final ImageWriter JPEG_IMAGE_WRITER = new JpegImageWriter();
    private static final ImageWriter PNG_IMAGE_WRITER = new PngImageWriter();

    private ImageWriterFactory() {
        // Prevent instantiation
    }

    /**
     * @param imageType The exporttpye to create an ImageWriter for.
     * @return An implementation of ImageWriter which is capable of writing images
     * indicated by the imageType.
     */
    public static ImageWriter getImageWriter(final ImageType imageType) {
        switch (imageType) {
            case PNG:
                return PNG_IMAGE_WRITER;
            case JPEG:
                return JPEG_IMAGE_WRITER;
            default:
                throw new IllegalStateException("No imagewriter defined for imageType[" + imageType + "].");
        }
    }
}
