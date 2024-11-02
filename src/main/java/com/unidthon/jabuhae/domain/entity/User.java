package com.unidthon.jabuhae.domain.entity;


import com.unidthon.jabuhae.domain.model.UserStatus;
import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userStatus = UserStatus.ACTIVE;
        this.exp = 0L;
        this.hungerRange = 50;
        this.comments = new ArrayList<>();
        this.bookmarks = new ArrayList<>();
    }

    public void updateExpAndHunger(Long exp, int hungerRange) {
        this.exp += exp;
        this.hungerRange += hungerRange;
        if (this.hungerRange < 0) {
            this.hungerRange = 0;
        }
        if (this.hungerRange > 100) {
            this.hungerRange = 100;
        }
    }
}