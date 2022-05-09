package org.investmentsimspring.domain.security;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Hash {

    // exception messages
    public static final String PASSWORD_EXCEPTION = "Hash can not be null nor empty";

    public static Hash fromHash(String hash) {
        Hash obj = new Hash();
        obj.password = hash;
        return obj;
    }

    @Access(AccessType.FIELD)
    private String password;

    protected Hash() {
        this.password = "";
    }

    public Hash(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException(PASSWORD_EXCEPTION);
//        this.password = password;
        this.password = HashHelper.encode(password);
    }

    public String getValue() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hash hash = (Hash) o;
        return Objects.equals(this.password, hash.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    @Override
    public String toString() {
        return password;
    }

}