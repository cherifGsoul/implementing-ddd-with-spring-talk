package library.lending.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class Loan {
    private final LoanId loanId;
    private final CopyId copyId;
    private final Borrower borrower;
    private final LoanPeriod period;
    private LocalDateTime returnedAt;
    private Set<LoanEvent> events;


    Loan(LoanId loanId, CopyId copyId, Borrower borrower, LoanPeriod period, LocalDateTime returnedAt) {
        this.loanId = loanId;
        this.copyId = copyId;
        this.borrower = borrower;
        this.period = period;
        this.returnedAt = returnedAt;
    }

    public Loan(CopyId copyId, Borrower borrower) {
        this.loanId = new LoanId();
        this.copyId = copyId;
        this.borrower = borrower;
        this.period = LoanPeriod.thirtyDaysStartingFromNow();
        this.recordThat(new LoanEvent.LoanCreated(this.copyId));
    }

    public static Loan reconstitute(LoanId loanId, CopyId copyId, Borrower borrower, LoanPeriod period, LocalDateTime returnedAt) {
        return new Loan(loanId, copyId, borrower, period, returnedAt);
    }

    public void returned() {
        this.returnedAt = LocalDateTime.now();
        if (this.period.isExceeded(this.returnedAt)) {
            // calculate fee
        }
        this.recordThat(new LoanEvent.LoanClosed(this.copyId));
    }

    private void recordThat(LoanEvent loanCreated) {
        events.add(loanCreated);
    }

    public Set<LoanEvent> releaseEvents() {
        return events;
    }


    public LoanId loanId() {
        return loanId;
    }

    public CopyId copyId() {
        return copyId;
    }

    public Borrower borrower() {
        return borrower;
    }

    public LocalDateTime borrowedAt() {
        return period.startAt();
    }

    public LocalDate expectedReturnDate() {
        return period.expectedReturnDate();
    }

    public LocalDateTime returnedAt() {
        return returnedAt;
    }
}
