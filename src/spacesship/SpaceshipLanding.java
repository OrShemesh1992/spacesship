package spacesship;

public class SpaceshipLanding implements SpaceshipActions {

    Spaceship spaceship;

    public SpaceshipLanding(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void landing() {

        if (spaceship.getAltitudeFromMoon() > 2000) {
            //keep vertical speed in range 20-25
        } else {
            //correct spaceship angle


//            double target = 0;
//            double actual = spaceship.getAngle();
//            double output = 0;
//
//            spaceship.getPidController().setSetpoint(target);
//            output = spaceship.getPidController().getOutput(actual, target);
//            actual = actual + output;
        }


        //calculation to fuel



    }

}
