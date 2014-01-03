package org.capaxit.imagegenerator.examples;

import org.capaxit.imagegenerator.Alignment;
import org.capaxit.imagegenerator.Margin;
import org.capaxit.imagegenerator.Style;
import org.capaxit.imagegenerator.TextImage;
import org.capaxit.imagegenerator.imageexporter.ImageType;
import org.capaxit.imagegenerator.imageexporter.ImageWriter;
import org.capaxit.imagegenerator.imageexporter.ImageWriterFactory;
import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.capaxit.imagegenerator.textalign.GreedyTextWrapper;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

public class TextWrap {
	public static void main(String[] args) throws Exception {
		new TextWrap().runExample();
	}

	private void runExample() throws Exception {
        TextImage textImage = new TextImageImpl(300, 550, new Margin(25, 5, 50, 0));

        InputStream is = this.getClass().getResourceAsStream(
				"/org/capaxit/imagegenerator/examples/fonts/cour.ttf");
        Font usedFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(11.0f);

        textImage.useTextWrapper(new GreedyTextWrapper());
        textImage.setTextAligment(Alignment.LEFT);
        textImage.withFont(usedFont);

        textImage
				.wrap(true)
                .write(
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever sinze the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").newLine();

        textImage
				.setTextAligment(Alignment.RIGHT)
                .write(
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever sinze the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").newLine();

        textImage
				.setTextAligment(Alignment.CENTER)
                .write(
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever sinze the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").newLine();

        textImage
				.setTextAligment(Alignment.JUSTIFY)
                .write(
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever sinze the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").newLine()
                ;

        textImage.wrap(false).withFontStyle(Style.UNDERLINED).setTextAligment(Alignment.LEFT).newLine().write(
				"This text falls of the line because wrapping is disabled again.");

        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
        imageWriter.writeImageToFile(textImage, new File("textwrap.png"));
	}
}
