package easymvp.usecase;

import easymvp.executer.PostExecutionThread;
import easymvp.executer.UseCaseExecutor;
import io.reactivex.Scheduler;

import javax.annotation.Nullable;

/**
 * Each {@code UseCase} of the system orchestrate the flow of data to and from the entities.
 * <p>
 * Outer layers of system can execute use cases by calling {@link #execute(Object)}} method. Also
 * you can use {@link #useCaseExecutor} to execute the job in a background thread and {@link
 * #postExecutionThread} to post the result to another thread(usually UI thread).
 *
 * @param <P> The response type of a use case.
 * @param <Q> The request type of a use case.
 * Created by megrez on 2017/3/12.
 */
public abstract class UseCase<P,Q> {
    private final UseCaseExecutor useCaseExecutor;
    private final PostExecutionThread postExecutionThread;

    public UseCase(UseCaseExecutor useCaseExecutor,
                   PostExecutionThread postExecutionThread) {
        this.useCaseExecutor = useCaseExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Executes use case. It should call {@link #interact(Object)} to get response value.
     */
    public abstract P execute(@Nullable Q param);

    /**
     * A hook for interacting with the given parameter(request value) and returning a response value for
     * each concrete implementation.
     * <p>
     * It should be called inside {@link #execute(Object)}.
     *
     * @param param The request value.
     * @return Returns the response value.
     */
    protected abstract P interact(@Nullable Q param);

    public Scheduler getUseCaseExecutor() {
        return useCaseExecutor.getScheduler();
    }

    public Scheduler getPostExecutionThread() {
        return postExecutionThread.getScheduler();
    }
}
