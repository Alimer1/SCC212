public class Satellite extends Orbiter
{
    private Planet planet;

    public Satellite(double diameter,String color,double distance,double velocity,SolarSystem solarSystem,Planet planet)
    {
        super(diameter,color,distance,velocity,solarSystem);
        this.planet = planet;
    }

    public void draw()
    {
        solarSystem.drawSolarObjectAbout(distance, angle, diameter, color, planet.getDistance(), planet.getAngle());
    }

}