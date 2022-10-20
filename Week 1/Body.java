public class Body 
{
    protected double diameter;
    protected String color;
    protected double distance;
    protected double angle = 0;

    private double velocity;
    private double angularVelocity;

    protected SolarSystem solarSystem;  //Solar System these bodies will be in

    public Body(double diameter,String color,SolarSystem solarSystem)   //Constructor for a star
    {
        this.diameter = diameter;
        this.color = color;
        this.solarSystem = solarSystem;
        distance = 0;
        velocity = 0;
    }

    public Body(double diameter,String color,double distance,double velocity,SolarSystem solarSystem)
    {
        this.diameter = diameter;
        this.color = color;
        this.distance = distance;
        this.velocity = velocity;
        this.solarSystem = solarSystem;
    }

    public double getDistance()
    {
        return(distance);
    }

    public double getAngle()
    {
        return(angle);
    }

    public void move()
    {
        calculateAngularVelocity();
        angle = angle + angularVelocity;
    }

    public void draw()
    {
        solarSystem.drawSolarObject(distance, angle, diameter, color);
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