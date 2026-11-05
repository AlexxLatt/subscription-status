package dev.subscription_service.service;

import dev.subscription_service.dto.mapper.SubscriptionMapper;
import dev.subscription_service.dto.SubscriptionResponceDto;
import dev.subscription_service.entity.Subscription;

import dev.subscription_service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import dev.subscription_service.repository.SubscriptionRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionResponceDto getSubscriptions(String userId) {

        Subscription subscription = subscriptionRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "Подписка пользователя не найдена: " + userId
                ));

        log.info("Взял подписку");
        return subscriptionMapper.toDto(subscription);

    }
}
