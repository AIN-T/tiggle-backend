package com.gamja.tiggle.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String loginType;
    private Boolean status;
    private Boolean enable;
    private String role;
    private String address;
    private String phoneNumber;
    private Integer point;
    //    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date verifiedAt;

    public UserEntity(Long id) {
        this.id = id;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void verifiedAt() {
        this.verifiedAt = Timestamp.from(Instant.now());
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
