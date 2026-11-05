package dev.subscription_service.schedule;


import dev.subscription_service.entity.Subscription;
import dev.subscription_service.enums.SubscriptionStatus;
import dev.subscription_service.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckSubscriptionStatusSchedule {

    private final SubscriptionRepository subscriptionRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Scheduled(cron = "${app.checkSub.cron}")
    public void checkSubscriptionStatus() {
        log.info("Schedule запустилась");

        OffsetDateTime now = OffsetDateTime.now();
        log.info("Текущее время (OffsetDateTime.now()): {}", now);
        log.info("Текущее время (UTC): {}", now.toInstant());

        List<Subscription> expiredSubscriptions =
                subscriptionRepository.findAllByExpiresAtBeforeAndStatus(now, SubscriptionStatus.PAID);

        log.info(" Найдено просроченных подписок: {}", expiredSubscriptions.size());

        if (expiredSubscriptions.isEmpty()) {
            return;
        }

        for (Subscription subscription : expiredSubscriptions) {
            log.info(" Истекает подписка у: {}", subscription.getUsername());
            subscription.setStatus(SubscriptionStatus.FREE);
            subscriptionRepository.save(subscription);

            kafkaTemplate.send("expired-subscriptions", subscription.getUsername(), subscription.getUsername());
            log.info("Отправлено сообщение в Kafka для: {}", subscription.getUsername());
        }
    }


}
