public class SolarDriver
{
    public static void main(String[] args)
    {
        int planetCount = 2;
        int satelliteCount = 2;

        switch(args.length)
        {
        case 1:
            planetCount = Integer.parseInt(args[0]);
            break;
        case 2:
            planetCount = Integer.parseInt(args[0]);
            satelliteCount = Integer.parseInt(args[1]);
            break;
        }
        SolarControl mainController = new SolarControl(planetCount,satelliteCount);
        mainController.mainLoop();
    }
}