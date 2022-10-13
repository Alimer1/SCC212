public class Orbiter extends CelestialBody
{
    private CelestialBody relativeAnchor;

    public Orbiter(String nName, String nColour, double nDiameter, double nRelativeAngle,double nRelativeDistance,CelestialBody nRelativeAnchor)
    {
        name = nName;
        colour = nColour;
        diameter = nDiameter;
        relativeAngle = nRelativeAngle;
        relativeDistance = nRelativeDistance;
        relativeAnchor = nRelativeAnchor; //Add a random speed variable
    }

    public void update()
    {
        relativeAngle = (relativeAngle+1) % 360;
    }

    public CelestialBody getRelativeAnchor()
    {
        return(relativeAnchor);
    }
}