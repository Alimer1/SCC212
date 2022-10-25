public class SolarControl
{
    private SolarSystem sol;
    private SolarBody[] solarBodies;

    //480p  [640x480]   4:3
    //720p  [1280x720]  16:9
    //1080p [1920x1080] 16:9
    //1440p [2560x1440] 16:9
    //2160p [3840x2160] 16:9
    private final int screenWidth = 2560;
    private final int screenHeight = 1440;

    //Randomizer Values
    private final double maxVelocity = 2.0;
    private final double minVelocity = 1.0;

    public SolarControl()
    {
        sol = new SolarSystem(screenWidth,screenHeight);

        solarBodies = new SolarBody[1+9+4];

        //Star
        solarBodies[0] = new Star(70,"#F28322",sol);

        //Planets
        solarBodies[1] = new Planet(12,"#595656",70,1,sol);
        solarBodies[2] = new Planet(14,"#88898C",90,1,sol);
        solarBodies[3] = new Planet(16,"#023373",110,1,sol);
        solarBodies[4] = new Planet(18,"#F27A5E",130,1,sol);
        solarBodies[5] = new Planet(20,"#BFAE99",150,1,sol);
        solarBodies[6] = new Planet(30,"#F2CD88",210,1,sol);
        solarBodies[7] = new Planet(27,"#95BBBF",260,1,sol);
        solarBodies[8] = new Planet(24,"#7595BF",310,1,sol);
        solarBodies[9] = new Planet(5,"#A69E94",360,-1,sol);

        //Satellites
        solarBodies[10] = new Satellite(6, randomColor(),15, 1,sol,(Planet) solarBodies[3]);
        solarBodies[11] = new Satellite(6, randomColor(),30, -1,sol,(Planet) solarBodies[6]);
        solarBodies[12] = new Satellite(3, randomColor(),38, 1,sol,(Planet) solarBodies[6]);
        solarBodies[13] = new Satellite(5, randomColor(),24, 1,sol,(Planet) solarBodies[7]);
    }

    public SolarControl(int planetCount,int satelliteCount)
    {
        sol = new SolarSystem(screenWidth,screenHeight);
        solarBodies = randomPlanets(planetCount,satelliteCount);
    }

    public void mainLoop()
    {
        while(true)
        {
            for(int i=0;i<solarBodies.length;i++)
            {
                solarBodies[i].moveAndDraw();
            }

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

    private String randomColor()
    {
        String color;
        color = "#";
        for(int i=0;i<6;i++)
        {
            color = color+Integer.toHexString((int)(Math.random()*16));
        }
        color = color.toUpperCase();
        return color;
    }

    private double randomVelocity()
    {
        double velocity;
        velocity = minVelocity + ((maxVelocity-minVelocity)*Math.random());
        if(Math.random()>0.1)
        {
            velocity = velocity*(-1);
        }
        return velocity;
    }

    private SolarBody[] randomPlanets(int numOfPlanets,int numOfSatellites)
    {
        SolarBody[] solarBodies = new SolarBody[1+numOfPlanets+numOfSatellites];

        /**
        So first we divide the heigh of the shortes distance from the center of the screen to the sides.
        giving us the distance from the center to the closest edge.
        we divide this region to the number of planets and +2(for the star in the middle)
        so for a system with 4 planets we crate this.

        -I---------I    Planet 4 Region
        -I-------I      Planet 3 Region
        -I-----I        Planet 2 Region
        -I---I          Planet 1 Region
        -I-I            Star Region
        -I-I            Star Region

        We than create a star with the size of minimum 1 maximum 2 regions.
        This makes sure that the star is the biggest thing in the solar system.
        Then we crate planets with minimum 1/10 of a region and maximum 1 regions.
        This makes sure that planets can't be to small while making sure they fit their regions.
        Then we calculate the empty space left in their region with their size.
        Then we move them up and down that distance randomly.
         
        **/

        int regions = 2 + numOfPlanets;
        double totalArea = Math.min(screenHeight,screenWidth)/2;
        double regionArea = totalArea/regions;

        System.out.println("Region Area: "+regionArea);

        double starArea = regionArea + (Math.random()*regionArea);
        solarBodies[0] = new Star(starArea*2,randomColor(),sol);

        for(int i = 0;i<numOfPlanets;i++)
        {
            //Calculating Planet Area
            double planetArea = (regionArea*(1.0/10.0))+(regionArea*(8.0/10.0)*Math.random()); //A planet can occupy 10% to 90% of the given region;

            //Calculating Planet Color
            String color = randomColor();

            //Calculating Planet Distance From Star
            int bottomRegion = 2+i;
            double bottomRegionArea = bottomRegion*regionArea;
            double extraSpace = regionArea-planetArea;
            double distanceFromStar = bottomRegionArea + (planetArea/2) + (extraSpace*Math.random());

            //Calculating Planet Velocity
            double velocity = randomVelocity();

            //Creating the Planet
            solarBodies[1+i] = new Planet(planetArea, color, distanceFromStar, velocity, sol);

            System.out.print("\nCreating Planet ["+i+"]\n");
            System.out.println("Planet Area: "+planetArea);
            System.out.println("Planet Color: "+color);
            System.out.println("Distance From Star: "+distanceFromStar);
            System.out.println("Planet Velocity: "+velocity);
        }

        for(int i = 0;i<numOfSatellites;i++)
        {
            //Calculating Satellite Area
            int targetPlanetNumber = 1+((int)(numOfPlanets*Math.random()));
            Planet targetPlanet = (Planet) solarBodies[targetPlanetNumber];
            double topGap;
            double bottomGap;
            double minGap;

            if(targetPlanetNumber==numOfPlanets)
            {
                topGap = totalArea-(targetPlanet.getDistance()+(targetPlanet.getDiameter()/2));
            }
            else
            {
                Planet topPlanet = (Planet) solarBodies[targetPlanetNumber+1];
                topGap = (topPlanet.getDistance()-(topPlanet.getDiameter()/2))-(targetPlanet.getDistance()+(targetPlanet.getDiameter()/2));
            }
            if(targetPlanetNumber==1)
            {
                bottomGap = (targetPlanet.getDistance()-(targetPlanet.getDiameter()/2))-(solarBodies[0].getDiameter()/2);
            }
            else
            {
                Planet bottomPlanet = (Planet) solarBodies[targetPlanetNumber-1];
                bottomGap = (targetPlanet.getDistance()-(targetPlanet.getDiameter()/2))-(bottomPlanet.getDistance()+(bottomPlanet.getDiameter()/2));
            }
            minGap = Math.min(topGap, bottomGap); //Area around this planet that is empty
            double satelliteArea = (minGap*(1.0/10.0))+(minGap*(5.0/10.0)*Math.random()); //A satellite can occupy 10% to 60% of the given region;
            if(satelliteArea>targetPlanet.getDiameter())
            {
                satelliteArea = targetPlanet.getDiameter();
            }

            //Calculating Satellite Color
            String color = randomColor();

            //Calculating Satellite Distance From Planet
            double extraSpace = minGap-satelliteArea;
            double distanceFromPlanet = (targetPlanet.getDiameter()/2)+(satelliteArea/2)+(extraSpace*(5.0/10.0))+(extraSpace*(5.0/10.0)*Math.random());

            //Calculating Satellite Velocity
            double velocity = randomVelocity();

            //Creating the Satellite
            solarBodies[1+numOfPlanets+i] = new Satellite(satelliteArea, color, distanceFromPlanet, velocity, sol,targetPlanet);

            System.out.print("\nCreating Satellite ["+i+"] of Planet ["+(targetPlanetNumber-1)+"]\n");
            System.out.println("Satellite Area: "+satelliteArea);
            System.out.println("Satellite Color: "+color);
            System.out.println("Distance From Planet: "+distanceFromPlanet);
            System.out.println("Satellite Velocity: "+velocity);
        }

        return(solarBodies);
    }
}
