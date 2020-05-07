package spacesship;

public class Formulas {

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
        double hs = vy * Math.sin(Math.toRadians(deg));
        double new_y = y - hs * t - 0.5 * grav * t * t;
        return new_y;
    }
}
