package org.investmentsimspring.domain.contracts;

/**
 * @author Carlos Anjos
 */
public interface Item {

    /**
     * @return monthly money flow, either positive or negative
     */
    double flow();
}
