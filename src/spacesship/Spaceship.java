package spacesship;

public class Spaceship {

	public static final double WEIGHT_CRAFT = 165;   // kg
	public static final double WEIGHT_FUEL = 420; // kg
    public static final double WEIGHT_FULL = WEIGHT_CRAFT + WEIGHT_FUEL; // kg
	public static final double MAIN_ENGINE_POWER = 430;   // N
    public static final double SECOND_ENGINE_POWER = 25; // N
	public static final double MAIN_ENGINE_CONSUMPTION  = 0.15;  // liter per sec, 12 liter per m'
	public static final double SECOND_ENGINE_CONSUMPTION= 0.009; // liter per sec 0.6 liter per m'
	public static final double ALL_ENGINE_START = MAIN_ENGINE_CONSUMPTION + 8 * SECOND_ENGINE_CONSUMPTION;
	
   //***********Variables**************
    private double start_distance_from_destination;
    private double start_altitude_from_moon;
    private double distance;
	
	private PID pid_controller;
	private double NN;

	private int    time;
	private double altitude_from_moon;
	private double fuel_amount;
	private double vertical_speed;
	private double accelerate_y;
	private double horizontal_speed;
	private double accelerate_x;
	private double angle;
	private double angle_speed;
	private double angle_accelerate; //[d/s ^2]
	private double accelerate;
	private double actual_weight;
	
	
	public Spaceship(int dist, int _time, double alt, double fuel, double vs, double hs, double accX, double accY,double ang,double _angSpeed,double _angACC) {
		this.time=_time;
		this.altitude_from_moon=alt;
		this.fuel_amount=fuel;
		this.vertical_speed=vs;
		this.horizontal_speed=hs;
		this.accelerate_x=accX;
		this.accelerate_y=accY;
		this.angle=ang;
		this.angle_speed=_angSpeed;
		this.angle_accelerate=_angACC;
		this.actual_weight = WEIGHT_CRAFT + fuel_amount;
		printtitle();
		pid_controller=new PID(13000,1,0,0);
		start_altitude_from_moon=alt;
		start_distance_from_destination=dist;
		distance = dist;
	}
	private void printtitle(){
		System.out.println( "time\talt\t\tvs\t\tvAcc\t\ths\t\thAcc\t\tfuel\tweight\tangle\tangSpeed\tdistance\tNN" );
		CSV.write( "time,alt,vs,vAcc,hs,hAcc,fuel,weight,angle,angSpeed,distance,NN");
	}
    public void printInfo() {
		String s= String.format( "%4ds,%9.2f,%5.2fm/s,%5.2fm/s,%7.2fm/s,%5.2fm/s,%.2f,%.2f,%5.2f,%5.2f,%6.3f,%9.3f",
				time,
				altitude_from_moon,
				vertical_speed,
				accelerate_y,
				horizontal_speed,
				accelerate_x,
				fuel_amount,
				WeightSpaces(),
				angle,
				angle_speed,
				distance,
				NN
				);
		System.out.println(s);
        CSV.write(s);
    }
	public double WeightSpaces() {
		return WEIGHT_CRAFT + this.fuel_amount;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getAltitude() {
		return altitude_from_moon;
	}

	public void setAltitude(double altitude) {
		this.altitude_from_moon = altitude;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}


	public double getFuel_amount() {
		return fuel_amount;
	}

	public void setFuel_amount(double fuel_amount) {
		this.fuel_amount = fuel_amount;
	}
	
	public void changeFuelAmount(double fix) {
        fuel_amount += fix;
    }

	public double getVertical_speed() {
		return vertical_speed;
	}

	public void setVertical_speed(double vertical_speed) {
		this.vertical_speed = vertical_speed;
	}

	public double getHorizontal_speed() {
		return horizontal_speed;
	}

	public void setHorizontal_speed(double horizontal_speed) {
		this.horizontal_speed = horizontal_speed;
	}

	public double getAccelerateX() {
		return accelerate_x;
	}

	public void setAccelerateX(double accelerateX) {
		this.accelerate_x = accelerateX;
	}

	public double getAccelerateY() {
		return accelerate_y;
	}

	public void setAccelerateY(double accelerateY) {
		this.accelerate_y = accelerateY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngSpeed() {
		return angle_speed;
	}

	public void setAngSpeed(double angSpeed) {
		this.angle_speed = angSpeed;
	}

	public double getAngAcc() {
		return angle_accelerate;
	}

	public void setAngAcc(double angAcc) {
		this.angle_accelerate = angAcc;
	}
	
    public PID getPidController() {
        return pid_controller;
    }
    
    public void setNN(double nn) {
    	this.NN = nn;
    }
    
    public double getNN() {
    	return NN;
    }
    
    public double getStartDistanceFromDestination() {
        return start_distance_from_destination;
    }

    public double getStartAltitudeFromMoon() {
        return start_altitude_from_moon;
    }

}