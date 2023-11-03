package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class AccountEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", length=20)
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="status", nullable=false)
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "role")
    private RoleEntity role;

//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    private SinhVienEntity sinhVien;
//
//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    private QuanTriVienEntity quanTriVien;
//
//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    private GiangVienEntity giangVien;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role));
//        return java.util.List.of(new SimpleGrantedAuthority(authorities.toString()));
//        // List.of(new SimpleGrantedAuthority(authorities.toString()));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
