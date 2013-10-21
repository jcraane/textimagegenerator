package org.capaxit.imagegenerator.imageexporter;

import org.capaxit.imagegenerator.TextImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: Jamie Craane
 */
public interface ImageWriter {
    /**
     * Writes the given image to the given outputStream.
     * Does not close the outputStream.
     *
     * @param image The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     */
    void writeImageToOutputStream(TextImage image, OutputStream outputStream) throws IOException;

    /**
     * Writes the given image to the given file.
     * @param image The image to write to the file.
     * @param file The outputStream to write the image to.
     */
    void writeImageToFile(TextImage image, File file) throws IOException;
}
