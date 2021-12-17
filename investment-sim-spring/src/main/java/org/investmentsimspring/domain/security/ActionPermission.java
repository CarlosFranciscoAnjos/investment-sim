package org.investmentsimspring.domain.security;

/**
 * @author Carlos Anjos
 * <p>
 * Represents the permission to execute a given action
 */
public enum ActionPermission {

    USERS_WRITE("users:write");

    private final String permission;

    ActionPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
