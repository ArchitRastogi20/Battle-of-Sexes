import java.util.concurrent.BrokenBarrierException;

public class Man extends Human {


    //call make_threads first time to initial population

    Man(boolean libertine) {
        super(libertine);
    }

    public void run() {
        try{
            if(flag) {
                gate.await();
                flag=false;
            }
        if (Running) {
            if (libertine)
                control.Hmen++;
            else
                control.NHmen++;
        }
            while (Running) {
                if (life_expectancy>0)
                    sleep((20/life_expectancy));
                if (isInterrupted()) throw new InterruptedException();
                try {
                    Woman chosen_woman = Women_stack.pop();
                    mating(this, chosen_woman);
                }
                catch(IndexOutOfBoundsException e){
                }
            }
        } catch (InterruptedException e) {
            if (Running) {
                if (libertine)
                    control.Hmen--;
                else
                    control.NHmen--;
            }
        }
        catch(BrokenBarrierException e){}
    }
}