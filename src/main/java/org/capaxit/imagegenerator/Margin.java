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
 * top, left, right and bottom margins in pixels.
 *
 * Immutable.
 */
public final class Margin {
    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;

    public Margin(final int left, final int top) {
        this.left = left;
        this.top = top;
    }

    public Margin(final int left, final int top, final int right, final int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * @return The left margin.
     */
    public int getLeft() {
        return left;
    }

    /**
     * @return The right margin.
     */
    public int getRight() {
        return right;
    }

    /**
     * @return The top margin.
     */
    public int getTop() {
        return top;
    }

    /**
     * @return The bottom margin.
     */
    public int getBottom() {
        return bottom;
    }
}
