package library.lending.application;

import library.lending.domain.Loan;
import library.lending.domain.LoanEvent;
import library.lending.domain.LoanId;
import library.lending.domain.Loans;

import java.util.Set;

public class ReturnBookUseCase {


    private final Loans loans;

    public ReturnBookUseCase(Loans loans) {
        this.loans = loans;
    }

    public void execute(LoanId loanId) throws Exception {
        Loan loan = loans.forId(loanId);
        loan.returned();
        Set<LoanEvent> events = loan.releaseEvents();
        // TODO dispatch events
    }
}
