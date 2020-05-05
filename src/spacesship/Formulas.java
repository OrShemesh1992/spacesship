package spacesship;

public class Formulas {

    public static double calcDegrees(double hs, double vs) {
        double s = calcSpeed(vs, hs);
        s = vs / s;
        return Math.toDegrees(Math.asin(s));
    }

    public static double calcHorizontalSpeed(double hs, double degrees) {
        return hs * Math.cos(degrees);
    }

    public static double calcVerticalSpeed(double vs, double degrees) {
        return vs * Math.cos(degrees);
    }

    public static double calcSpeed(double vs, double hs) {
        return Math.sqrt(Math.pow(vs, 2) + Math.pow(hs, 2));
    }

    public static double calcAccelerate (double weight, boolean main_engine, double seconds_engines) {
        double force = 0;
        if (main_engine) {
            force += Spaceship.MAIN_ENGINE_POWER;
        }
        force += seconds_engines * Spaceship.SECOND_ENGINE_POWER;
        double ans = force / weight;
        return ans;
    }
}
