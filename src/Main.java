
public class Main {

    public static void main(String[] args) {

        MachineAction.turnOnCaffeeMaker();
        Status.checkStatus();

        MachineAction.turnOffCaffeeMaker();
        Status.checkStatus();


    }


}