import java.util.HashMap;

/*
        https://leetcode.com/discuss/interview-question/object-oriented-design/1583096/API-Rate-limiter-or-LLD-(Common-Atlassian-question)
 */
public class Driver {

    private HashMap<String,RateLimiter> clientMap = new HashMap<>();

    final int MAX_REQ = 10;
    final int TIME = 1;

    void checkRequestAllowed(String uid, String time) {
        if(!clientMap.containsKey(uid)) {
            RateLimiter rateLimiter = new SlidingWindowRateLimiter(MAX_REQ,TIME);
            clientMap.put(uid, rateLimiter);
        }

        if(clientMap.get(uid).rateLimit()) {
            System.out.println(uid+" "+time+" ALLOWED");
        }
        else {
            System.out.println(uid+" "+time+" NOT ALLOWED");
        }
    }
}