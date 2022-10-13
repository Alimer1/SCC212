public class CelestialController
{
    private Anchor mainAnchor;
    private Orbiter[] orbiters;
    private CelestialBody[] celestialBodies;
    private SolarSystem mainSystem = new SolarSystem(1000,1000);


    public CelestialController(int orbiterCount)
    {
        mainAnchor = new Anchor("Sun","#FFFF00",100,0,0);
        orbiters = new Orbiter[orbiterCount];

        int celestialSelection = orbiterCount + 1;
        celestialBodies = new CelestialBody[celestialSelection];
        celestialBodies[0] = mainAnchor;

        for(int i=0;i<orbiterCount;i++)
        {
            String customName = "Planet: "+(i+1);

            String randomColour = "#00FF00";

            System.out.println(randomColour);
            double randomDiameter = Math.random()*100;
            double randomDistance = 200 + (Math.random()*75);
            CelestialBody celestialAnchor = celestialBodies[(int) Math.random()*(i+1)];
            orbiters[i] = new Orbiter(customName, randomColour, randomDiameter, 0,randomDistance,celestialAnchor);
            celestialBodies[i+1] = orbiters[i];
        }
    }

    private void draw()
    {

        mainSystem.drawSolarObject
        (
            mainAnchor.getRelativeDistance(),
            mainAnchor.getRelativeAngle(),
            mainAnchor.getDiameter(),
            mainAnchor.getColour()
        );

        for(int i=0;i<orbiters.length;i++)
        {
            mainSystem.drawSolarObjectAbout
            (
                orbiters[i].getRelativeDistance(), 
                orbiters[i].getRelativeAngle(), 
                orbiters[i].getDiameter(),
                orbiters[i].getColour(), 
                orbiters[i].getRelativeAnchor().getRelativeDistance(), 
                orbiters[i].getRelativeAnchor().getRelativeAngle()
            );
        }
        mainSystem.finishedDrawing();
    }

    private void move()
    {
        for(int i=0;i<orbiters.length;i++)
        {
            orbiters[i].update();
        }
    }

    public void mainLoop()
    {
        while(true)
        {
            move();
            draw();
        }
    }
}
