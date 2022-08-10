import java.util.ArrayList;
import java.util.List;

public class Women_stack {
    volatile public static List<Woman> woman_list=new ArrayList<Woman>();
    public synchronized void push(Woman Woman) {
        if (is_empty() && Human.Running) {
            notifyAll();
        }
        if (!Human.Running) {
            Woman.interrupt();
        }
        woman_list.add(0,Woman);
    }

    public synchronized Woman pop() throws InterruptedException,IndexOutOfBoundsException {
        //shuffle();
        while (is_empty() && Human.Running) {
            wait();
        }
        return woman_list.remove(0);
    }

    private synchronized boolean is_empty(){
        return woman_list.isEmpty();
    }
}

