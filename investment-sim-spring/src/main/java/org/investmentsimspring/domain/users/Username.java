package org.investmentsimspring.domain.users;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Username {

    // exception messages
    public static final String NAME_EXCEPTION = "Username can not be null nor empty";

    // min length
    private static final int MIN_LENGTH = 3;

    // username regex
    public static final String REGEX = "^[a-zA-Z0-9_-]+$";

    @Access(AccessType.FIELD)
    private final String userName;

    protected Username() {
        this.userName = "";
    }

    public Username(String userName) {
        if (userName == null || userName.length() < MIN_LENGTH || !userName.matches(REGEX))
            throw new IllegalArgumentException(NAME_EXCEPTION);
        this.userName = userName;
    }

    public String getValue() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return this.userName.equalsIgnoreCase(username.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return userName;
    }

}
