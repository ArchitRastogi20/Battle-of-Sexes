public class Controller {
    volatile static float NHmen;
    volatile static float Hmen;
    volatile static float NH_Women;
    volatile static float H_Women;

    public double MRatio(){
        double r=NHmen/Hmen;
        return r;
    }
    public double WRatio(){
        double r=NHmen/Hmen;
        return r;
    }
    public double TRatio(){
        double r=this.WRatio()+this.MRatio();
        return r;
    }
    public void Print_population(){
        System.out.println(((Human.control.Hmen)/(Human.control.Hmen+Human.control.NHmen))*100+" philanderer");
        System.out.println(((Human.control.NHmen)/(Human.control.Hmen+Human.control.NHmen))*100+" faithfull");
        System.out.println(((Human.control.H_Women)/(Human.control.H_Women+Human.control.NH_Women))*100+" fast");
        System.out.println(((Human.control.NH_Women)/(Human.control.H_Women+Human.control.NH_Women))*100+" coy");
    }
}
