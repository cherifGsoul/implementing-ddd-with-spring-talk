package library.lending.infrastructure.persistence.jpa;

import jakarta.persistence.EntityManager;
import library.lending.domain.CopyId;
import library.lending.domain.Loan;
import library.lending.domain.LoanId;
import library.lending.domain.Loans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JpaLoans implements Loans {
    private final LoanRepository repository;
    private final EntityManager entityManager;

    @Autowired
    public JpaLoans(LoanRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public boolean isAvailable(CopyId id) {
        return repository.isAvailable(id.id());
    }

    @Override
    public Loan forId(LoanId id) throws Exception {
        JpaLoan entity = repository.findByIdOrThrow(id.id());
        return entity.toLoan();
    }

    @Override
    public void save(Loan loan) {
        repository.save(JpaLoan.fromLoan(loan));
    }
}
