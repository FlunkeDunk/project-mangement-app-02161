package dtu.planner.ui.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws IOException;
}