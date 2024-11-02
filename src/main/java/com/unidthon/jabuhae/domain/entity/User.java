package com.unidthon.jabuhae.domain.entity;


import com.unidthon.jabuhae.domain.model.UserStatus;
import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserStatus userStatus;

    @Column(nullable = false)
    private Long exp;

    @Column(nullable = false)
    private int hungerRange;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @Builder
    public User(String name, String email, String password, UserStatus userStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.exp = 0L;
        this.hungerRange = 50;
    }
}