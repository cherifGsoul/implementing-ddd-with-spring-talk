package library.lending.domain;

public sealed interface LoanEvent permits LoanEvent.LoanCreated, LoanEvent.LoanClosed {
    record LoanCreated(CopyId copyId) implements LoanEvent {
    }

    record LoanClosed(CopyId copyId) implements LoanEvent {
    }

}
