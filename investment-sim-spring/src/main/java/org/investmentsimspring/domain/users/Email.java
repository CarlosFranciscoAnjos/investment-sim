package org.investmentsimspring.domain.users;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Email {

    // exception messages
    public static final String EMAIL_EXCEPTION = "Invalid Email";

    // email regex
    public static final String REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Access(AccessType.FIELD)
    private final String email;

    protected Email() {
        this.email = "";
    }

    public Email(String email) {
        if (email == null || email.isEmpty() || !email.matches(REGEX))
            throw new IllegalArgumentException(EMAIL_EXCEPTION);
        this.email = email;
    }

    public String getValue() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(this.email, email.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return email;
    }

}
