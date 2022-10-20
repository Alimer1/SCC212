public class Body 
{
    protected double diameter;
    protected String color;

    protected SolarSystem solarSystem;  //Solar System these bodies will be in

    public Body(double diameter,String color,SolarSystem solarSystem)   //Constructor for a star
    {
        this.diameter = diameter;
        this.color = color;
        this.solarSystem = solarSystem;
    }

    public void draw()
    {
        solarSystem.drawSolarObject(0,0, diameter, color);
    }
}