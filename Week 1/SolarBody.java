public abstract class SolarBody 
{
    private double diameter;
    private String color;
    private SolarSystem solarSystem;

    public SolarBody(double diameter,String color,SolarSystem solarSystem)
    {
        this.diameter = diameter;
        this.color = color;
        this.solarSystem = solarSystem;
    }

    public double getDiameter()
    {
        return(diameter);
    }

    public String getColor()
    {
        return(color);
    }

    public SolarSystem getSolarSystem()
    {
        return(solarSystem);
    }

    public abstract void draw();

    public abstract void moveAndDraw();
}