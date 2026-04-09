textimagegenerator
==================

Library for easily creating images (PNG/JPG) which contain text-data, making those data harder to copy then regular HTML.

This project provides a Java API for creating images/graphics with text-based content. 
The image can be saved in several formats (png,jpg etc.). Fonts, styles and colors can be 
chosen for the text being rendered. See the src/test/nl/jamiecraane/imagegenerator/examples 
packages for numerous examples of how to use this library.

For there's an actual example in my blogpost about this project at http://jcraane.blogspot.com/2009/01/text-image-generator-example.html, but it's a bit outdated. The following is a working example that uses this library to create an image with some text in it:

```
TextImage textImage = new TextImageImpl(400, 250, new Margin(0, 0));

// Declare or read the fonts you need
Font header = new Font("Sans-Serif", Font.BOLD, 15);
Font plain = new Font("Sans-Serif", Font.PLAIN, 12);

// Read in any images you need
InputStream is = Main.class.getResourceAsStream("/Warning2.png");
Image warning = ImageIO.read(is);
// Put close() in try-finally block with production code! 
is.close();

// 1. specify font and write text with a newline
textImage.withFont(header).writeLine("Example usage text-image-generator.").newLine();
// 2. enabled the wrapping of text and write text which is automatically wrapped 
textImage.withFont(plain).wrap(true).write("This is an example of how to use the text-image generation library. With this library you can dynamically generated textbased content as images (png or jpeg). Notice how this sentence is automatically wrapped. See the code at http://code.google.com/p/textimagegenerator/ for more examples.").newLine();

// 3. Disable text-wrapping again. Write underlined text.
textImage.wrap(false).withColor(Color.BLUE).withFontStyle(Style.UNDERLINED).write("This line of text is underlinedand blue.").withFontStyle(Style.PLAIN).newLine();
textImage.writeLine("You can also embed images in your generated image:");
// 4. embed the actual image. Here we use absolute positioning
textImage.write(warning, 175, 175);

// 5. Write the image as a png to a file
OutputStream os = new FileOutputStream(new File(PATH, "example.png"));
ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
imageWriter.writeImageToOutputStream(textImage, os);
os.close();
```

To use this library just add the downloaded jar file to your projects classpath.
You can also clone the whole repository to your computer and use Maven to package and install it into your local repository, like this:

```
mvn install
```

This project has minimal dependencies on external libraries (see pom.xml). The distributable can be found under downloads. 
This jar is compatible with Java 1.8 or higher.
