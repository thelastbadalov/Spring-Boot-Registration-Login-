package com.example.registirationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name="first_name")
private String firstName;
    @Column(name="last_name")

private String lastName;

    public User(String firstName, String lastName, String email, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private String email;


    private String password;

@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(
   name="user_id",referencedColumnName = "id" ),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
private List<Role> roles;
}
