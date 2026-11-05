package dev.subscription_service.dto;

import dev.subscription_service.enums.SubscriptionStatus;

import java.time.OffsetDateTime;

public record SubscriptionResponceDto(
        String username,
        SubscriptionStatus status,
        OffsetDateTime expiresAt
) {
}
