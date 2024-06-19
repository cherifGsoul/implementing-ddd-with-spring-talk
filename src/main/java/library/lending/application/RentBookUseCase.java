package library.lending.application;

import library.lending.domain.*;

import java.util.Set;

public class RentBookUseCase {
    private final Loans loans;

    public RentBookUseCase(Loans loanRepository) {
        this.loans = loanRepository;
    }

    public void execute(CopyId copyId, Borrower userId) {
        // TODO: ensure rented copy is not rented again
        Loan loan = new Loan(copyId, userId);
        loans.save(loan);
        Set<LoanEvent> events = loan.releaseEvents();
        // TODO dispatch events
    }
}
