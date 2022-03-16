package org.investmentsimspring.domain.containers;

import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.users.User;

import javax.persistence.*;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a container for financial items
 */
@Entity
public class Container implements Dtoable<ContainerDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;

    protected Container() {
    }

    public Container(long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Container(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @Override
    public ContainerDto toDto() {
        ContainerDto dto = new ContainerDto();
        dto.id = this.id;
        dto.userDto = this.user.toDto();
        return dto;
    }
}
