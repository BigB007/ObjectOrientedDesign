/**
 *
 * @author Aabhas
 * Worker thread to notify the application whenever the rate limit is
 * breached.
 * This thread is submitted to a thread pool asynchronously.
 */
public class WorkerThread implements Runnable
{
    private RateLimitListener listener;
    public WorkerThread(RateLimitListener listener )
    {
        this.listener = listener;
    }
    @Override
    public void run()
    {
        this.listener.rateLimitThresholdBreached();
    }
}