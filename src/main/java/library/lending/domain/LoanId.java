package library.lending.domain;

import com.sun.jdi.InvalidTypeException;

import java.util.UUID;

public record LoanId(UUID id) {

    public static LoanId fromUUID(UUID id) throws InvalidTypeException {
        if (id == null) {
            throw new InvalidTypeException("Loan id must not be null");
        }
        return new LoanId(id);
    }

    public LoanId() {
        this(UUID.randomUUID());
    }
}
