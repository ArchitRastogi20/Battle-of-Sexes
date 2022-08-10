import java.util.concurrent.BrokenBarrierException;

public class Woman extends Human {
    //call once to make intial population
    Woman(boolean libertine) {
        super(libertine);
    }

    public synchronized void wake_up(){
        notify();
    }

    public synchronized void run() {
        try{
            if(flag) {
                gate.await();
                flag=false;
            }
        if (Running) {
            if (libertine)
                control.H_Women++;
            else
                control.NH_Women++;
        }
            while (Running) {
                if (life_expectancy>0)
                    sleep((20/life_expectancy));
                if (isInterrupted()) throw new InterruptedException();
                Women_stack.push(this);
                wait();
            }
        }
        catch(InterruptedException e){
            if (Running) {
                if (libertine){
                    control.H_Women--;}
                else{
                    control.NH_Women--;}
            }
        }
        catch(BrokenBarrierException e){}
    }
}

