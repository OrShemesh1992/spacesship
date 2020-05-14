package spacesship;

@SuppressWarnings("Duplicates")
public class SpaceshipLanding implements SpaceshipActions {

    Spaceship spaceship;

    public SpaceshipLanding(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public void landing() {
    	
//    	if(spaceship.getAltitudeFromMoon()>10000) {
    		
    		
    		
    		
    		//-> hs>1000
//    	}
//		if(8000<alt&&alt<10000) {
//			if(spaceship.getVerticalSpeed()>800) {
//				
//			}
//			
//			
//		//	-> 800< hs <1000
//			
//		}
//    	if(6000<alt&&alt<8000) -> 600< hs <800
//      	if(4000<alt&&alt<6000) -> 400< hs <600
//      
//      	if(2000<alt&&alt<4000) -> 200< hs <400
//      
//      	if(1000<alt&&alt<2000) -> 50< hs <200
//      	if(100<alt&&alt<1000) -> 0< hs <50

    	   // over 2 km above the ground
        if (spaceship.getAltitudeFromMoon() > 2000) {    // maintain a vertical speed of [20-25] m/s
            if (spaceship.getVerticalSpeed() > 25) {
                spaceship.changeNN(0.003 * spaceship.getDt());
            } // more power for braking
            if (spaceship.getVerticalSpeed() < 20) {
                spaceship.changeNN(-0.003 * spaceship.getDt());
            } // less power for braking
        }
        // lower than 2 km - horizontal speed should be close to zero
        else {
            if (spaceship.getAngle() > 3) {
                spaceship.changeAngle(-3);
            } // rotate to vertical position.
            else {
                spaceship.setAngle(0); // | - 0, _ - 90
            }

            spaceship.setNN(0.5); // brake slowly, a proper PID controller here is needed!

            if (spaceship.getHorizontalSpeed() < 2) {
                spaceship.setHorizontalSpeed(0);
            }

            if (spaceship.getAltitudeFromMoon() < 125) { // very close to the ground!

                spaceship.setNN(1); // maximum braking!

                if (spaceship.getVerticalSpeed() < 5) {
                    spaceship.setNN(0.7);
                } // if it is slow enough - go easy on the brakes
            }
        }


        if (spaceship.getAltitudeFromMoon() < 5) { // no need to stop
            spaceship.setNN(0.4);
        }

        // main computations
        double angle_radian = Math.toRadians(spaceship.getAngle());

        double horizontal_accelerate = Math.sin(angle_radian) * spaceship.getAccelerate();
        double vertical_accelerate = Math.cos(angle_radian) * spaceship.getAccelerate();

        double vertical_moon_gravity = Moon.getAcc(spaceship.getHorizontalSpeed());

        double dw = spaceship.getDt() * Spaceship.ALL_ENGINE_START * spaceship.getNN();

        if (spaceship.getFuelAmount() > 0) {
            spaceship.changeFuelAmount(-dw);
            spaceship.setActualWeight(Spaceship.WEIGHT_CRAFT + spaceship.getFuelAmount());
            spaceship.setAccelerate(spaceship.getNN() * spaceship.fullPowerLanding());
        } else { // ran out of fuel
            spaceship.setAccelerate(0);
        }

        vertical_accelerate -= vertical_moon_gravity;

        if (spaceship.getHorizontalSpeed() > 0) {
            spaceship.changeHorizontalSpeed(-1 * horizontal_accelerate * spaceship.getDt());
        }

        spaceship.changeDistanceFromDestination(-1 * spaceship.getHorizontalSpeed() * spaceship.getDt());
        spaceship.changeVerticalSpeed(-1 * vertical_accelerate * spaceship.getDt());
        spaceship.changeAltitudeFromMoon(-1 * spaceship.getDt() * spaceship.getVerticalSpeed());

    }

}
