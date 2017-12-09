package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5825297594852203129L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

}
