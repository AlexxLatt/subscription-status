package dev.subscription_service.repository;

import dev.subscription_service.entity.Subscription;
import dev.subscription_service.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    List<Subscription> findAllByExpiresAtBeforeAndStatus (OffsetDateTime dateTime, SubscriptionStatus status);
}
