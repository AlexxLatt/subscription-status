package dev.subscription_service.entity;

import dev.subscription_service.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(length = 100, nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubscriptionStatus status;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;
}