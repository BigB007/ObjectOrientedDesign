package pub_sub_queue.public_interface;

public interface ISubscriber {

    String getId();
    void consume(Message message) throws InterruptedException;
}