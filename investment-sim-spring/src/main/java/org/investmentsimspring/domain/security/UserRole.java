package org.investmentsimspring.domain.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Carlos Anjos
 * <p>
 * Represents the roles a user can take
 */
public enum UserRole {

    ADMIN(Sets.newHashSet()), // ROLE_ADMIN
    CLIENT(Sets.newHashSet()); // ROLE_CLIENT

    private final Set<ActionPermission> permissions;

    UserRole(Set<ActionPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ActionPermission> getRolePermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getRolePermissions().stream()
                .map((permission) -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
