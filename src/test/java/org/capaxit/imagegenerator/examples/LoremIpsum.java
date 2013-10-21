package org.capaxit.imagegenerator.examples;

import org.capaxit.imagegenerator.Margin;
import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageType;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.imageexporter.ImageWriterFactory;
import org.capaxit.imagegenerator.impl.TextImageImpl;

import java.awt.*;
import java.io.File;

public class LoremIpsum {
	public static void main(String[] args) throws Exception {
		new LoremIpsum().runExample();
	}
	
	private void runExample() throws Exception {
		// 1. create the nl.jamiecraane.imagegenerator.examples.fonts used
		Font SansSerifBoldBig = new Font("SansSerif", Font.BOLD, 20);
		Font SansSerifBoldNormal = new Font("SansSerif", Font.BOLD, 12);
		Font SansSerifPlainNormal = new Font("SansSerif", Font.PLAIN, 12);

		// 2. Create a new textimage of 450x300 pixels
		TextImage textImage = new TextImageImpl(450, 300, new Margin(15, 0));

		// 3. Specify nl.jamiecraane.imagegenerator.examples.fonts, text and color (last line)
		textImage.withFont(SansSerifBoldBig).writeLine("What is Lorem Ipsum?");
		textImage.withFont(SansSerifBoldNormal).write("Lorem Ipsum ").withFont(SansSerifPlainNormal).writeLine(
				"is simply dummy text of the printing and typesetting industry.");
		textImage.writeLine("Lorem Ipsum has been the industry's standard dummy text ever since");
		textImage.writeLine("the 1500s, when an unknown printer took a galley of type and");
		textImage.writeLine("scrambled it to make a type specimen book. It has survived not only");
		textImage.writeLine("five centuries, but also the leap into electronic typesetting,");
		textImage.writeLine("remaining essentially unchanged. It was popularised in the 1960s with");
		textImage.writeLine("the release of Letraset sheets containing Lorem Ipsum passages, and");
		// Underline
		textImage.withFontStyle(Style.UNDERLINED).write("more recently with desktop publishing software like Aldus PageMaker").newLine();
		// Use a red color
		textImage.withFontStyle(Style.PLAIN).withColor(Color.RED).writeLine("including versions of Lorem Ipsum.");

        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
        imageWriter.writeImageToFile(textImage, new File("loremipsum.png"));
	}
}
