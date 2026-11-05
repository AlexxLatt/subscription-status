package dev.subscription_service.controller;

import dev.subscription_service.dto.SubscriptionResponceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.subscription_service.service.SubscriptionService;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{userName}")
    public SubscriptionResponceDto getSubscriptions(@PathVariable String userName) {
        return subscriptionService.getSubscriptions(userName);
    }
}