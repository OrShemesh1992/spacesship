package spacesship;


public class Main {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        Spaceship bereshit = SituationCreator.getSituation1();
        SpaceshipLanding spaceshipLanding = new SpaceshipLanding(bereshit);

        PID pid = new PID(0, 0.25,0.01,0.3);


        int time = 0;
        int dist = 1000;

        while (bereshit.getHorizontalSpeed() > 1
                && bereshit.getVerticalSpeed() > 1
                && bereshit.getFuelAmount() > 0
                && bereshit.getAltitudeFromMoon() > 0
                && bereshit.getDistanceFromDestination() > 0
        && time < 1000) {


            if (time++ % 10 == 0) {
//                System.out.println("_________________________________________________________");
//                System.out.println(bereshit.getPosition() + " - " + bereshit.getNextPosition(10));
//                System.out.println(bereshit.getPidController().update(time, bereshit.getDistanceFromDestination()));
                bereshit.printInfo();
            }

            spaceshipLanding.landing();
        }

        bereshit.printInfo();
        System.out.println(time);

    }
}
