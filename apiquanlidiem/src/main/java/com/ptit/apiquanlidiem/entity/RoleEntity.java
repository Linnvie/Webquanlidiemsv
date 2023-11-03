package com.ptit.apiquanlidiem.entity;

import com.ptit.apiquanlidiem.vo.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="role")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity{

    @Id
    @Enumerated(EnumType.STRING)
    private RoleEnum id;

    @Column(name="name", length=20)
    private String name;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.id.name()));
        return authorities;
    }

    public RoleEnum getId() {
        return id;
    }

    public void setId(RoleEnum id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
