/*
 * Copyright 2014 Jamie Craane - Capax IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.capaxit.imagegenerator;

import java.awt.*;

/**
 * Interface containing all methods for constructing images with text based content. Most methods in this
 * interface can be chained.
 */
public interface TextImage {
	/**
	 * Defines the textwrapper to use.
     * @return this
	 */
	TextImage useTextWrapper(TextWrapper wrapper);
	/**
	 * Method which accepts a TextImageCallback which makes the graphics object available for custom processing.
	 * @param callback
     * @return this
	 */
	TextImage performAction(TextImageCallback callback);
	/**
	 * Adds the specified horizontal space in pixels. Can be used for the
	 * aligment of elements when you need precise control over the positioning
	 * of elements.
	 * 
	 * @param space
	 *            The horizontal space in pixels.
	 * @return this
	 */
	TextImage addHSpace(int space);
    /**
	 * Adds the specified vertical space in pixels. Can be used for the
	 * aligment of elements when you need precise control over the positioning
	 * of elements.
	 *
	 * @param space
	 *            The vertical space in pixels.
	 * @return this
	 */
	TextImage addVSpace(int space);
	/**
	 * Enables/disables the wrapping of text at the right end margin when the
	 * methods are used with no explicit newline, like {@link #write(String)}.
	 * 
	 * @param enable
	 *            true enables text wrapping, false disables text wrapping.
     * @return this
	 */
	TextImage wrap(boolean enable);

	/**
	 * @return The height of the image in pixels.
	 */
	int getHeight();

	/**
	 * @return The width of the image in pixels.
	 */
	int getWidth();

	/**
	 * Add text, in the image current font and color to the image.
	 * 
	 * @param text
	 *            The text to render.
	 * @return this
	 */
	TextImage write(String text);

	/**
	 * Add text, in the image current font and color to the image. The specified
	 * yAligment aligns the text.
	 * 
	 * @param text
	 *            The text to render.
	 * @param yOffset
	 * @return this
	 */
	TextImage write(String text, int yOffset);

	/**
	 * Add text, in the image current font and color to the image. Inserts a
	 * newline.
	 * 
	 * @param text
	 *            The text to render.
	 * @return this
	 */
	TextImage writeLine(String text);

	/**
	 * Adds the specified image, aligned with the text, to the image. The bottom
	 * of the the image is aligned with the beginning of the ascent of the font.
	 * 
	 * @param image
	 * @return this
	 */
	TextImage write(Image image);

	/**
	 * Adds the specified image, aligned with the text, to the image. The bottom
	 * of the the image is aligned with the beginning of the ascent of the font.
	 * Aligns the image at the specified yOffset.
	 * 
	 * @param image
	 * @param yOffset
	 * @return this
	 */
	TextImage write(Image image, int yOffset);

	/**
	 * Adds the specified image, aligned with the text, to the image. The bottom
	 * of the the image is aligned with the beginning of the ascent of the font.
	 * Adds a newline.
	 * 
	 * @param image
	 * @return this
	 */
	TextImage writeLine(Image image);

	/**
	 * Adds an image at the specified x and y positions (absolute).
	 * 
	 * @param image
	 *            The image to be inserted.
	 * @param x
	 *            left position of teh image.
	 * @param y
	 *            top position of the image.
	 * @return this
	 */
	TextImage write(Image image, int x, int y);

	/**
	 * Inserts a newline based on the previously used dont. The previously used
	 * font is determined by the last call to the addText* methods with the
	 * given font. If none of these methods are used, no font is available yet,
	 * you can set one with the {@link #withFont(Font)} and
	 *
	 * @return this
	 */
	TextImage newLine();

	/**
	 * Convenience method which executes the newline the specified number of times.
	 * 
	 * @param times
	 *            The number of times the newline is executed.
	 * @return this
	 */
	TextImage newLine(int times);

	/**
	 * Use the specified Font in all subsequent calls.
	 * 
	 * @param font
	 * @return this
	 */
	TextImage withFont(Font font);

	/**
	 * Set the fontstyle to be used.
	 * 
	 * @param style
	 * @return this
	 */
	TextImage withFontStyle(Style style);

	/**
	 * Use the specified color in all subsequent calls.
	 * 
	 * @param color
	 * @return this
	 */
	TextImage withColor(Color color);
	/**
     * Returns the currently used Font.
	 * @return The currently used font.
	 */
	Font getCurrentFont();
	/**
     * Returs the currently used Color.
	 * @return The currently used color. 
	 */
	Color getCurrentColor();

    /**
     * Sets the text aligment. @see Alignment.
     * @param alignment
     * @return this
     */
    TextImage setTextAligment(Alignment alignment);
}
