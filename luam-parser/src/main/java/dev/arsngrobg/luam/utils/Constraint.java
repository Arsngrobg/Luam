package dev.arsngrobg.luam.utils;

import java.util.Objects;
import java.util.function.Predicate;

public final class Constraint<T> {
    public static int unsignedInt(int i) throws RuntimeException {
        Constraint<Integer> constraint = new Constraint<>(_i -> _i >= 0,
            new IllegalArgumentException("must be an unsigned integer")
        );
        return constraint.check(i);
    }

    public static <T> T notNull(T obj) {
        Constraint<T> constraint = new Constraint<>(o -> o != null,
            new NullPointerException("must not be NULL")
        );
        return constraint.check(obj);
    }

    private final Predicate<T>     predicate;
    private final RuntimeException toThrowOnFailure;

    private Constraint(Predicate<T> predicate, RuntimeException toThrowOnFailure) {
        this.predicate        = Objects.requireNonNull(predicate,        "predicate cannot be NULL");
        this.toThrowOnFailure = Objects.requireNonNull(toThrowOnFailure, "toThrowOnFailure cannot be NULL");
    }

    public T check(T parameter) throws RuntimeException {
        boolean satisfiesConstraint = predicate.test(parameter);
        if (!satisfiesConstraint) {
            throw (toThrowOnFailure != null)
              ?   toThrowOnFailure
              :   new IllegalArgumentException("parameter does not satify constraint");
        }

        return parameter;
    }

    public Constraint<T> throwOnFailure(RuntimeException toThrowOnFailure) {
        return new Constraint<>(predicate, toThrowOnFailure);
    }

    @Override
    public int hashCode() {
        return predicate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Constraint<?> other)
          &&   (predicate.equals(other.predicate));
    }

    @Override
    public String toString() {
        return "Constraint<%s>".formatted(predicate);
    }
}
