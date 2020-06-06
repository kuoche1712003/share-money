package io.kuoche.share.presenter.config;

import io.kuoche.share.core.usercase.activity.ActivityRepository;
import io.kuoche.share.core.usercase.activity.CreateActivityUseCase;
import io.kuoche.share.core.usercase.activity.GetActivityUseCase;
import io.kuoche.share.core.usercase.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    public CreateActivityUseCase createActivityUseCase(ActivityRepository repository){
        return new CreateActivityUseCase(repository);
    }

    @Bean
    public GetActivityUseCase getActivityUseCase(ActivityRepository repository){
        return new GetActivityUseCase(repository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(
            ParticipatorRepository participatorRepository,
            OrderRepository repository){
        return new CreateOrderUseCase(participatorRepository, repository);
    }
    @Bean
    public GetActivityOrdersUseCase getActivityOrdersUseCase(OrderRepository repository){
        return new GetActivityOrdersUseCase(repository);
    }

    @Bean
    public GetActivityDebtUseCase getActivityDebtUseCase(GetActivityOrdersUseCase getActivityOrdersUseCase){
        return new GetActivityDebtUseCase(getActivityOrdersUseCase);
    }

}
