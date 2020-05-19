package spacesship;
/**
 * 
 * @author Or Shemesh
 *
 */
public class Formulas {

	public static final double GRAVITY = 6.674 * Math.pow( 10, -11 );

	public static double getGravityForce( double mass1, double mass2, double distance ) {
		return GRAVITY * mass1 * mass2 / Math.pow( distance, 2 );
	}

	public static double getDisplacement( double dt, double vel, double acc ) {
		return vel * dt + acc * dt * dt / 2;
	}

	public static double getVelocity( double dt, double acc ) {
		return acc * dt;
	}

	public static double getForceNewton2( double mass, double acc ) {
		return mass * acc;
	}

	public static double getAccNewton2( double force, double mass ) {
		return force / mass;
	}

	public static double getMassNewton2( double force, double acc ) {
		return force / acc;
	}

	public static double getTorque( double radius, double force ) {
		return radius * force;
	}

	public static double getDiscMoment( double radius, double mass ) {
		return radius * radius * mass / 2;
	}

}

