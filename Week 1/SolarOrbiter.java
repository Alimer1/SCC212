public abstract class SolarOrbiter extends SolarBody
{
    private double distance;
    private double angle = Math.random()*360.0;

    private double velocity;
    private double angularVelocity;

    /**
     * 
     * @param diameter
     * @param color
     * @param distance Distance from the orbited object.
     * @param velocity
     * @param solarSystem
     */
    public SolarOrbiter(double diameter,String color,double distance,double velocity,SolarSystem solarSystem)
    {
        super(diameter,color,solarSystem);
        this.distance = distance;
        this.velocity = velocity;
    }

    /**
     * Calls the move and call functions
     */
    public void moveAndDraw()
    {
        move();
        draw();
    }

    /**
     * Moves the postion of the orbiter depending on its angular velocity
     */
    public void move()
    {
        calculateAngularVelocity();
        angle = angle + angularVelocity;
        angle = angle % 360;
    }

    /**
     * Returns the distance.
     * @return the distance.
     */
    public double getDistance()
    {
        return(distance);
    }

    /**
     * Sets the distance.
     * @param distance the distance.
     */
    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    /**
     * Returns the angle.
     * @return the angle.
     */
    public double getAngle()
    {
        return(angle);
    }

    /**
     * Calculates the angular velocity
     */
    private void calculateAngularVelocity()
    {
        if(velocity == 0)
        {
            angularVelocity = 0;
        }
        else
        {
            double time = (2*Math.PI*distance)/velocity;
            angularVelocity = 360/time;
        }
    }
}
