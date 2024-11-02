package com.unidthon.jabuhae.domain.entity;


import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooking_id", nullable = false, updatable = false)
    private Cooking cooking;

    @Builder
    public Comment(String content, User user, Cooking cooking) {
        this.content = content;
        this.user = user;
        this.cooking = cooking;
    }
}