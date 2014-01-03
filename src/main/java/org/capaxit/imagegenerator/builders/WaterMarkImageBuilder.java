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

package org.capaxit.imagegenerator.builders;

import org.capaxit.imagegenerator.impl.TextImageImpl;
import org.capaxit.imagegenerator.Margin;
import org.capaxit.imagegenerator.TextImage;

import java.awt.image.BufferedImage;

/**
 * Builder for creating image instances with a watermark.
 */
public final class WaterMarkImageBuilder {
	private final int width;

	private final int height;

	private final Margin margin;

    /**
     * Constructs a new instance of the WaterMarkImageBuilder class.
     * @param width The width of the image returned by this builder.
     * @param height The height of the image returned by this builder.
     * @param margin The margins used of the image returned by this builder.
     */
    public WaterMarkImageBuilder(final int width, final int height, final Margin margin) {
        if (margin == null) {
            throw new IllegalArgumentException("The margin may not be null.");
        }

		this.width = width;
		this.height = height;
		this.margin = margin;
	}

	/**
	 * Creates an image with a watermark.
	 * 
	 * @param waterMark The watermark used for the image.
	 * @return Watermarked image.
	 */
	public TextImage build(final BufferedImage waterMark) {
		TextImageImpl image = new TextImageImpl(width, height);

		for (int x = 0; x < this.width; x += waterMark.getWidth()) {
			for (int y = 0; y < this.height; y += waterMark.getHeight()) {
				image.write(waterMark, x, y);
			}
		}

		image.setMargin(margin);

		return image;
	}

}
