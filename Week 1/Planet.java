public class Planet extends SolarOrbiter
{
    public Planet(double diameter,String color,double distance,double velocity,SolarSystem solarSystem)
    {
        super(diameter,color,distance,velocity,solarSystem);
    }

    public void draw()
    {
        getSolarSystem().drawSolarObject(getDistance(),getAngle(),getDiameter(),getColor());
    }
}
