import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Human extends Thread {
    volatile static boolean Running;
    volatile int life_expectancy =10;
    volatile boolean libertine;
    volatile boolean father;
    volatile boolean mother;
    static int a;
    static int b;
    static int c;
    static int fast;
    static int coy;
    static int faithful;
    static int philanderer;
    static CyclicBarrier gate=new CyclicBarrier(100);
    volatile static boolean flag = true;
    volatile static Women_stack Women_stack;
    volatile static Controller control;


    Human(boolean libertine) {
        this.libertine = libertine;
    }

    Human(int a, int b, int c, int fast, int coy, int faithful, int philanderer) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.control=new Controller();
        this.fast = fast;
        this.coy = coy;
        this.faithful = faithful;
        this.philanderer = philanderer;
        this.Women_stack=new Women_stack();
        if (fast+coy+faithful+philanderer!= 100){
         throw new IllegalStateException("Please enter valid values");
        }
        Equilibrium Eq=new Equilibrium();
        Running = true;
        Eq.start();
        make_threads();
    }

    // connect with main

    private void make_threads() {
        for (int i = 0; i <fast; i++) {
            make_threads(false,true,false,true);
        }
        for (int i = 0; i <coy; i++) {
            make_threads(false,false,true,false);
        }
        for (int i = 0; i <philanderer; i++) {
            make_threads(true,true,false,true);
        }
        for (int i = 0; i <faithful; i++) {
            make_threads(true,false,true,false);
        }
    }


    private static int kind_of_mating (Man M, Woman W) {
        int swich_parameter=0;
        if (M.libertine && !W.libertine) // H , NH
            swich_parameter = 1;
        if (M.libertine && W.libertine) //H, H
            swich_parameter = 2;
        if (!M.libertine && W.libertine)// NH,H
            swich_parameter = 3;
        if (!M.libertine && !W.libertine)//NH,NH
            swich_parameter = 4;
        return swich_parameter;
    }

    public void mating(Man M,Woman W){
        make_the_baby(M,W);
        give_take_points(M,W);
        W.wake_up();
    }
    private static boolean compute_genetics(Man M,Woman W){
        int probability=0;
        Random R=new Random();
        if (M.father)
            probability++;
        if (W.father)
            probability++;
        if (M.mother)
            probability++;
        if (W.mother)
            probability++;
        if (R.nextInt(0)<probability)
            return true;
        else
            return false;
    }

    private static void give_take_points(Man M, Woman W){
        int  case_int_points = kind_of_mating(M, W);
        switch(case_int_points){
            case 1:  // H , NH
                M.life_expectancy = M.life_expectancy;
                W.life_expectancy = W.life_expectancy;
                break;
            case 2 :   //H, H
                M.life_expectancy = M.life_expectancy+a;
                W.life_expectancy = W.life_expectancy+a-b;
                break;
            case 3: // NH,H
                M.life_expectancy = M.life_expectancy + a - b/2;
                W.life_expectancy = W.life_expectancy + a - b/2;
                break;
            case 4: //NH,NH
                M.life_expectancy = M.life_expectancy + a -b/2-c;
                W.life_expectancy = W.life_expectancy + a -b/2-c;
                break;
        }
        if (M.life_expectancy<=0){
            M.interrupt();
        }
        if (W.life_expectancy<=0){
            W.interrupt();
        }
    }

    public  void make_the_baby(Man M, Woman W){
        int case_int_baby = kind_of_mating(M, W);
        Random random=new Random();
        boolean boy= random.nextBoolean();
        if (case_int_baby!=1)
            make_threads(boy, compute_genetics(M, W), M.libertine, W.libertine);
        }
    //Overloading
    private void make_threads(boolean male,boolean libertine,boolean father,boolean mother) {
        if (male){
            Man M=new Man(libertine);
            M.father=father;
            M.mother=mother;
            M.start();
        }
        else{
            Woman W=new Woman(libertine);
            W.father=father;
            W.mother=mother;
            W.start();
        }
    }
}


