package dtu.planner.ui.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface ThrowingBiConsumer<T, U> {
    void accept(T arg1, U arg2) throws IOException;
}