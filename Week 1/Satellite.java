public class Satellite extends Body
{
    private Planet planet;

    public Satellite(double diameter,String color,double distance,double velocity,Planet planet)
    {
        super(diameter,color,distance,velocity);
        this.planet = planet;
    }
}