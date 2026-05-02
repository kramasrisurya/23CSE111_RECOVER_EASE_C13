package util;

import java.util.UUID;

public class IDGenerator {
    public static String generate() {
        // Short 8-char ID for readability
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
