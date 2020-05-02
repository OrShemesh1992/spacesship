package spacesship;

public class Spaceship {

    public static final double WEIGHT_CRAFT = 165; // kg
    public static final double WEIGHT_FUEL = 420; // kg
    public static final double WEIGHT_FULL = WEIGHT_CRAFT + WEIGHT_FUEL; // kg
    public static final double MAIN_ENGINE_POWER = 430; // N
    public static final double SECOND_ENGINE_POWER = 25; // N
    public static final double MAIN_ENGINE_CONSUMPTION = 0.15; //liter per sec, 12 liter per m'
    public static final double SECOND_ENGINE_CONSUMPTION = 0.009; //liter per sec 0.6 liter per m'
    public static final double ALL_ENGINE_START = MAIN_ENGINE_CONSUMPTION + 8 * SECOND_ENGINE_CONSUMPTION;

    private double vertical_speed;
    private double horizontal_speed;
    private double distance_from_earth;
    private double angle;
    private double altitude_from_moon;
    private double dt;
    private double accelerate;
    private double landing_fuel;
    private double actual_weight;
    private double NN = 0.7;


    public Spaceship(double vs, double hs, double dfe, double ang, double alt, double dt, double acc, double lf){
        vertical_speed = vs;
        horizontal_speed = hs;
        distance_from_earth = dfe;
        angle = ang;
        altitude_from_moon = alt;
        this.dt = dt;
        accelerate = acc;
        landing_fuel = lf;
        actual_weight = WEIGHT_CRAFT + landing_fuel;
    }

    public double getVerticalSpeed() {
        return vertical_speed;
    }

    public void changeVerticalSpeed(double fix) {
        vertical_speed += fix;
    }

    public double getHorizontalSpeed() {
        return horizontal_speed;
    }

    public void setHorizontalSpeed(double hs){
        horizontal_speed = hs;
    }

    public void changeHorizontalSpeed(double fix){
        horizontal_speed += fix;
    }

    public double getDistanceFromEarth() {
        return distance_from_earth;
    }

    public void changeDistanceFromEarth(double fix){
        distance_from_earth += fix;
    }

    public double getAngle() {
        return angle;
    }

    public void fixAngle(double fix){
        angle += fix;
    }

    public void setAngle(double ang){
        angle = ang;
    }

    public double getAltitudeFromMoon() {
        return altitude_from_moon;
    }

    public void changeAltitudeFromMoon(double fix) {
        altitude_from_moon += fix;
    }

    public double getDt() {
        return dt;
    }

    public double getAccelerate() {
        return accelerate;
    }

    public void setAccelerate(double acc){
        accelerate = acc;
    }

    public void changeAccelerate(double fix){
        accelerate += fix;
    }

    public double getLandingFuel(){
        return landing_fuel;
    }

    public void setLandingFuel(double lf){
        landing_fuel = lf;
    }

    public void changeLandingFuel(double fix){
        landing_fuel += fix;
    }

    public double getActualWeight() {
        return actual_weight;
    }

    public void setActualWeight(double w){
        actual_weight = w;
    }

    public void changeActualWeight(double fix){
        actual_weight += fix;
    }

    public double getNN() {
        return NN;
    }

    public void setNN(double nn) {
        NN = nn;
    }

    public void changeNN(double nn) {
        NN += nn;
    }
}
