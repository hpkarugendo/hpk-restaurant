package com.hpkarugendo.hpkrestaurant.management.models;

import com.hpkarugendo.hpkrestaurant.management.enums.Department;
import com.hpkarugendo.hpkrestaurant.management.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements UserDetails {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName, lastName, email, phone;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    private Position position;
    private boolean loggedIn;
    @Column(unique = true)
    private int code;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        if(position == Position.EMPLOYEE){
            authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
        } else if(position == Position.SUPERVISOR){
            authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
            authorities.add(new SimpleGrantedAuthority("SUPERVISOR"));
        } else if(position == Position.MANAGER){
            authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
            authorities.add(new SimpleGrantedAuthority("SUPERVISOR"));
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
