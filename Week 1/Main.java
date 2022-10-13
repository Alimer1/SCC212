public class Main
{
    public static void main(String[] args)
    {
        int planetCount = 4;

        if(args.length == 1)
        {
            planetCount = Integer.valueOf(args[0]);
        }

        CelestialController mainController = new CelestialController(planetCount);
        mainController.mainLoop();
    }
}