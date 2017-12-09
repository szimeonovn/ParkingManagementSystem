package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role extends BaseEntity {

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

}
