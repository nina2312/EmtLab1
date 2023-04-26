package com.example.labemt.model;


import com.example.labemt.model.enumerations.Role;
import javax.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name="librarian_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    //todo list od carts.. ?

    public User() {
    }

    public User(String name, String surname, String username, String password, String email, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}