package spacesship;

public class Formulas {
    //https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%A6%D7%99%D7%90%D7%95%D7%9C%D7%A7%D7%95%D7%91%D7%A1%D7%A7%D7%99
    public static double calcTsiolkovsky(double v0, double ve, double m0, double mf) {
        return v0 - ve * Math.log(m0 / mf);
    }

    //calculate angle of velocity vector
    public static double calcAngleOfLanding(double hs, double vs) {
        double s = calcSpeed(vs, hs);
        s = vs / s;
        return Math.toDegrees(Math.asin(s));
    }

    //calculate horizontal speed by v0 and angle
    public static double calcHorizontalSpeed(double v0, double angle) {
        return v0 * Math.cos(Math.toRadians(angle));
    }

    //calculate vertical speed by v0, angle and time
    public static double calcVerticalSpeed(double v0, double angle, double t) {
        return v0 * Math.sin(Math.toRadians(angle)) + Moon.GRAVITY_ACCELERATION * t;
    }

    //calculate vector velocity from horizontal speed and vertical speed
    public static double calcSpeed(double vs, double hs) {
        return Math.sqrt(Math.pow(vs, 2) + Math.pow(hs, 2));
    }

    //calculate acceleration
    public static double calcAccelerate(double weight, boolean main_engine, double seconds_engines) {
        double force = 0;
        if (main_engine) {
            force += Spaceship.MAIN_ENGINE_POWER;
        }
        force += seconds_engines * Spaceship.SECOND_ENGINE_POWER;

        return Formulas.calcAccelerationByNewton(force, weight);
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
    	return -constant_g*spacecraft_mass*moon_mass/Math.pow(dis, 2);
    }

    //accelerate by Newton theory
    public static double calcAccelerationByNewton(double force, double weight){
        return force / weight;
    }

    public static Point calcNextPointWithOpositePower() {
        return null;
    }

    public static Point calcNextPoint(double x, double y, double gr, double vx, double vy, double t) {
        double ang = calcAngleOfLanding(vx, vy);

        double new_x = calcNextHorizontalPoint(x, gr, vx, ang, t);
        double new_y = calcNextVerticalPoint(y, gr, vy, ang, t);

        return new Point(new_x, new_y);
    }

    private static double calcNextHorizontalPoint(double x, double grav, double vx, double ang, double t) {
        double hs = calcHorizontalSpeed(vx, ang);
//        System.out.println(hs);
//        double new_x = x - hs * t - 0.5 * grav * t * t;
        double new_x = x - hs * t;
        return new_x;
    }

    private static double calcNextVerticalPoint(double y, double grav, double vy, double ang, double t) {
//        double hs = calcVerticalSpeed(vy, ang, 1);
        double vs = vy * Math.sin(Math.toRadians(ang));
        double new_y = y - vs * t - 0.5 * grav * t * t;
        return new_y;
    }
    
    public static double calcDifferenceBetweenPoints(double x, double new_x, double y, double new_y, double grav, double vx,  double vy, double ang) {
    	double diff_xtime = calcHorizontalDiffTime(x, new_x, vx, ang);
    	double diff_ytime = calcVerticalDiffTime(y, new_y, grav, vy, ang);
    	return Math.abs(diff_xtime - diff_ytime);
    }

    private static double calcHorizontalDiffTime(double x, double new_x, double vx, double ang) {
    	double hs = vx;
    	double t = (new_x - x)/hs;
    	return t;
    }
    
    private static double calcVerticalDiffTime(double y, double new_y, double grav, double vy, double ang) {
    	double vs = vy;
    	double a = -0.5 * grav;
    	double b = -vs;
    	double c = y - new_y;
        double t1, t2;

        double determinant = b * b - 4 * a * c;

        // condition for real and different roots
        if(determinant > 0) {
            t1 = (-b + Math.sqrt(determinant)) / (2 * a);
            t2 = (-b - Math.sqrt(determinant)) / (2 * a);

//            System.out.format("root1 = %.2f and root2 = %.2f", t1 , t2);
            
            if(t1>0) return t1;
            if(t2>0) return t2;
        }
        return -1;
    }
    
    public static double calcDisplacement(double dt, double vel, double acc) {
        return vel * dt + acc * dt * dt / 2;
    }
}
