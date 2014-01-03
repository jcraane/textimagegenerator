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

/**
 * (Immutable) represents text to draw on the given xpos.
 */
public final class DrawableText {
    private final String text;
    private final int xPos;

    public DrawableText(final String text, final int xPos) {
        this.text = text;
        this.xPos = xPos;
    }

    public String getText() {
        return text;
    }

    public int getXPos() {
        return xPos;
    }
}
