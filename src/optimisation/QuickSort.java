package optimisation;

public class QuickSort {

    //algorithm from https://www.geeksforgeeks.org/quick-sort/
    private int partition(String[][] list, int start, int end) { //splits array around a value then puts that value in sorted array and put all smaller elements before that value and all greater elements after that value
        double pivot = Double.parseDouble(list[end][0]);
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if (Double.parseDouble(list[j][0]) < pivot) {
                i++;
                String[] temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
        String[] temp = list[i + 1];
        list[i + 1] = list[end];
        list[end] = temp;
        return (i + 1);
    }

    public void quickSort(String[][] list, int start, int end) { //A recursive method that can order an array of items very fast
        if (start < end) {
            int part = partition(list, start, end);
            quickSort(list, start, part - 1);
            quickSort(list, part + 1, end);
        }
    }
}
