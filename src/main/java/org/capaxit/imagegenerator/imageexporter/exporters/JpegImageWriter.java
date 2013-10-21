package org.capaxit.imagegenerator.imageexporter.exporters;

import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.capaxit.imagegenerator.util.Validate;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Locale;

/**
 * Implementation of the ImageWriter that handlers Jpeg export.
 * Uses the highest quality Jpeg.
 *
 * User: Jamie Craane
 */
public final class JpegImageWriter implements ImageWriter {
    private static final int MAX_COMPRESSION_QUALITY = 1;

    /**
     * {@inheritDoc}
     * @param image The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     * @throws IOException
     */
    public void writeImageToOutputStream(final TextImage image, final OutputStream outputStream) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(outputStream, "The outputStream may not be null.");

        compressJpegStream(outputStream, image);
    }

    /**
     * {@inheritDoc}
     * @param image The image to write to the file.
     * @param file The outputStream to write the image to.
     * @throws IOException
     */
    public void writeImageToFile(final TextImage image, final File file) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(file, "The file may not be null.");

        OutputStream outputStream = new FileOutputStream(file);
        compressJpegStream(outputStream, image);
    }

    private void compressJpegStream(
            final OutputStream outputStream,
            final TextImage image) throws IOException {
        javax.imageio.ImageWriter jpegWriter = getJpegWriter();
        ImageWriteParam imageWriteParam = getHighestCompressionQualityParams();
        writeImageToOutputStream(outputStream, image, jpegWriter, imageWriteParam);

    }

    private javax.imageio.ImageWriter getJpegWriter() {
        javax.imageio.ImageWriter writer = null;
        Iterator<javax.imageio.ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
        if (iter.hasNext()) {
            writer = iter.next();
        }
        return writer;
    }

    private ImageWriteParam getHighestCompressionQualityParams() {
        // Set the compression quality
        ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault()) {
            public void setCompressionQuality(float quality) {
                this.compressionQuality = quality;
            }
        };
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
        iwparam.setCompressionQuality(MAX_COMPRESSION_QUALITY);
        return iwparam;
    }

    private void writeImageToOutputStream(
            final OutputStream outputStream,
            final TextImage image,
            final javax.imageio.ImageWriter jpegWriter,
            final ImageWriteParam imageWriteParam) throws IOException {
        ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
        try {
            jpegWriter.setOutput(ios);
            jpegWriter.write(null, new IIOImage(((TextImageImpl)image).getBufferedImage(), null, null), imageWriteParam);
        } finally {
            cleanUp(jpegWriter, ios);
        }
    }

    private void cleanUp(final javax.imageio.ImageWriter jpegWriter, final ImageOutputStream ios) throws IOException {
        ios.flush();
        jpegWriter.dispose();
        ios.close();
    }
}
