public class SolarControl
{
    private SolarSystem sol;
    private Star star;
    private Planet[] planets;
    private Satellite[] satellites;

    private final int screenWidth = 640;
    private final int screenHeight = 480;

    public SolarControl(int planetCount,int satelliteCount)
    {
        sol = new SolarSystem(screenWidth,screenHeight);

        star = new Star(100,"YELLOW");

        planets = new Planet[planetCount];
        for(int i=0;i<planetCount;i++)
        {
            planets[i] = new Planet(i, null, i, i);
        }

        satellites = new Satellite[satelliteCount];




    }

    private void draw()
    {

    }

    private void move()
    {

    }

    public void mainLoop()
    {
        while(true)
        {

        }
    }
}
