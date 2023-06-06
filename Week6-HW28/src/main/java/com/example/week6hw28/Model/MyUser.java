package com.example.week6hw28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {
    @JsonIgnore
    @Transient
    private final String rol="CUSTOMER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Column(unique = true,nullable = false)
    private String username;
    @NotEmpty
    @Column(nullable = false)
    private String password;
    @NotEmpty
    @Column(nullable = false)
    @Pattern(regexp = "^("+rol+")",message = "register as -CUSTOMER- please")
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private Set<Order> orders;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
