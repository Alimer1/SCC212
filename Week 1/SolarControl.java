public class SolarControl
{
    private SolarSystem sol;
    private SolarBody[] solarBodies;

    //480p  [640x480]   4:3
    //720p  [1280x720]  16:9
    //1080p [1920x1080] 16:9
    //1440p [2560x1440] 16:9
    //2160p [3840x2160] 16:9
    private final int screenWidth = 1280;
    private final int screenHeight = 720;

    //Randomizer Values
    private final double maxVelocity = 2.0;
    private final double minVelocity = 1.0;

    private final int starWeight        = 2;
    private final int planetWeight      = 2;
    private final int satelliteWeight   = 1; //Will occupy twice the value here since its orbit passes through the line twice

    private final double starMax        = 2.0; //Must be lower than Min and weight
    private final double starMin        = 1.0;
    private final double planetMax      = 2.0; //Must be lower than Min and weight
    private final double planetMin      = 1.2;
    private final double satelliteMax   = 1.0; //Must be lower than Min and weight
    private final double satelliteMin   = 0.5;

    /**
     * This constructor creates our current solar system.
     */
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

    /**
     * This constructor creates a random solar system with the given planet and satellite count.
     * @param planetCount
     * @param satelliteCount
     */
    public SolarControl(int planetCount,int satelliteCount)
    {
        sol = new SolarSystem(screenWidth,screenHeight);
        solarBodies = randomSystem(planetCount,satelliteCount);
    }

    /**
     * Starts the solarsystem animation. (Never ends)
     */
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

    /**
     * This method creates a random solar system with the given number of planets and sattelites.
     * @param numOfPlanets      Number of planets that are going to be in the generated solar system. (Minimum there must be 1 planet)
     * @param numOfSatellites   Number of satellites that are going to be in the generated solar system.
     * @return                  Array of all the generated planets, satellietes and the star.
     */
    private SolarBody[] randomSystem(int numOfPlanets,int numOfSatellites)
    {
        SolarBody[] solarBodies = new SolarBody[1+numOfPlanets+numOfSatellites];

        //Calculates the distance from the center of the screen to the closest side
        double totalSpace = Math.min(screenHeight, screenWidth)/2;

        //How many pieces are going to be divided to be given to the planets
        int spaceCount = starWeight + (numOfPlanets*planetWeight) + (numOfSatellites*satelliteWeight*2);
        System.out.println("Space Count Is: ["+spaceCount+"]");

        //Size of the individual pieces
        double singleSpace = totalSpace/spaceCount;

        int currentSpaceCount;
        Planet lastPlanet; //This is used by the satellites to reference the last planet.

        //Creating the star
        solarBodies[0] = new Star(randomBetween(starMin*singleSpace,starMax*singleSpace)*2.0,randomColor(), sol); //First finding the size of the stars radius then *2
        currentSpaceCount = starWeight;

        //Creating the first planet this is mandotory since a satellite requires an planet reference. There needs to be atleast one planet before we can assign satellites.
        lastPlanet = randomPlanet(singleSpace, currentSpaceCount);
        solarBodies[1] = lastPlanet;
        currentSpaceCount = currentSpaceCount+planetWeight;

        int planetLeft = numOfPlanets-1;
        int satelliteLeft = numOfSatellites;
        
        for(int i=2;i<solarBodies.length;i++)
        {
            double planetChance = (double)planetLeft/(double)(planetLeft+satelliteLeft);

            System.out.println("Chance for a planet is :"+planetChance);
            if(Math.random()<planetChance)
            { //Create Planet
                lastPlanet = randomPlanet(singleSpace, currentSpaceCount);
                solarBodies[i] = lastPlanet;

                planetLeft--;
                currentSpaceCount=currentSpaceCount+planetWeight;
                System.out.println("Created a Planet");
            }
            else
            { //Create Satellite
                /*
                 * Pushes the last planet by the orbit area a satellite will occupy. This is done because the create random satellite method add the satellite to the top of the planet.
                 * So we need to push the planet up first since a satellites orbit goes through the "AREA" twice. From bothe bottom and the top.
                 */
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

    /**
     * Returns a random planet in the given space and connected to the given planet.
     * @param space         Individual space size.
     * @param spaceCount    Current space count.
     * @param planet        Planet that the satellite will orbit.
     * @return              Random planet.
     */
    private Satellite randomSatellite(double space,int spaceCount,Planet planet)
    {
        double diameter = randomBetween(space*satelliteMin, space*satelliteMax);
        //Making sure that the satellite is smaller than the planet.
        if(diameter>=planet.getDiameter())
        {
            diameter = planet.getDiameter();
        }
        //Extra space is the space left after we find the diameter.
        double extraSpace = (space*satelliteWeight)-diameter;
         //Bottom space is the space under this satellite.
        double bottomSpace = space*spaceCount;
        //Extra movement is for the random placement of the satellite given the space left for it.
        double extraMovement = randomBetween(0, extraSpace);
        //Making bottom space be the distance from the planet rather than the star
        bottomSpace = bottomSpace - planet.getDistance();
        Satellite satellite = new Satellite(diameter, randomColor(),bottomSpace+(diameter/2)+extraMovement,negativeChance(0.1)*randomBetween(minVelocity,maxVelocity),sol,planet);
        return satellite;
    }

    /**
     * Returns a random planet in the given space and far by the given space count.
     * @param space         Individual space size.
     * @param spaceCount    Current space count.
     * @return              Random planet.
     */
    private Planet randomPlanet(double space,int spaceCount)
    {
        double diameter = randomBetween(space*planetMin, space*planetMax);
        //Extra space is the space left after we find the diameter.
        double extraSpace = (space*planetWeight)-diameter;
        //Bottom space is the space under this planet.
        double bottomSpace = space*spaceCount;
        //Extra movement is for the random placement of the planet given the space left for it.
        double extraMovement = randomBetween(0, extraSpace);
        Planet planet = new Planet(diameter, randomColor(),bottomSpace+(diameter/2)+extraMovement,negativeChance(0.1)*randomBetween(minVelocity,maxVelocity), sol);
        return planet;
    }

    /**
     * Returns a random hex color code as a String.
     * @return  Random hex color code.
     */
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

    /**
     * Returns a random double number between min and max. (max must be higher than min)
     * @param min   Low number.
     * @param max   High number.
     * @return      Random number.
     */
    private static double randomBetween(double min,double max)
    {
        double result = min + ((max-min)*Math.random());
        return result;
    }

    /**
     * Returns a negative 1 or positive 1 with the given chace.
     * @param chance    Chance for -1. (Must be between 0-1)
     * @return          -1 or 1 value randomly by the given chance.
     */
    private static double negativeChance(double chance)
    {
        if(Math.random()<chance)
        {
            return -1;
        }
        return 1;
    }
}
