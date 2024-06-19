package library.lending.domain;

public interface Loans {
    boolean isAvailable(CopyId id);

    Loan forId(LoanId id) throws Exception;

    void save(Loan loan);
}
