package hu.unideb.rft.parkingmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TEST")
public class Test implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "TEST_SEQ")
    private Long id;

    @Column(name = "NAME")
    private String name;

}
