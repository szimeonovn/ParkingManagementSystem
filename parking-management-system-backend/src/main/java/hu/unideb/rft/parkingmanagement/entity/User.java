package hu.unideb.rft.parkingmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles;
}
