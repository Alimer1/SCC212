public abstract class SolarBody 
{
    private double diameter;
    private String color;
    private SolarSystem solarSystem;

    /**
     * 
     * @param diameter      diameter of the solar body.
     * @param color         color of the solar body as hex string.
     * @param solarSystem   solar system that the soalr body will exist in.
     */
    public SolarBody(double diameter,String color,SolarSystem solarSystem)
    {
        this.diameter = diameter;
        this.color = color;
        this.solarSystem = solarSystem;
    }

    /**
     * Returns the diameter of the solar body.
     * @return  diameter.
     */
    public double getDiameter()
    {
        return(diameter);
    }

    /**
     * Returns the color of the solar body as string.
     * @return color as hex string.
     */
    public String getColor()
    {
        return(color);
    }

    /**
     * Returns the solar system reference.
     * @return  Solar system reference.
     */
    public SolarSystem getSolarSystem()
    {
        return(solarSystem);
    }

    public abstract void draw();

    public abstract void moveAndDraw();
}