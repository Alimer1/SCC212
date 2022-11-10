public class SolarDriver
{
    public static void main(String[] args)
    {
        if(args.length==0)
        {
            SolarControl mainController = new SolarControl();
            mainController.mainLoop(); //Starts the animation
        }
        if(args.length==2)
        {
            SolarControl mainController = new SolarControl(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
            mainController.mainLoop(); //Starts the animation
        }
    }
}