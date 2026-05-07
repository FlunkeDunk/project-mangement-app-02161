package dtu.example.ui.interfaces;

@FunctionalInterface
public interface UiActionExecutor {
    <U, T> void execute(
        ThrowingBiConsumer<U, T> action,
        U arg,
        T arg2,
        String errorTitle
    );
}
