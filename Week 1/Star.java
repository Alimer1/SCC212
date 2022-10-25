public class Star extends SolarBody
{
    public Star(double diameter,String color,SolarSystem solarSystem)
    {
        super(diameter,color,solarSystem);
    }

    public void draw()
    {
        getSolarSystem().drawSolarObject(0,0, getDiameter(), getColor());
    }

    public void moveAndDraw()
    {
        //Star will not move
        draw();
    }
}