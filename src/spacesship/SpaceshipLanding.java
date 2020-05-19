package spacesship;
/**
 * 
 * @author Or Shemesh
 *
 */
public class SpaceshipLanding implements SpaceshipActions {

	Spaceship spaceship;

	public SpaceshipLanding(Spaceship spaceship) {
		this.spaceship = spaceship;
	}
/**
 ***************** landing function************************
 */
	public void landing(){

		spaceship.setTime(spaceship.getTime()+1);
		Acceleration();
		Engines();
		Rotation();
		updateAltitude();
		updateFuel();
		updateSpeed();
	}

	//**********Rotation spaceship***************
	private void Rotation() {

		if ( spaceship.getAngle() > 0 && spaceship.getAngSpeed() > -0.3 )
			spaceship.setAngAcc( -getAngularPower());
		else if ( spaceship.getAngle() < 0 && spaceship.getAngSpeed() < 0.3 )
			spaceship.setAngAcc(getAngularPower());
		else
			spaceship.setAngAcc(0);
		updateAngularSpeed();
		updateAngle();
	}

	// **********Update angular speed*************
	private void updateAngularSpeed() {
		spaceship.setAngSpeed(Formulas.getVelocity( spaceship.dt, spaceship.getAngAcc())+spaceship.getAngSpeed());
	}

	// ************Update angle*******************
	private void updateAngle() {
		spaceship.setAngle(spaceship.getAngle()+Formulas.getDisplacement( spaceship.dt, spaceship.getAngSpeed(), spaceship.getAngAcc() ));
		normalizeAngle();
	}

	// ************Keep the range***************** 
	private void normalizeAngle() {
		while ( spaceship.getAngle() < -180 )
			spaceship.setAngle(spaceship.getAngle()+360);
		while ( spaceship.getAngle() > 180 )
			spaceship.setAngle(spaceship.getAngle()-360);
	}
    //************get angular power****************
	public double getAngularPower() {
		double torque = Formulas.getTorque( spaceship.SELF_RADIUS, getTotalEnginePower() );
		double moment = Formulas.getDiscMoment( spaceship.SELF_RADIUS, getTotalWeight() );
		return Formulas.getAccNewton2( torque, moment );
	}

	// ***************** Acceleration without engine************
	private void Acceleration() {
		// no gravitational acceleration 
		spaceship.setAccelerateX(0);
		// calculate gravitational acceleration
		double weight  = getTotalWeight();
		double gravity = Moon.getGravityForce(weight, spaceship.getAltitude() );
		spaceship.setAccelerateY(Formulas.getAccNewton2( gravity, weight ));
	}
	
	// ****************Calculate horizontal **************
	private void PowerHorizontally() {
		spaceship.setAccelerateX(spaceship.getAccelerateX()-getTotalEnginePower());
	}

	// **************** Calculate vertical ***************
	private void PowerVertically() {
		spaceship.setAccelerateY(spaceship.getAccelerateY()+getTotalEnginePower());
	}

	// ******************* Activate engines *************** 
	private void Engines() {

		if (spaceship.getHorizontal_speed() > 0 )
			PowerHorizontally();
		if ( spaceship.getAltitude()> 20000 ) {
			if ( spaceship.getVertical_speed()< -25 )
				PowerVertically();

		} else if ( this.spaceship.getAltitude() > 10000 ) {

			if ( spaceship.getVertical_speed() < -100 )
				PowerVertically();

		} else if ( spaceship.getAltitude()> 1000 ) {

			if ( spaceship.getVertical_speed() < -50 )
				PowerVertically();

		} else if (spaceship.getAltitude()> 100 ) {

			if ( spaceship.getVertical_speed() < -10 )
				PowerVertically();

		} else if ( spaceship.getAltitude() > 5 )  {

			if ( spaceship.getVertical_speed() < -5 )
				PowerVertically();
		}
	}	

	// ************************** update Fuel ********************
	private void updateFuel() {
		spaceship.setFuel_amount(spaceship.getFuel_amount()-
				(spaceship.dt*(spaceship.MAIN_ENGINE_CONSUMPTION+spaceship.SECOND_ENGINE_CONSUMPTION*8)));
	}

	// ************************** update Altitude ****************
	private void updateAltitude() {
		spaceship.setAltitude(spaceship.getAltitude()+
				Formulas.getDisplacement( spaceship.dt, spaceship.getVertical_speed(),
						spaceship.getAccelerateY() ))	;
	}

	// ************************** update speed *******************
	private void updateSpeed() {
		spaceship.setHorizontal_speed(spaceship.getHorizontal_speed()+
				Formulas.getVelocity( spaceship.dt, spaceship.getAccelerateX() ));
		spaceship.setVertical_speed(spaceship.getVertical_speed()+
				Formulas.getVelocity( spaceship.dt, spaceship.getAccelerateY() ));
	}
	// ************************** Total Weight *******************
	public double getTotalWeight() {
		return spaceship.WEIGHT_CRAFT + spaceship.getFuel_amount();
	}

	// ************************** Engine Power *******************
	public double getTotalEnginePower() {
		double weight = getTotalWeight();
		double force  = spaceship.MAIN_ENGINE_POWER + spaceship.SECOND_ENGINE_POWER * 8;
		return Formulas.getAccNewton2( force, weight );
	}
}


