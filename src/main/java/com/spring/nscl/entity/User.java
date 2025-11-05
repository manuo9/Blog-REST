package com.spring.nscl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name= "users")
@Entity
public class User {
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private  Long id;
    private  String name;
    @Column(nullable = false , unique = true)
    private  String email;
    @Column(nullable = false , unique = true)
    private  String username;
    @Column(nullable = false )
    private  String password;

    @ManyToMany(fetch =  FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name ="user_roles",
            joinColumns = @JoinColumn (name = "user-id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> role ;

}
