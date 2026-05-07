package dtu.example.ui.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface ThrowingConsumer<T> {
    void accept(T arg) throws IOException;
}
