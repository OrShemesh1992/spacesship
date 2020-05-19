package spacesship;

public class Moon {
    // from: https://he.wikipedia.org/wiki/%D7%94%D7%99%D7%A8%D7%97
    public static final double RADIUS = 3475 * 1000; // meters
    public static final double GRAVITY_ACCELERATION = 1.622;// m/s^2
    public static final double EQUIVALENT_SPEED = 1700;// m/s
	public static final double MASS= 7.3477 * Math.pow( 10, 22 ); // meters
	
	public static double getGravityForce( double mass, double height ) {
		return -Formulas.getGravityForce( mass, MASS, RADIUS + height );
	}

}

