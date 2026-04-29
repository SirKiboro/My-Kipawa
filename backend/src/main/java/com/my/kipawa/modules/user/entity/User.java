package com.my.kipawa.modules.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @Column(nullable = false)
    private boolean enabled = true;

    @PrePersist
    @PreUpdate
    private void syncFullName(){
        if (firstName != null || lastName != null) {
            this.fullName = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
            this.fullName = this.fullName.trim();
        }
    }

            // security methods
    @Override
    public String getUsername(){
        return email;
    }

    public String getActualUsername() {
        return username;
    }

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true; }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return enabled;
    }

}
