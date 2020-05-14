package spacesship;

public class Formulas {
//https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%A6%D7%99%D7%90%D7%95%D7%9C%D7%A7%D7%95%D7%91%D7%A1%D7%A7%D7%99
	public static double calcTsiolkovsky(double v0,double ve,double m0,double mf ) {
		return v0-ve*Math.log(m0/mf);
	}
    public static double calcDegreesOfLanding(double hs, double vs) {
        double s = calcSpeed(vs, hs);
        s = vs / s;
        return Math.toDegrees(Math.asin(s));
    }

    public static double calcHorizontalSpeed(double hs, double degrees) {
        return hs * Math.cos(Math.toRadians(degrees));
    }

    public static double calcVerticalSpeed(double vs, double degrees, double t) {
        return vs * Math.sin(Math.toRadians(degrees)) + Moon.GRAVITY_ACCELERATION * t;
    }

    public static double calcSpeed(double vs, double hs) {
        return Math.sqrt(Math.pow(vs, 2) + Math.pow(hs, 2));
    }

    public static double calcAccelerate(double weight, boolean main_engine, double seconds_engines) {
        double force = 0;
        if (main_engine) {
            force += Spaceship.MAIN_ENGINE_POWER;
        }
        force += seconds_engines * Spaceship.SECOND_ENGINE_POWER;
        double ans = force / weight;
        return ans;
    }

    public static Point calcNextPointWithOpositePower() {
        return null;
    }

    public static Point calcNextPoint(double x, double y, double gr, double vx, double vy, double t) {
        double deg = calcDegreesOfLanding(vx, vy);

        double new_x = calcNextHorizontalPoint(x, gr, vx, deg, t);
        double new_y = calcNextVerticalPoint(y, gr, vy, deg, t);

        return new Point(new_x, new_y);
    }

    private static double calcNextHorizontalPoint(double x, double grav, double vx, double deg, double t) {
        double hs = calcHorizontalSpeed(vx, deg);
//        System.out.println(hs);
//        double new_x = x - hs * t - 0.5 * grav * t * t;
        double new_x = x - hs * t;
        return new_x;
    }

    private static double calcNextVerticalPoint(double y, double grav, double vy, double deg, double t) {
//        double hs = calcVerticalSpeed(vy, deg, 1);
        double vs = vy * Math.sin(Math.toRadians(deg));
        double new_y = y - vs * t - 0.5 * grav * t * t;
        return new_y;
    }
	public static double getDisplacement( double dt, double vel, double acc ) {
		return vel * dt + acc * dt * dt / 2;
	}
}
