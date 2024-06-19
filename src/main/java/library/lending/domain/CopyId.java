package library.lending.domain;

import com.sun.jdi.InvalidTypeException;

import java.util.UUID;

public record CopyId(UUID id) {

    public static CopyId fromUUID(UUID id) throws InvalidTypeException {
        if (id == null) {
            throw new InvalidTypeException("Copy id must not be null");
        }
        return new CopyId(id);
    }

    public CopyId() {
        this(UUID.randomUUID());
    }
}
