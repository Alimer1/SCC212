public class SolarControl
{
    private SolarSystem sol;
    private Star star;
    private Planet[] planets;
    private Satellite[] satellites;

    private final int screenWidth = 1280;
    private final int screenHeight = 720;

    public SolarControl(int planetCount,int satelliteCount)
    {
        sol = new SolarSystem(screenWidth,screenHeight);

        star = new Star(70,"YELLOW",sol);

        planets = new Planet[9];
        planets[0] = new Planet(12,"#B7BDB9",70,2,sol);
        planets[1] = new Planet(14,"#D9973B",90,2,sol);
        planets[2] = new Planet(16,"#49A7dE",110,2,sol);
        planets[3] = new Planet(18,"#BA7300",130,2,sol);
        planets[4] = new Planet(20,"#DBB67B",150,2,sol);
        planets[5] = new Planet(30,"#DECAAB",210,2,sol);
        planets[6] = new Planet(27,"#CFEEFF",240,2,sol);
        planets[7] = new Planet(24,"#4AACE0",270,2,sol);
        planets[8] = new Planet(5,"#858535",300,2,sol);

        satellites = new Satellite[4];
        satellites[0] = new Satellite(6, "GRAY",15, 1,sol,planets[2]);
        satellites[1] = new Satellite(6, "ORANGE",30, 1,sol,planets[5]);
        satellites[2] = new Satellite(3, "ORANGE",38, 1,sol,planets[5]);
        satellites[3] = new Satellite(5, "ORANGE",24, 1,sol,planets[6]);
    }

    private void draw()
    {
        star.draw();
        for(int i=0;i<planets.length;i++)
        {
            planets[i].draw();
        }
        for(int i=0;i<satellites.length;i++)
        {
            satellites[i].draw();
        }
    }

    private void move()
    {
        star.move();
        for(int i=0;i<planets.length;i++)
        {
            planets[i].move();
        }
        for(int i=0;i<satellites.length;i++)
        {
            satellites[i].move();
        }
    }

    public void mainLoop()
    {
        while(true)
        {
            move();
            draw();
            sol.finishedDrawing();
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }
}
