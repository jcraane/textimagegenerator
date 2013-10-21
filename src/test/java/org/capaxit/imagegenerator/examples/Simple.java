package org.capaxit.imagegenerator.examples;

import org.capaxit.imagegenerator.Margin;
import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageType;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.imageexporter.ImageWriterFactory;
import org.capaxit.imagegenerator.impl.TextImageImpl;

import java.io.File;

/**
 * Simple example to demonstrate the creation of simple text-based images.
 */
public class Simple {
	public static void main(String[] args) throws Exception {
		new Simple().runExample();
	}

	private void runExample() throws Exception {
		// 1. create a new TextImageImpl with a size of 300x300 pixels
		// and a left and top margin of 5 pixels. The default font is SansSerif,
		// PLAIN,12
		TextImage textImage = new TextImageImpl(300, 300, new Margin(0, 0));

		// 2. These methods add text and a newline
		textImage.writeLine("This is a simple example");
		textImage.writeLine("of creating a textimage.");

		// 3. Add explicit newlines. All methods can be chained for convenience.
		textImage.newLine().newLine();

		// 4. Add other text
		textImage.withFontStyle(Style.UNDERLINED).write("Hello world!");

        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
        imageWriter.writeImageToFile(textImage, new File("simple.png"));
	}
}
