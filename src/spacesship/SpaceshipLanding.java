package spacesship;


import java.util.ArrayList;

public class SpaceshipLanding implements SpaceshipActions {

	Spaceship spaceship;
	//pid varibales
    private int num_of_destination_points = 9;
    private ArrayList<Point> destination_points;
    private Point current_destination_point;
    private int index_des_point;
    
    private double NN;

	public SpaceshipLanding(Spaceship spaceship) {
		this.spaceship = spaceship;
		this.NN = 0.5;
	}
/**
 ***************** landing function************************
 */
	public void landing(){
		spaceship.setTime(spaceship.getTime()+1);
		
		// ***************** Acceleration set without engine************
		double force = Formulas.calcGravitationalAccelerationByNewton(spaceship.WeightSpaces(), Moon.RADIUS+spaceship.getAltitude());
		// no gravitational acceleration 
		spaceship.setAccelerateX(0);
		// calculate gravitational acceleration
		spaceship.setAccelerateY(Formulas.calcAccelerationByNewton(force, spaceship.WeightSpaces()));
		Engines();
		System.out.println("NN = "+NN);
		Rotation();
		updateAltitude();
		updateDistance();
		updateFuel();
		updateSpeed();
	}

	//**********Rotate the spaceship around its axis***************
	private void Rotation() {

		if ( spaceship.getAngle() > 0 && spaceship.getAngSpeed() > -0.3 )
			spaceship.setAngAcc( -getAngularAcc());
		else if ( spaceship.getAngle() < 0 && spaceship.getAngSpeed() < 0.3 )
			spaceship.setAngAcc(getAngularAcc());
		else
			spaceship.setAngAcc(0);
		spaceship.setAngSpeed(spaceship.getAngAcc()+spaceship.getAngSpeed());
		spaceship.setAngle(spaceship.getAngle()+Formulas.calcDisplacement( 1, spaceship.getAngSpeed(), spaceship.getAngAcc()));
		if(spaceship.getAngle() < -180) {
			spaceship.setAngle(spaceship.getAngle()%360);
		}
		else if(spaceship.getAngle() > 180) {
			spaceship.setAngle(spaceship.getAngle()%360);
		}
	}

    //************get angular power****************
	// calc the angular acceleration
	public double getAngularAcc() {
		double force = getTotalEnginePower(1);
		double moment = Formulas.calcDiscMoment(1, spaceship.WeightSpaces());
		return Formulas.calcAccelerationByNewton(force, moment );
	}

	// ******************* Activate engines *************** 
	private void Engines() {

		Double error = spaceship.getPidController().update(spaceship.getTime(), spaceship.getAltitude());
		if(error!=null) {
		}
		
		// Horizontal speed not effect the Engines and fuel - it constant
		if (spaceship.getHorizontal_speed() > 0) {
			PowerHorizontally(1);
		}
		
		// Vertical speed effect the Engines and fuel - the 
		if ( this.spaceship.getAltitude() > 7000 ) {
			if ( spaceship.getVertical_speed()< -35) {
				this.setNN(NN + 0.003);
				PowerVertically(NN); //0 - 1
			}
			else if ( spaceship.getVertical_speed()> -20) {
				this.setNN(NN - 0.003);
				PowerVertically(NN); //0 - 1
			}
		} else if ( spaceship.getAltitude()> 5000 ) {
			if ( spaceship.getVertical_speed() < -30 ) {
				this.setNN(NN + 0.003);
				PowerVertically(NN); //0 - 1
			}
			else if ( spaceship.getVertical_speed()> -20) {
				this.setNN(NN - 0.003);
				PowerVertically(NN); //0 - 1
			}
		} else if ( spaceship.getAltitude()> 3500 ) {
			if ( spaceship.getVertical_speed() < -25 ) {
				this.setNN(NN + 0.003);
				PowerVertically(NN); //0 - 1
			}
			else if (spaceship.getVertical_speed()> -20) {
				this.setNN(NN - 0.003);
				PowerVertically(NN); //0 - 1
			}

		} else if ( spaceship.getAltitude()> 1000 ) {
			if ( spaceship.getVertical_speed() < -15 ) {
				this.setNN(NN + 0.003); 
				PowerVertically(NN); //0 - 1
			}
			else if (spaceship.getVertical_speed()> -10) {
				this.setNN(NN - 0.003); 
				PowerVertically(NN); //0 - 1
			}

		} else if (spaceship.getAltitude()> 100 ) {
			if ( spaceship.getVertical_speed() < -10 ) {
				this.setNN(NN + 0.003); 
				PowerVertically(NN); //0 - 1
			}
			else if (spaceship.getVertical_speed()> -5) {
				this.setNN(NN - 0.003); 
				PowerVertically(NN); //0 - 1
			}

		} else if ( spaceship.getAltitude() > 5 )  {
			if ( spaceship.getVertical_speed() < -5 ) {
				this.setNN(NN + 0.03); //full breaking power
				PowerVertically(NN); //0 - 1
			}
		}
		this.spaceship.setNN(NN);
	}	
	
	// ****************Calculate horizontal **************
	private void PowerHorizontally(double NN) {
		spaceship.setAccelerateX(spaceship.getAccelerateX()-getTotalEnginePower(NN));
	}

	// **************** Calculate vertical ***************
	private void PowerVertically(double NN) {
		spaceship.setAccelerateY(spaceship.getAccelerateY()+getTotalEnginePower(NN));
	}

	// ************************** update Altitude ****************
	private void updateAltitude() {
		spaceship.setAltitude(spaceship.getAltitude()+
				Formulas.calcDisplacement( 1, spaceship.getVertical_speed(),
						spaceship.getAccelerateY()))	;
	}
	
	// ************************** update Distance ****************
	private void updateDistance() {
		spaceship.setDistance(spaceship.getDistance() - spaceship.getHorizontal_speed());
	}
	
	// ************************** update Fuel ********************
	private void updateFuel() {
		spaceship.setFuel_amount(spaceship.getFuel_amount()-
				(spaceship.MAIN_ENGINE_CONSUMPTION+spaceship.SECOND_ENGINE_CONSUMPTION*8)*NN);
	}

	// ************************** update speed *******************
	private void updateSpeed() {
		spaceship.setHorizontal_speed(spaceship.getHorizontal_speed()+spaceship.getAccelerateX());
		spaceship.setVertical_speed(spaceship.getVertical_speed()+spaceship.getAccelerateY());
	}

	// ************************** Engine Power *******************
	public double getTotalEnginePower(double NN) {
		double weight = spaceship.WeightSpaces();
		double force  = (spaceship.MAIN_ENGINE_POWER + spaceship.SECOND_ENGINE_POWER * 8) * NN;
		return Formulas.calcAccelerationByNewton( force, weight );
	}
	
	 private void setNN(double nn) {
		 NN = nn;
		 if(nn > 1) {NN = 1;}
		 if(nn < 0) {NN = 0;}
	 }
	
	// ************************** update pid **********************
	 private void defineLandingPath() {
	    	this.destination_points = new ArrayList<Point>();
	    	double total_alt = spaceship.getStartAltitudeFromMoon();
	    	double total_dis = spaceship.getStartDistanceFromDestination();
	    	double dis_from_ver_landing = 3000;          //arbitrary
	    	double alt_from_ver_landing = 2000;          //arbitrary
	    	double dis = total_dis - dis_from_ver_landing;
	    	double alt = total_alt - alt_from_ver_landing;
	    	int num_points = this.num_of_destination_points;
	    	double dis_between_points = dis/num_points;
	    	double alt_between_points = alt/num_points;
	    	
	    	this.destination_points.add(new Point(0, total_alt));
	    	
	    	double x=0, y=total_alt;
	    	for(int i=0 ; i<num_points ; i++) {
	    		x += dis_between_points;
	    		y -= alt_between_points;
	    		this.destination_points.add(new Point(x,y));
	    	}
	    	this.current_destination_point = this.destination_points.get(1);
	    	index_des_point = 1;
	    	spaceship.getPidController().setSetpoint(current_destination_point.getY());
	    }
}