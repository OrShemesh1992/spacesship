package spacesship;




/**
 * A simple PID control loop.
 *
 */
public class PID {

    private double setPoint;
    private double pGain;
    private double iGain;
    private double dGain;

    private double lastTime;
    private double lastError = 0;
    private double integral = 0;


    /**
     * Establishes a new controller with a given setpoint and gains.
     *
     * @param set The target point.
     * @param p The proportional gain.
     * @param i The integral gain.
     * @param d The derivative gain.
     */
    public PID(double set, double p, double i, double d) {

        reset(set);

        pGain = p;
        iGain = i;
        dGain = d;
    }


    /**
     * Updates the controller with the current time and value.
     *
     * @param currTime The current time (in nanoseconds).
     * @param currValue The current value.
     *
     * @return The PID output.
     */
    public Double update(double currTime, double currValue) {
        if (lastTime == 0) {

            lastTime = currTime;
            lastError = setPoint - currValue;

            return null;
        }


        double dt = (double)(currTime - lastTime);

        if (dt == 0) {
            return null;
        }

        double error = setPoint - currValue;
        double deriv = (error - lastError) / dt;

        integral += error * dt;
        lastTime = currTime;
        lastError = error;

        double ans = (pGain * error) + (iGain * integral) + (dGain * deriv);
        return ans;
    }


    /**
     * Establishes a new setpoint for the PID controller to achieve.
     *
     * @param set The new target point.
     */
    public void setSetpoint(double set) {
        reset(set);
    }


    /**
     * Resets the PID controller to target a new setpoint. This includes
     * zeroing the accumulated integral and historical error value.
     *
     * @param set The new setpoint.
     */
    private void reset(double set) {

        setPoint = set;

        lastTime = 0;
        lastError = 0;
        integral = 0;
    }
}