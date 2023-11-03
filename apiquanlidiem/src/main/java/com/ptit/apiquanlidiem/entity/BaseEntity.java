package com.ptit.apiquanlidiem.entity;

import com.ptit.apiquanlidiem.security.jwt.JwtUtils;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


@MappedSuperclass
public  class BaseEntity implements Serializable {

    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    private String updatedBy;

    @PrePersist
    public void onPrePersist() {
        String currentUser = currentUser();
        this.createdAt = Instant.now();
        this.createdBy = currentUser;
        this.updatedAt = Instant.now();
        this.updatedBy = currentUser;
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = Instant.now();
        this.updatedBy = currentUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdBy, updatedBy, createdAt, updatedAt);
    }

    private String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            return "Anonymous";
        }
    }

    public BaseEntity(String createdBy, Instant createdAt, Instant updatedAt, String updatedBy) {
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public BaseEntity() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
