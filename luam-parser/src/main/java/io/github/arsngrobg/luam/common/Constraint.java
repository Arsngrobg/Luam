package io.github.arsngrobg.luam.common;

/**
 * A function that ensures a specific type {@code <T>} satisfies a given predicate to ensure safe usage.
 * All constraint functions must throw an exception if it does not satisfy the constraint.
 * @param <T> the type to accept and return
 */
@FunctionalInterface
public interface Constraint<T> {
    /**
     * Test whether the given {@code obj} satisfies this contract.
     * @param obj the {@code Object} to test
     * @return the {@code obj} on success
     */
    T satisfied(T obj) throws Exception;

    /**
     * Constraint that ensures concrete type is used and not a nullable type.
     * @param <T> any {@code Object} subclass
     * @param obj an {@code Object} of type {@code <T>}
     * @throws NullPointerException
     *     if {@code obj} is {@code null}
     */
    public static <T> T notNull(T obj) {
        if (obj == null) {
            throw new NullPointerException("Expected concrete type got nullable type instead");
        }

        return obj;
    }

    /**
     * Constraint that ensures integer is semantically equivalent  to an unsigned integer.
     * @param signed a regular signed integer
     * @throws IllegalArgumentException
     *     if {@code signed} is not equivalent to an unsigned integer
     */
    public static int uint(int signed) {
        if (signed < 0) {
            throw new IllegalArgumentException("Expected uint got int instead");
        }

        return signed;
    }
}
