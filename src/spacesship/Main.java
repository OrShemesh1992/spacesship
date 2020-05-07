package spacesship;

public class Main {

    public static void main(String [] args){

        Spaceship bereshit = SituationCreator.getSituation1();
        SpaceshipLanding spaceshipLanding = new SpaceshipLanding(bereshit);

        int i = 0;

        while (bereshit.getAltitudeFromMoon() > 1 || bereshit.getFuelAmount() > 0) {
            if(i++ % 10 == 0){
                bereshit.printInfo();
            }


            spaceshipLanding.landing();
        }


    }
}
