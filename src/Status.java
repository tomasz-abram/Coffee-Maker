public class Status {
    public static int waterLevel = 250;
    public static int minWaterLevel = 150;
    public static int milkLevel = 200;
    public static int minMilkLevel = 150;
    public static float coffeeBeansLevel = 250;
    public static float minCoffeeBeansLevel = 40;
    public static int groundContainer = 15;
    public static int maxGroundContainer = 25;
    public static float coffeeCounter = 23;
    public static float maxDescaleCounter = 250;
    public static float waterHardness = 5;
    public static float descaleCounter = coffeeCounter * waterHardness;

    public static void checkStatus() {
        if (waterLevel < minWaterLevel) {
            System.err.println("WATER LEVEL: Not enough water! " + waterLevel + "ml in the container. [" + minWaterLevel + "ml min]");
        } else {
            System.out.println("WATER LEVEL: OK! " + waterLevel + "ml in the container. [" + minWaterLevel + "ml min]");
        }

        if (milkLevel < minMilkLevel) {
            System.err.println("MILK LEVEL: Not enough MILK! " + milkLevel + "ml in the container. [" + minMilkLevel + "ml min]");
        } else {
            System.out.println("MILK LEVEL: OK! " + milkLevel + "ml in the container. [" + minMilkLevel + "ml min]");
        }

        if (coffeeBeansLevel < minCoffeeBeansLevel) {
            System.err.println("COFFEE BEANS LEVEL: Not enough coffee beans! " + coffeeBeansLevel + "g in the container. [" + minCoffeeBeansLevel + "g min]");
        } else {
            System.out.println("COFFEE BEANS LEVEL: OK! " + coffeeBeansLevel + "g in the container. [" + minCoffeeBeansLevel + "g min].");
        }

        if (groundContainer > maxGroundContainer) {
            System.err.println("GROUND CONTAINER: Ground container is full [" + maxGroundContainer + " pieces]");
        } else {
            System.out.println("GROUND CONTAINER: Relax you can still make " + (maxGroundContainer - groundContainer) + " single coffees.");
        }

        if (descaleCounter > maxDescaleCounter) {
            System.err.println("DESCALE COUNTER: If you want to enjoy delicious coffee all the time, DESCALE the coffee machine now! [" + maxDescaleCounter + "]");
        } else {
            System.out.println("DESCALE COUNTER: You can still make around " + ((maxDescaleCounter - descaleCounter) / waterHardness) + " single coffees. [" + maxDescaleCounter + " max level]");
        }
    }
}
