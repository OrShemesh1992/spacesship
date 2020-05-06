package spacesship;

/**
 * This class represents the basic flight controller of the Bereshit space craft.
 *
 * @author ben-moshe
 */
public class Bereshit_101 {


    //accelerate calculation
    public static double accMax(double weight) {
        return acc(weight, true, 8);
    }

    public static double acc(double weight, boolean main, int seconds_engines) {

        double force = 0;

        if (main) {
            force += Spaceship.MAIN_ENGINE_POWER;
        }
//        force += Spaceship.MAIN_ENGINE_POWER;
        force += seconds_engines * Spaceship.SECOND_ENGINE_POWER;
        double ans = force / weight;
        return ans;
    }

    // 14095, 955.5, 24.8, 2.0
    public static void main(String[] args) {
        System.out.println("Simulating Bereshit's Landing:");

        Spaceship bereshit = SituationCreator.getSituation1();
        System.out.println("time, vs, hs, dist, alt, ang, weight, acc");
        double time = 0;

        // ***** main simulation loop ******
        while (bereshit.getAltitudeFromMoon() > 0) {
            if (time % 10 == 0 || bereshit.getAltitudeFromMoon() < 100) {
//                System.out.println(
//                        time + ", " +
//                                bereshit.getVerticalSpeed() + ", " +
//                                bereshit.getHorizontalSpeed() + ", " +
//                                bereshit.getDistanceFromEarth() + ", " +
//                                bereshit.getAltitudeFromMoon() + ", " +
//                                bereshit.getAngle() + ", " +
//                                bereshit.getActualWeight() + ", " +
//                                bereshit.getAccelerate()
//                );
                bereshit.printInfo();
            }


            // over 2 km above the ground
            if (bereshit.getAltitudeFromMoon() > 2000) {    // maintain a vertical speed of [20-25] m/s
                if (bereshit.getVerticalSpeed() > 25) {
                    bereshit.changeNN(0.003 * bereshit.getDt());
                } // more power for braking
                if (bereshit.getVerticalSpeed() < 20) {
                    bereshit.changeNN(-0.003 * bereshit.getDt());
                } // less power for braking
            }
            // lower than 2 km - horizontal speed should be close to zero
            else {
                if (bereshit.getAngle() > 3) {
                    bereshit.changeAngle(-3);
                } // rotate to vertical position.
                else {
                    bereshit.setAngle(0); // | - 0, _ - 90
                }

                bereshit.setNN(0.5); // brake slowly, a proper PID controller here is needed!

                if (bereshit.getHorizontalSpeed() < 2) {
                    bereshit.setHorizontalSpeed(0);
                }

                if (bereshit.getAltitudeFromMoon() < 125) { // very close to the ground!

                    bereshit.setNN(1); // maximum braking!

                    if (bereshit.getVerticalSpeed() < 5) {
                        bereshit.setNN(0.7);
                    } // if it is slow enough - go easy on the brakes
                }
            }


            if (bereshit.getAltitudeFromMoon() < 5) { // no need to stop
                bereshit.setNN(0.4);
            }


            // main computations
            double angle_radian = Math.toRadians(bereshit.getAngle());

            double horizontal_accelerate = Math.sin(angle_radian) * bereshit.getAccelerate();
            double vertical_accelerate = Math.cos(angle_radian) * bereshit.getAccelerate();

            double vertical_moon_gravity = Moon.getAcc(bereshit.getHorizontalSpeed());

            time += bereshit.getDt();

            double dw = bereshit.getDt() * Spaceship.ALL_ENGINE_START * bereshit.getNN();

            if (bereshit.getFuelAmount() > 0) {
                bereshit.changeFuelAmount(-dw);
                bereshit.setActualWeight(Spaceship.WEIGHT_CRAFT + bereshit.getFuelAmount());
                bereshit.setAccelerate(bereshit.getNN() * accMax(bereshit.getActualWeight())); //calc accelerate
            } else { // ran out of fuel
                bereshit.setAccelerate(0);
            }

            vertical_accelerate -= vertical_moon_gravity;

            if (bereshit.getHorizontalSpeed() > 0) {
                bereshit.changeHorizontalSpeed(-1 * horizontal_accelerate * bereshit.getDt());
            }

            bereshit.changeDistanceFromDestination(-1 * bereshit.getHorizontalSpeed() * bereshit.getDt());
            bereshit.changeVerticalSpeed(-1 * vertical_accelerate * bereshit.getDt());
            bereshit.changeAltitudeFromMoon(-1 * bereshit.getDt() * bereshit.getVerticalSpeed());
        }
        System.out.println(time);
    }
}
