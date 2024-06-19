package library.lending.infrastructure.persistence.jpa;

import com.sun.jdi.InvalidTypeException;
import jakarta.persistence.*;
import library.lending.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class JpaLoan {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "copy_id", nullable = false)
    private UUID copyId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private LocalDateTime createdAt;
    private LocalDate expectedReturnDate;
    private LocalDateTime returnedAt;

    @Version
    private Long version;

    public static JpaLoan fromLoan(Loan loan) {
        JpaLoan entity = new JpaLoan();
        entity.id = loan.loanId().id();
        entity.copyId = loan.copyId().id();
        entity.userId = loan.borrower().id();
        entity.createdAt = loan.borrowedAt();
        entity.expectedReturnDate = loan.expectedReturnDate();
        entity.returnedAt = loan.returnedAt();
        return entity;
    }

    public Loan toLoan() throws InvalidTypeException {
        return Loan.reconstitute(
                LoanId.fromUUID(this.id),
                CopyId.fromUUID(this.copyId),
                Borrower.fromUUID(this.userId),
                new LoanPeriod(this.createdAt, this.expectedReturnDate),
                this.returnedAt
        );
    }


}
