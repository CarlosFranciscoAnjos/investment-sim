package org.investmentsimspring.domain.contracts;

/**
 * @param <T>
 * @author Carlos Anjos
 */
public interface Dtoable<T extends Dto> {

    T toDto();
}
