package spacesship;

public class Formulas {
	
	//https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%A6%D7%99%D7%90%D7%95%D7%9C%D7%A7%D7%95%D7%91%D7%A1%D7%A7%D7%99
    public static double calcTsiolkovsky(double v0, double ve, double m0, double mf) {
        return v0 - ve * Math.log(m0 / mf);
    }
	
	// g = (G*M)/R^2
    // G = 6.6742 * (10^-11)
    // m1 = falling body mass
    // m2 = moon mass
    // d = distance from center of heavy in moon
    // 
    public static double calcGravitationalAccelerationByNewton(double spacecraft_mass, double dis) {
    	double constant_g = 6.674 * Math.pow(10, -11);
    	double moon_mass = 7.3477 * Math.pow(10, 22);
    	return -(constant_g*spacecraft_mass*moon_mass/Math.pow(dis, 2));
    }
    
  //accelerate by Newton theory
    public static double calcAccelerationByNewton(double force, double weight){
        return force / weight;
    }
    
    // https://en.wikipedia.org/wiki/List_of_moments_of_inertia
	public static double calcDiscMoment(double radius, double mass) {
		return radius * radius * mass / 2;
	}
	
	public static double calcDisplacement(double dt, double vel, double acc) {
		return vel * dt + acc * dt * dt / 2;
	}

}

