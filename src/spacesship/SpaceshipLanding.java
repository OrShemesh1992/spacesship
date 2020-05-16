package spacesship;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class SpaceshipLanding implements SpaceshipActions {

    private Spaceship spaceship;
    private int landing_time;
    private double power = 0;
    
    private int num_of_destination_points = 9;
    private ArrayList<Point> destination_points;
    private Point current_destination_point;

    public SpaceshipLanding(Spaceship spaceship) {
        this.landing_time = 0;
        this.spaceship = spaceship;
        defineLandingPath();
    }

    @Override
    public void landing() {

        Double pid_result = spaceship.getPidController().update(landing_time, spaceship.getDistanceFromDestination());
        if (pid_result != null) pid_result = Math.abs(pid_result);

        double dist_result = spaceship.getStartDistanceFromDestination() - spaceship.getNextPosition(landing_time).getX();

        if (pid_result != null) {
            double minus_speed = (dist_result / pid_result);
            spaceship.setNN(minus_speed);
        }

        power = power + 0.01;
        if(power > 1){
            power = 1;
        }

        boolean main_engine = false;
        double seconds_engines = 0;

        if (spaceship.getNN() >= 0.85) {
            main_engine = true;
            seconds_engines = 8;
//            fullPowerLanding();
//            return;
        } else if (0.7 <= spaceship.getNN() && spaceship.getNN() < 0.85) {
            main_engine = true;
            seconds_engines = 4;
        } else if (0.3 <= spaceship.getNN() && spaceship.getNN() < 0.7) {
            main_engine = true;
        } else if (0.15 <= spaceship.getNN() && spaceship.getNN() < 0.3) {
            seconds_engines = 8;
        } else if (spaceship.getNN() < 0.15) {
            seconds_engines = 4;
        }

        double acc = Formulas.calcAccelerate(spaceship.getActualWeight(), main_engine, seconds_engines);
        updateSpeed(acc);
        updatePlace();
        burnFuel(main_engine, seconds_engines);

        spaceship.setAccelerate(acc);
        landing_time++;
    }

    //Define the desired landing path, which the spacecraft will land on
    private void defineLandingPath() {
    	this.destination_points = new ArrayList<Point>();
    	double total_alt = this.spaceship.getStartAltitudeFromMoon();
    	double total_dis = this.spaceship.getStartDistanceFromDestination();
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
    }
    
    private void fullPowerLanding() {
        double acc = Formulas.calcAccelerate(spaceship.getActualWeight(), true, 8);
        updateSpeed(acc);
        updatePlace();
        burnFuel(true, 8);

        spaceship.setAccelerate(acc);
        landing_time++;
    }

    private void updatePlace() {
        spaceship.changeDistanceFromDestination(-spaceship.getHorizontalSpeed());
        spaceship.changeAltitudeFromMoon(-spaceship.getVerticalSpeed());
    }

    private void updateSpeed(double counter_acc) {
//        System.out.println(counter_acc);
        double vertical_acc = counter_acc * Math.sin(Math.toRadians(spaceship.getAngle()));
        double horizontal_acc = counter_acc * Math.cos(Math.toRadians(spaceship.getAngle()));
        double ang = Formulas.calcAngleOfLanding(spaceship.getHorizontalSpeed(), spaceship.getVerticalSpeed());

        updateVerticalSpeed(vertical_acc, ang);
        updateHorizontalSpeed(horizontal_acc, ang);

        updateAngle(ang);
    }

    private void updateVerticalSpeed(double counter_acc, double ang) {
        double actual_speed = Formulas.calcVerticalSpeed(spaceship.getVerticalSpeed(), ang, 1);
        actual_speed -= counter_acc;
//        setVerticalSpeed(actual_speed);
    }

    private void updateHorizontalSpeed(double counter_acc, double ang) {
        double actual_speed = Formulas.calcHorizontalSpeed(spaceship.getHorizontalSpeed(), ang);
        actual_speed -= counter_acc;
        spaceship.setHorizontalSpeed(actual_speed);
    }

    private void updateAngle(double landing_ang) {
        double angle_similarity = landing_ang / spaceship.getAngle();

        if (angle_similarity < 0.1) {
            spaceship.changeAngle(-3);
        } else if (angle_similarity < 0.2) {
            spaceship.changeAngle(-2);
        } else if (angle_similarity < 1) {
            spaceship.changeAngle(-1);
        } else {
            spaceship.changeAngle(1);
        }
    }

    private void burnFuel(boolean main_engine, double seconds_engines) {
        if (main_engine) {
            spaceship.changeFuelAmount(-Spaceship.MAIN_ENGINE_CONSUMPTION);
        }
        spaceship.changeFuelAmount(-Spaceship.SECOND_ENGINE_CONSUMPTION * seconds_engines);
        spaceship.setActualWeight(Spaceship.WEIGHT_CRAFT + spaceship.getFuelAmount());
    }
}
