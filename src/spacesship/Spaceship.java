package spacesship;
/**
 * 
 * @author Or Shemesh
 *
 */

public class Spaceship {

	public static final double WEIGHT_CRAFT             = 165;   // kg
	public static final double SELF_RADIUS              = 0.925; // M
	public static final double MAIN_ENGINE_POWER        = 430;   // N
	public static final double SECOND_ENGINE_POWER      = 25;    // N
	public static final double MAIN_ENGINE_CONSUMPTION  = 0.15;  // liter per sec, 12 liter per m'
	public static final double SECOND_ENGINE_CONSUMPTION= 0.009; // liter per sec 0.6 liter per m'
	public static final int dt = 1;    // Delta Time is 1 sec
   //***********Variables**************
	private int    time;
	private double altitude ;
	private double fuel_amount;
	private double vertical_speed ;
	private double horizontal_speed;
	private double accelerateX  ;
	private double accelerateY ;
	private double angle    ;
	private double angSpeed  ;
	private double angAcc   ;
	
	public Spaceship(int _time, double alt, double fuel, double vs, double hs, double accX, double accY,double ang,double _angSpeed,double _angACC) {
		this.time=_time;
		this.altitude=alt;
		this.fuel_amount=fuel;
		this.vertical_speed=vs;
		this.horizontal_speed=hs;
		this.accelerateX=accX;
		this.accelerateY=accY;
		this.angle=ang;
		this.angSpeed=_angSpeed;
		this.angAcc=_angACC;
		printtitle();
	}
	private void printtitle(){
		System.out.println( "Time\tAlt\t\tV-Speed\t\tV-Acc\t\tH-Speed\t\tH-Acc\t\tFuel\tWeight\tAngle\tA-Speed\tA-Acc" );
		CSV.write( "Time,Alt,V-Speed,V-Acc,H-Speed,H-Acc,Fuel,Weight,Angle,A-Speed,A-Acc");
	}
    public void printInfo() {
		String s= String.format( "%4ds,%9.2f,%5.2fm/s,%5.2fm/s²,%7.2fm/s,%5.2fm/s²,%.2f,%.2f,%5.2f°,%5.2f,%6.3f",
				time,
				altitude,
				vertical_speed,
				accelerateY,
				horizontal_speed,
				accelerateX,
				fuel_amount,
				WeightSpaces(),
				angle,
				angSpeed,
				angAcc
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
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getFuel_amount() {
		return fuel_amount;
	}

	public void setFuel_amount(double fuel_amount) {
		this.fuel_amount = fuel_amount;
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
		return accelerateX;
	}

	public void setAccelerateX(double accelerateX) {
		this.accelerateX = accelerateX;
	}

	public double getAccelerateY() {
		return accelerateY;
	}

	public void setAccelerateY(double accelerateY) {
		this.accelerateY = accelerateY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngSpeed() {
		return angSpeed;
	}

	public void setAngSpeed(double angSpeed) {
		this.angSpeed = angSpeed;
	}

	public double getAngAcc() {
		return angAcc;
	}

	public void setAngAcc(double angAcc) {
		this.angAcc = angAcc;
	}

}