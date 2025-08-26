package com.nova.shared.security.core;

import java.util.Set;

public final class PermissionCheck {
    private PermissionCheck() {}

    public static boolean hasAny(Set<String> granted, String... required) {
        if (granted == null || granted.isEmpty() || required == null) return false;
        for (String r : required) if (granted.contains(r)) return true;
        return false;
    }

    public static boolean hasAll(Set<String> granted, String... required) {
        if (required == null) return true;
        if (granted == null) return false;
        for (String r : required) if (!granted.contains(r)) return false;
        return true;
    }
}
