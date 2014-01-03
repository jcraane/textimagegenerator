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

import java.awt.FontMetrics;
import java.util.List;

/**
 * Defines the contract for textwrapper implementations. A textwrapper implements de details of how to
 * wrap the given text over multiple lines. Different strategies can be implemented.
 * @see org.capaxit.imagegenerator.textalign.GreedyTextWrapper
 */
public interface TextWrapper {
	/**
	 * Wraps the given text and writes in on the specified graphics object.
	 * @param text
	 * @param lineWidth
	 * @param fm
	 * @return A List of Strings. Each entry in the List should appear on a new line.
	 */
	List<String> doWrap(String text, int lineWidth, FontMetrics fm);
}
