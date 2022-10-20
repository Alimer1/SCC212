public class Body 
{
    private double diameter;
    private String color;
    private double distance;
    private double angle = 0;

    private double velocity;
    private double angularVelocity;

    public Body()
    {
        diameter = 10 + Math.random()*40;
        color = "#FFFFFF";
        distance = 75 + Math.random()*125;
        velocity = 5 + Math.random()*25;
    }

    public Body(double diameter,String color)
    {
        this.diameter = diameter;
        this.color = color;
        distance = 0;
        velocity = 0;
    }

    public Body(double diameter,String color,double distance,double velocity)
    {
        this.diameter = diameter;
        this.color = color;
        this.distance = distance;
        this.velocity = velocity;
    }

    public void move()
    {
        calculateAngularVelocity();
        angle = angle + angularVelocity;
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