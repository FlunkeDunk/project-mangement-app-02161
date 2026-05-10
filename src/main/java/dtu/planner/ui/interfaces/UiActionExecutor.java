package dtu.planner.ui.interfaces;

@FunctionalInterface
public interface UiActionExecutor {
    <T> void execute(
        ThrowingConsumer<T> action,
        T arg,
        String errorTitle
    );
}
