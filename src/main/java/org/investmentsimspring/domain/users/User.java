package org.investmentsimspring.domain.users;

import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.security.Hash;
import org.investmentsimspring.domain.security.HashHelper;
import org.investmentsimspring.domain.security.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a user for authentication purposes (used to log in)
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"})
)
public class User implements Dtoable<UserDto>, UserDetails {

    // exception messages
    public static final String ROLE_EXCEPTION = "Role can not be null";

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Username username;
    @Embedded
    private Email email;
    @Embedded
    private Hash hash;
    @Enumerated
    private UserRole role;
    @Basic
    private boolean isEnabled;

    protected User() {
    }

    protected User(long id, Username username, Email email, Hash hash, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hash = hash;
        this.role = role;
        this.isEnabled = true;
    }

    /**
     * @param username
     * @param email
     * @param hash
     * @param role
     */
    public User(Username username, Email email, Hash hash, UserRole role) {
        this.username = username;
        this.email = email;
        this.hash = hash;
        this.role = role;
        this.isEnabled = true;
    }

    //region getters & setters
    public long getId() {
        return id;
    }

    public Username getName() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Hash getHash() {
        return hash;
    }

    public UserRole getRole() {
        return role;
    }
    //endregion

    /**
     * Enables user login
     */
    protected void enable() {
        this.isEnabled = true;
    }

    /**
     * Disables user login
     */
    protected void disable() {
        this.isEnabled = false;
    }

    /**
     * @param email
     * @return true if this user has the email (used as ID) passed by parameter, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.getValue().equalsIgnoreCase(email);
    }

    /**
     * @param password
     * @return true if this user has the hash passed by parameter, false otherwise
     */
    public boolean hasPassword(String password) {
        return password != null && this.hash.getValue().equals(HashHelper.encode(password));
    }

    /**
     * @param userRole
     * @return true if successfully set role to user, false otherwise
     */
    public boolean setRole(UserRole userRole) {
        if (userRole == null) return false;
        role = userRole;
        return true;
    }

    /**
     * @param role
     * @return true if this user has the role passed by parameter, false otherwise
     */
    public boolean hasRole(UserRole role) {
        return this.role.equals(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", email=" + email +
                ", hash=" + hash +
                ", role=" + role +
                '}';
    }

    @Override
    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.id = this.id;
        dto.username = this.username.getValue();
        dto.email = this.email.getValue();
        dto.hash = this.hash.getValue();
        dto.role = this.role.name();
        return dto;
    }

    //region user details interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return hash.getValue();
    }

    @Override
    public String getUsername() {
        return email.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    //endregion

}
