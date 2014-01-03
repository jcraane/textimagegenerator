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

package org.capaxit.imagegenerator.util;

/**
 * Simple utility class to validate parameters. Throws an IllegaleArgumentException if the
 * parameter is not valid.
 */
public final class Validate {
    private Validate() {
        // Prevent instantiation
    }

    /**
     * Checks the passed-in param for null. If the param is null, throws an IllegalArgumentException
     * with the given message.
     * @param param The parameter to check for nullity.
     * @param message The message to throw in an IllegalArgumentException if the param is null.
     */
    public static <T> T notNull(final T param, final String message) {
        if (param == null) {
            throw new IllegalArgumentException(message);
        }

        return param;
    }
}
