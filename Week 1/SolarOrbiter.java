public abstract class SolarOrbiter extends SolarBody
{
    private double distance;
    private double angle = Math.random()*360.0;

    private double velocity;
    private double angularVelocity;

    public SolarOrbiter(double diameter,String color,double distance,double velocity,SolarSystem solarSystem)
    {
        super(diameter,color,solarSystem);
        this.distance = distance;
        this.velocity = velocity;
    }

    public void moveAndDraw()
    {
        move();
        draw();
    }

    public void move()
    {
        calculateAngularVelocity();
        angle = angle + angularVelocity;
        angle = angle % 360;
    }

    public double getDistance()
    {
        return(distance);
    }

    public double getAngle()
    {
        return(angle);
    }

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
