package optimisation;

public class Energy {

    private final String[][] POWER;//holds power values and a time for that value in a 2d array

    private String[] energyPeriods;//string that holds a value of energy produced for each time period

    public Energy(String[][] power) {
        this.POWER = power;
    }

    private double sumArr(String[][] arr) {
        double sum = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            sum += Double.parseDouble(arr[i][0]);
        }
        return sum;
    }

    public void findEnergy() { //Calculates a forecast for the energy that is produced by the solar panels based upon the expected power output of the panels.
        energyPeriods = new String[11];
        String[][] subArr = new String[5][3];
        double energy;
        for (int i = 0; i < POWER.length - 5; i++) {
            for (int j = 0; j < 5; j++) {
                subArr[j] = POWER[i + j];
            }
            energy = trapeziumCalculus(subArr); //calcluates energy from power values
            energyPeriods[i] = Double.toString(energy);
        }
    }

    private double trapeziumCalculus(String[][] vals) { //Uses the Trapezium Rule to perform integration on a set of data. 
        double energy = Double.parseDouble(vals[0][0]) + Double.parseDouble(vals[vals.length - 1][0]);
        energy = 0.25 * (energy + (2 * sumArr(vals)));
        return energy;
    }

    public String[][] findRatios(String[][] prices) { //Finds the ratios between electricity price and energy generated at time periods over a day.
        String[][] ratios = new String[prices.length][3];
        for (int i = 0; i < prices.length; i++) {
            try {
                ratios[i][0] = Double.toString(Double.parseDouble(prices[i][0]) / Double.parseDouble(this.energyPeriods[i]));//ratio of price and energy 
            } catch (ArithmeticException e) {
                ratios[i][0] = Double.toString(1000000000); //incase the energy produced is equal to 0 then ratio set to very high value
            }
            ratios[i][1] = prices[i][1];
            ratios[i][2] = prices[i][2];
        }
        return ratios;
    }
}
