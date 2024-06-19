package library.lending.infrastructure.spring;

import library.lending.application.RentBookUseCase;
import library.lending.application.ReturnBookUseCase;
import library.lending.domain.Loans;
import org.springframework.context.annotation.Bean;

public class LendingConfig {
    @Bean
    public RentBookUseCase rentBookUseCase(Loans loans) {
        return new RentBookUseCase(loans);
    }

    @Bean
    public ReturnBookUseCase returnBookUseCase(Loans loans) {
        return new ReturnBookUseCase(loans);
    }
}
