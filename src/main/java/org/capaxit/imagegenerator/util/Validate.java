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
