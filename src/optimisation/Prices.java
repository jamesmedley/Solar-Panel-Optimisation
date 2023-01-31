package optimisation;

public class Prices {

    private final String[][] PRICES; //Holds the price and time for each half hour across a day

    public Prices(String[][] prices) {
        this.PRICES = prices;
    }

    private double sumArr(double[] arr) {
        double sum = 0;
        for (Double arr1 : arr) {
            sum += arr1;
        }
        return sum;
    }

    public String[][] findPrices(boolean sort, int interval) { //Finds the total cost of importing energy for every 2.5 hour period over a day
        int length = PRICES.length - interval;
        double[] periodVals = new double[interval];
        String[] startAndEnd = new String[2];
        String[][] periods = new String[length][3];
        for (int i = 0; i < length; i++) {
            boolean end = false;
            for (int j = 0; j < periodVals.length; j++) {
                try {
                    periodVals[j] = Double.parseDouble(PRICES[i + j][0]);
                } catch (Exception e) {
                    break;
                }
            }
            startAndEnd[0] = PRICES[i][1];
            try {
                startAndEnd[1] = PRICES[i + interval - 1][1];
            } catch (Exception e) {
                break;
            }
            double sum = sumArr(periodVals);
            periods[i] = new String[]{Double.toString(sum), startAndEnd[0], startAndEnd[1]};
            if (end) {
                break;
            }
        }
        if (sort) { //if the array is needed to be sorted from lowest to highest (needed in optimisation process) then quicksort can be used
            QuickSort qs = new QuickSort();
            qs.quickSort(periods, 0, periods.length - 1);
        }
        return periods;
    }
}
