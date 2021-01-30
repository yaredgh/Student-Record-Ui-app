package com.yaredgidey.studentdatauiapp.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @NotEmpty(message = "Password must not be blank.")
        @Size(min = 8, max = 15, message = "Password must be between 8 to 15 Characters.")
        private String password;
        @NotEmpty(message = "Name must not be blank.")
        @Size(min = 4, max = 15, message = "Name must be between 4 to 15 Characters.")
        private String name;
        @NotEmpty(message = "Name must not be blank.")
        @Size(min = 4, max = 15, message = "Name must be between 4 to 15 Characters.")
        private String username;
        @NotEmpty(message = "Name must not be blank.")
        @Size(min = 4, max = 15, message = "Name must be between 4 to 15 Characters.")
        private String lastName;
        @NotEmpty(message = "Email must not be blank.")
        @Email
        private String email;
    @ManyToMany (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long Id() {
            return Id;
        }
        public void seId(Long Id) {
            this.Id = Id;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

    public User() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
