public class SolarDriver
{
    public static void main(String[] args)
    {

        SolarControl mainController;

        if(args.length==0)
        {
            mainController = new SolarControl();
        }
        else
        {
            mainController = new SolarControl(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        }

        mainController.mainLoop();
    }
}