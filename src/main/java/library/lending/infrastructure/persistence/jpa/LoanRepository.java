package library.lending.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface LoanRepository extends CrudRepository<JpaLoan, UUID> {
    @Query("select count(*) = 0 from Loan where copyId = :id and returnedAt is null")
    boolean isAvailable(UUID id);

    default JpaLoan findByIdOrThrow(UUID loanId) {
        return findById(loanId).orElseThrow();
    }
}
