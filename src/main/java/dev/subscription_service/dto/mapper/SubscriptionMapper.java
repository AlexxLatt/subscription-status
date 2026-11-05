package dev.subscription_service.dto.mapper;


import dev.subscription_service.dto.SubscriptionDto;
import dev.subscription_service.dto.SubscriptionResponceDto;
import dev.subscription_service.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponceDto toDto(Subscription subscription);

    Subscription toEntity(SubscriptionDto dto);

}
