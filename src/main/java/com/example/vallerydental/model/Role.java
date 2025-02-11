package com.example.vallerydental.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
