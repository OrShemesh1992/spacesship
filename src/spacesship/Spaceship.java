package spacesship;


//another pid controller code - https://github.com/bryankate/simbeeotic/blob/master/simbeeotic-core/src/main/java/harvard/robobees/simbeeotic/util/PIDController.java
public class Spaceship {

    public static final double WEIGHT_CRAFT = 165; // kg
    public static final double WEIGHT_FUEL = 420; // kg
    public static final double WEIGHT_FULL = WEIGHT_CRAFT + WEIGHT_FUEL; // kg
    public static final double MAIN_ENGINE_POWER = 430; // N
    public static final double SECOND_ENGINE_POWER = 25; // N
    public static final double MAIN_ENGINE_CONSUMPTION = 0.15; //liter per sec, 12 liter per m'
    public static final double SECOND_ENGINE_CONSUMPTION = 0.009; //liter per sec 0.6 liter per m'
    public static final double ALL_ENGINE_START = MAIN_ENGINE_CONSUMPTION + 8 * SECOND_ENGINE_CONSUMPTION;


    private double start_distance_from_destination;
    private double start_altitude_from_moon;


    private double distance_from_destination;
    private double altitude_from_moon;
    private double dt;
    private double vertical_speed;
    private double accelerate_y;
    private double horizontal_speed;
    private double accelerate_x;
    private double angle;
    private double angle_speed;
    private double angle_accelerate; //[d/s ^2]
    private double accelerate;
    private double fuel_amount;
    private double actual_weight;
    private double NN = 0.7;
    private PID pid_controller;


    public Spaceship(double vs, double hs, double dfe, double ang, double alt, double dt, double acc, double fl) {
        vertical_speed = vs;
        horizontal_speed = hs;
        distance_from_destination = dfe;
        angle = ang;
        altitude_from_moon = alt;
        this.dt = dt;
        accelerate = acc;
        fuel_amount = fl;
        actual_weight = WEIGHT_CRAFT + fuel_amount;
        createController();

        start_distance_from_destination = distance_from_destination;
        start_altitude_from_moon = altitude_from_moon;
        CSV.write( "Time,Angle,Power");
    }

//    private void createController() {
//        pid_controller = new PID(0.25, 0.01, 0.4);
//        pid_controller.setOutputLimits(1);
//        //miniPID.setMaxIOutput(2);
//        //miniPID.setOutputRampRate(3);
//        //miniPID.setOutputFilter(.3);
//        pid_controller.setSetpointRange(0.1);
//    }

    private void createController() {
        pid_controller = new PID(0, 1, 0.0, 0.0);
    }

    public void printInfo() {
        System.out.printf(
                "time: %3.2f, vs: %3.2f, hs: %3.2f, dist: %3.2f, alt: %3.2f, ang: %3.2f, wgt: %3.2f, acc: %3.2f, nn: %3.2f\n",
                Utils.round(dt),
                Utils.round(vertical_speed),
                Utils.round(horizontal_speed),
                Utils.round(distance_from_destination),
                Utils.round(altitude_from_moon),
                Utils.round(angle),
                Utils.round(actual_weight),
                Utils.round(accelerate),
                Utils.round(NN)
        );
        CSV.write( Utils.round(dt)+","+Utils.round(angle)+","+ Utils.round(NN));
    }

    public PID getPidController() {
        return pid_controller;
    }

    public Point getPosition() {
        return new Point(distance_from_destination, altitude_from_moon);
    }

    public Point getNextPosition(double t) {
        return Formulas.calcNextPoint(
                distance_from_destination,
                altitude_from_moon,
                Moon.GRAVITY_ACCELERATION * NN,
                horizontal_speed,
                vertical_speed,
                t);
    }

    public double getVerticalSpeed() {
        return vertical_speed;
    }

    public void changeVerticalSpeed(double fix) {
        vertical_speed += fix;
    }
    
    public double getAccelerateY() {
    	return accelerate_y;
    }
    
    public void setAccelerateY(double acc_y) {
    	accelerate_y = acc_y;
    }

    public double getHorizontalSpeed() {
        return horizontal_speed;
    }

    public void setHorizontalSpeed(double hs) {
        horizontal_speed = hs;
    }
    
    public double getAccelerateX() {
    	return accelerate_x;
    }
    
    public void setAccelerateX(double acc_x) {
    	accelerate_x = acc_x;
    }

    public void setVerticalSpeed(double vs) {
        vertical_speed = vs;
    }

    public void changeHorizontalSpeed(double fix) {
        horizontal_speed += fix;
    }

    public double getDistanceFromDestination() {
        return distance_from_destination;
    }

    public void changeDistanceFromDestination(double fix) {
        distance_from_destination += fix;
    }

    public double getAngle() {
        return angle;
    }

    public void changeAngle(double fix) {
        angle = (angle + fix) % 360;
    }

    public void setAngle(double ang) {
        angle = ang;
    }
    
    public double getAngleSpeed() {
    	return angle_speed;
    }
    
    public void setAngleSpeed(double as) {
    	angle_speed = as;
    }
    
    public double getAngleAccelerate() {
    	return angle_accelerate;
    }
    
    public void setAngleAccelerate(double aa) {
    	angle_accelerate = aa;
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

    public void setAccelerate(double acc) {
        accelerate = acc;
    }

    public void changeAccelerate(double fix) {
        accelerate += fix;
    }

    public double getFuelAmount() {
        return fuel_amount;
    }

    public void setFuelAmount(double lf) {
        fuel_amount = lf;
    }

    public void changeFuelAmount(double fix) {
        fuel_amount += fix;
    }

    public double getActualWeight() {
        return actual_weight;
    }

    public void setActualWeight(double w) {
        actual_weight = w;
    }

    public void changeActualWeight(double fix) {
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

    public double getStartDistanceFromDestination() {
        return start_distance_from_destination;
    }

    public double getStartAltitudeFromMoon() {
        return start_altitude_from_moon;
    }
}
