package dev.reddy.olm.enums;

import static dev.reddy.olm.Constants.*;

public enum Authority {

    USER(USER_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES),
    MANAGER(MANAGER_AUTHORITIES);

    private final String value;

    Authority(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Authority fromValue(String value) {
        for (Authority authority : values()) {
            if (authority.value.equals(value)) {
                return authority;
            }
        }
        throw new IllegalArgumentException("Unknown authority: " + value);
    }
}
