package library.lending.domain;

import com.sun.jdi.InvalidTypeException;

import java.util.UUID;

public record Borrower(UUID id) {

    public static Borrower fromUUID(UUID id) throws InvalidTypeException {
        if (id == null) {
            throw new InvalidTypeException("Borrower id must not be null");
        }
        return new Borrower(id);
    }

}
