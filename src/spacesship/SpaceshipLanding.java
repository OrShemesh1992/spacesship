package spacesship;

@SuppressWarnings("Duplicates")
public class SpaceshipLanding implements SpaceshipActions {

    Spaceship spaceship;

    public SpaceshipLanding(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void landing() {

//        if (spaceship.getAltitudeFromMoon() > 2000) {
//            //keep vertical speed in range 20-25
//        } else {
//            //correct spaceship angle
//        }
//
//
//        //calculation to fuel
//
//
//        //if altitude more than 2000 - keep vertical speed in range [20-25] m/s
//        //else - horizontal speed should be close to zero
//        if (spaceship.getAltitudeFromMoon() > 2000) {
//
//            if (spaceship.getVerticalSpeed() > 25) {
//                spaceship.changeNN(0.003 * spaceship.getDt());
//            }
//
//            if (spaceship.getVerticalSpeed() < 20) {
//                spaceship.changeNN(-0.003 * spaceship.getDt());
//            }
//        } else {
//
//            if (spaceship.getAngle() > 3) {
//                spaceship.fixAngle(-3);
//            } else {
//                spaceship.setAngle(0);
//            }
//
//            spaceship.setNN(0.5); // brake slowly, a proper PID controller here is needed!
//
//
//            double target = 0;
//            double actual = spaceship.getHorizontalSpeed();
//            double output = 0;
//
//            spaceship.getPidController().setSetpoint(target);
//            output = spaceship.getPidController().getOutput(actual, target);
//            actual = actual + output;
//
//
//            if (spaceship.getHorizontalSpeed() < 2) {
//                spaceship.setHorizontalSpeed(0);
//            }
//
//            if (spaceship.getAltitudeFromMoon() < 125) { // very close to the ground!
//
//                spaceship.setNN(1); // maximum braking!
//
//                if (spaceship.getVerticalSpeed() < 5) {
//                    spaceship.setNN(0.7);
//                } // if it is slow enough - go easy on the brakes
//            }
//        }
//
//        if (spaceship.getAltitudeFromMoon() < 5) { // no need to stop
//            spaceship.setNN(0.4);
//        }
//
//        // main computations
//        double angle_radian = Math.toRadians(spaceship.getAngle());
//        double horizontal_accelerate = Math.sin(angle_radian) * spaceship.getAccelerate();
//        double vertical_accelerate = Math.cos(angle_radian) * spaceship.getAccelerate();
//        double vertical_moon_gravity = Moon.getAcc(spaceship.getHorizontalSpeed());
//
//        time += spaceship.getDt();
//
//        double dw = spaceship.getDt() * Spaceship.ALL_ENGINE_START * spaceship.getNN();
//
//        if (spaceship.getFuelAmount() > 0) {
//            spaceship.changeFuelAmount(-dw);
//            spaceship.setActualWeight(Spaceship.WEIGHT_CRAFT + spaceship.getFuelAmount());
//            spaceship.setAccelerate(spaceship.getNN() * accMax(spaceship.getActualWeight()));
//        } else { // ran out of fuel
//            spaceship.setAccelerate(0);
//        }
//
//        vertical_accelerate -= vertical_moon_gravity;
//
//        if (spaceship.getHorizontalSpeed() > 0) {
//            spaceship.changeHorizontalSpeed(-1 * horizontal_accelerate * spaceship.getDt());
//        }
//
//        spaceship.changeDistanceFromEarth(-1 * spaceship.getHorizontalSpeed() * spaceship.getDt());
//        spaceship.changeVerticalSpeed(-1 * vertical_accelerate * spaceship.getDt());
//        spaceship.changeAltitudeFromMoon(-1 * spaceship.getDt() * spaceship.getVerticalSpeed());
//

    }

}
