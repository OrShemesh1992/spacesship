package spacesship;


public class Main {

    public static void main(String[] args) {
        Spaceship bereshit = SituationCreator.getSituation1();
        SpaceshipLanding spaceshipLanding = new SpaceshipLanding(bereshit);

        while ( bereshit.getFuel_amount() > 0 && bereshit.getAltitude() > 0 ) {
    		//if( bereshit.getTime() % 10 == 0 ) {
    			bereshit.printInfo();
    		//}
            spaceshipLanding.landing();
        }
    }
}
