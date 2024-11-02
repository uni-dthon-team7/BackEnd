package com.unidthon.jabuhae.domain.entity;

import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Heart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long cookingHeartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooking_id", nullable = false, updatable = false)
    private Cooking cooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    private String memo;

    @Builder
    public Heart(Cooking cooking, User user, String memo) {
        this.cooking = cooking;
        this.user = user;
        this.memo = memo;
    }
}