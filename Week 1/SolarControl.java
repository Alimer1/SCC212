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
    private final double maxVelocity = 3.0;
    private final double minVelocity = 1.0;

    private final int starWeight        = 5;
    private final int planetWeight      = 3;
    private final int satelliteWeight   = 1; //Will occupy twice the value here since its orbit passes through the line twice

    private final double starMax        = 5.0; //Must be lower than Min and weight
    private final double starMin        = 4.0;
    private final double planetMax      = 3.0; //Must be lower than Min and weight
    private final double planetMin      = 0.8;
    private final double satelliteMax   = 1.0; //Must be lower than Min and weight
    private final double satelliteMin   = 0.2;

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
        solarBodies = randomSystem(planetCount,satelliteCount);
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

    private SolarBody[] randomSystem(int numOfPlanets,int numOfSatellites)
    {
        SolarBody[] solarBodies = new SolarBody[1+numOfPlanets+numOfSatellites];

        double totalSpace = screenHeight/2;
        int spaceCount = starWeight + (numOfPlanets*planetWeight) + (numOfSatellites*satelliteWeight*2);
        System.out.println("Space Count Is: ["+spaceCount+"]");
        double singleSpace = totalSpace/spaceCount;

        int currentSpaceCount;
        Planet lastPlanet;

        /*
        Star size will be between       3 - 2   spaces
        Planet size will be between     2 - 0.5 spaces
        Satellite size will be between  1*- 0.1 spaces
        *Always lower or equal the planet size
        */

        solarBodies[0] = new Star(randomBetween(starMin*singleSpace,starMax*singleSpace)*2.0,randomColor(), sol); //First finding the size of the stars radius then *2
        currentSpaceCount = starWeight;
        lastPlanet = randomPlanet(singleSpace, currentSpaceCount);
        solarBodies[1] = lastPlanet;
        currentSpaceCount = currentSpaceCount+planetWeight;

        int planetsLeft = numOfPlanets-1;
        int satelliteLeft = numOfSatellites;
        double planetChance;
        
        for(int i=2;i<solarBodies.length;i++)
        {
            planetChance = (double)planetsLeft/(double)(planetsLeft+satelliteLeft);
            System.out.println("Chance for a planet is :"+planetChance);
            if(Math.random()<planetChance)
            { //Create Planet
                lastPlanet = randomPlanet(singleSpace, currentSpaceCount);
                solarBodies[i] = lastPlanet;
                planetsLeft--;
                currentSpaceCount=currentSpaceCount+planetWeight;
                System.out.println("Created a Planet");
            }
            else
            { //Create Satellite
                lastPlanet.setDistance(lastPlanet.getDistance()+(singleSpace*((double)satelliteWeight)));
                currentSpaceCount=currentSpaceCount+satelliteWeight;
                solarBodies[i] = randomSatellite(singleSpace, currentSpaceCount, lastPlanet);
                satelliteLeft--;
                currentSpaceCount=currentSpaceCount+satelliteWeight;
                System.out.println("Created a Satellite");
            }
        }

        return solarBodies;
    }

    private Satellite randomSatellite(double space,int spaceCount,Planet planet)
    {
        double diameter = randomBetween(space*satelliteMin, space*satelliteMax);
        if(diameter>=planet.getDiameter())
        {
            diameter = planet.getDiameter();
        }
        double extraSpace = (space*satelliteWeight)-diameter;
        double bottomSpace = space*spaceCount;
        double extraMovement = randomBetween(0, extraSpace);
        bottomSpace = bottomSpace - planet.getDistance(); //Planet core to bottomSpace top distance
        Satellite satellite = new Satellite(diameter, randomColor(),bottomSpace+(diameter/2)+extraMovement,negativeChance(0.1)*randomBetween(minVelocity,maxVelocity),sol,planet);
        return satellite;
    }

    private Planet randomPlanet(double space,int spaceCount)
    {
        double diameter = randomBetween(space*planetMin, space*planetMax);
        double extraSpace = (space*planetWeight)-diameter;
        double bottomSpace = space*spaceCount;
        double extraMovement = randomBetween(0, extraSpace);
        Planet planet = new Planet(diameter, randomColor(),bottomSpace+(diameter/2)+extraMovement,negativeChance(0.1)*randomBetween(minVelocity,maxVelocity), sol);
        return planet;
    }

    private static String randomColor()
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

    private static double randomBetween(double min,double max)
    {
        double result = min + ((max-min)*Math.random());
        return result;
    }

    private static double negativeChance(double chance)
    {
        if(Math.random()<chance)
        {
            return -1;
        }
        return 1;
    }
}
