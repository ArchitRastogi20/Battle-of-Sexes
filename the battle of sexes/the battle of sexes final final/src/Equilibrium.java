import java.lang.*;
import java.sql.SQLOutput;

public class Equilibrium extends Thread {
    // we are gonna save the for x ms
    double previous_ratio=0;
    int counter=0;
    float time=0;
    double delta=0.3;
    int sleeptime=5;
    public void run() {
        try {
            while (Human.Running) {
                sleep(sleeptime);
                time++;
                double m_ratio=Human.control.TRatio();
                if (Math.abs(previous_ratio-m_ratio)<=delta) {
                    counter++;
                    delta=Math.abs(previous_ratio-m_ratio);
                }
                else {
                    counter = 0;
                    delta = 0.3;
                }
                previous_ratio=m_ratio;


                if (counter==20) {
                    Human.control.Print_population();
                    this.interrupt();
               }
            }
        }
        catch(InterruptedException e) {
            System.out.println("the equilibrium was found after "+((time*sleeptime)/1000)+" seconds");
            System.exit(0);
        }
        }
    }
