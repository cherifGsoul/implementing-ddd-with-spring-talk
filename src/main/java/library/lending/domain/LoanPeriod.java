package library.lending.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanPeriod(LocalDateTime startAt, LocalDate expectedReturnDate) {

    public static LoanPeriod thirtyDaysStartingFromNow() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDate expectedReturnDate = LocalDate.now().plusDays(30);
        return new LoanPeriod(startAt, expectedReturnDate);
    }

    public boolean isExceeded(LocalDateTime dt) {
        return dt.isAfter(expectedReturnDate.atStartOfDay());
    }
}
