import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowRateLimiter extends RateLimiter{

    private final ConcurrentMap<Long,AtomicInteger> window = new ConcurrentHashMap<>();


    protected SlidingWindowRateLimiter(int requests, int time) {
        super(requests,time);
    }

    @Override
    boolean rateLimit() {
        long curTime = System.currentTimeMillis();
        long curwkey = curTime/1000*1000;

        window.putIfAbsent(curwkey, new AtomicInteger(0));
        long previousWkey = curwkey-1000;
        AtomicInteger preCount = window.get(previousWkey);

        if(preCount==null)
            return window.get(curwkey).incrementAndGet()<=(requestsAllowed/timeAllowed);

        double previouswt = 1-(curTime-curwkey)/1000.0;
        long count = (long) (preCount.get() * previouswt + window.get(curwkey).incrementAndGet());
        return count <= (requestsAllowed/timeAllowed);
    }
}
