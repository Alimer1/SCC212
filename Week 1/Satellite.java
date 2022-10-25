public class Satellite extends SolarOrbiter
{
    private Planet planet;

    public Satellite(double diameter,String color,double distance,double velocity,SolarSystem solarSystem,Planet planet)
    {
        super(diameter,color,distance,velocity,solarSystem);
        this.planet = planet;
    }

    public void draw()
    {
        getSolarSystem().drawSolarObjectAbout(getDistance(), getAngle(), getDiameter(), getColor(), planet.getDistance(), planet.getAngle());
    }
}