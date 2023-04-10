package solarproject;

import model.PVModel;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Graph {

    public JPanel drawScatter(String xTitle, String yTitle) { //Produces a scatter graph from set of data and also adds a curve of best fit using the complex mathematical model
        final XYChart chart = new XYChartBuilder().width(600).height(400).title("Scatter Plot with regresssion curve").xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        PVModel pvModel = new PVModel();
        double[] xdata = new double[1100];
        double[] ydata;
        for (int i = 0; i < 1100; i++) {//generates an array of x datapoints from 0 to 1099
            xdata[i] = i;
        }
        ydata = pvModel.useModel(xdata);//creates set of data of values of the function up to x=1100
        chart.addSeries("Data set", pvModel.getGHIData(), pvModel.getPPVData());//all datapoints from model added to chart
        XYSeries line = chart.addSeries("Curve Fit", xdata, ydata);//x and y data points transformed in model function added to chart
        line.setXYSeriesRenderStyle(XYSeriesRenderStyle.Line);
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(600, 400);
        return chartPanel;
    }

    public JPanel drawLineGraph(String title, String xTitle, String yTitle, double[] yData) { //Produces a line graph plot of a set of data. This method is overloaded to take into account if multiple datasets were represented on the same axis or if the area under the graph was shaded in.
        final XYChart chart = new XYChartBuilder().width(1429).height(722).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setCursorEnabled(true);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        Number[] numsArr = toNums(yData);
        XYSeries series = chart.addSeries("Data Set", Arrays.asList(createTimeArr(96)), new ArrayList<Number>(Arrays.asList(numsArr)));//adds one set of data with time on the x-axis as a Date object
        series.setMarker(SeriesMarkers.NONE);
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(1429, 722);
        return chartPanel;
    }

    public JPanel drawLineGraphGHI(String title, String Xtitle, String Ytitle, double[] Ydata, double[] Ydata10, double[] Ydata90) { //Draws a line graph with shaded high and low scenarios from 3 sets of data in the parameters.
        final XYChart chart = new XYChartBuilder().width(1429).height(722).title(title).xAxisTitle(Xtitle).yAxisTitle(Ytitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setZoomEnabled(false);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        chart.getStyler().setPlotGridHorizontalLinesVisible(false);
        chart.getStyler().setCursorEnabled(true);
        Number[] numsArr = toNums(Ydata);
        Number[] numsArr10 = toNums(Ydata10);
        Number[] numsArr90 = toNums(Ydata90);
        XYSeries series90 = chart.addSeries("High Scenario", Arrays.asList(createTimeArr(96)), new ArrayList<Number>(Arrays.asList(numsArr90)));//adds high scenario dataset to graph 
        XYSeries series10 = chart.addSeries("Low Scenario", Arrays.asList(createTimeArr(96)), new ArrayList<Number>(Arrays.asList(numsArr10)));//adds low scenario dataset to graph
        XYSeries series = chart.addSeries("Data Set", Arrays.asList(createTimeArr(96)), new ArrayList<Number>(Arrays.asList(numsArr)));//adds mean most likely scenario dataset to graph
        series.setMarker(SeriesMarkers.NONE);//code below adds colours and shading to each plot to acheive desired effect
        series.setLineColor(Color.red);
        series90.setMarker(SeriesMarkers.NONE);
        series90.setXYSeriesRenderStyle(XYSeriesRenderStyle.Area);
        series90.setFillColor(new Color(211, 211, 211, 170));
        series10.setMarker(SeriesMarkers.NONE);
        series10.setXYSeriesRenderStyle(XYSeriesRenderStyle.Area);
        series10.setFillColor(new Color(255, 255, 255));
        series10.setLineColor(new Color(211, 211, 211, 170));
        series90.setLineColor(new Color(211, 211, 211, 170));
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(1429, 722);
        return chartPanel;
    }

    public JPanel drawLineGraph(String title, String Xtitle, String Ytitle, double[] Xdata, double[]... Ydata) { //Produces a line graph plot of a set of data. This method is overloaded to take into account if multiple datasets were represented on the same axis or if the area under the graph was shaded in.
        final XYChart chart = new XYChartBuilder().width(1429).height(722).title(title).xAxisTitle(Xtitle).yAxisTitle(Ytitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        for (int i = 0; i < Ydata.length; i++) { // method uses varargs to add any amount of datasets to a graph this loop iterates through vararg parameter to add all the plots
            XYSeries series = chart.addSeries(Integer.toString(i), Xdata, Ydata[i]);
            series.setMarker(SeriesMarkers.NONE);
        }
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(1429, 722);
        return chartPanel;
    }

    public JPanel drawLineGraph(String title, String Xtitle, String Ytitle, String[] dataTitles, String[] Xdata, double[]... Ydata) { //Produces a line graph plot of a set of data. This method is overloaded to take into account if multiple datasets were represented on the same axis or if the area under the graph was shaded in.
        final XYChart chart = new XYChartBuilder().width(1429).height(722).title(title).xAxisTitle(Xtitle).yAxisTitle(Ytitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
        chart.getStyler().setCursorEnabled(true);
        for (int i = 0; i < Ydata.length; i++) {
            if (Ydata[i] == null) {
                continue;
            }
            Number[] numsArr = toNums(Ydata[i]);
            XYSeries series = chart.addSeries(dataTitles[i], Arrays.asList(toDateArr(Xdata)), new ArrayList<Number>(Arrays.asList(numsArr)));
            series.setMarker(SeriesMarkers.NONE);
        }
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(1429, 722);
        return chartPanel;
    }

    public JPanel drawBarChart(String title, String xTitle, String yTitle, String[][] data) { //Produces a bar chart from inputted data
        String[][] split = splitArray(data);
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setAnnotationsRotation(90);
        chart.getStyler().setPlotBackgroundColor(new Color(23, 0, 72));
        chart.getStyler().setSeriesColors(new Color[]{new Color(254, 71, 215)});
        chart.getStyler().setXAxisLabelRotation(85);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotTicksMarksVisible(false);
        double[] doubleArr = Arrays.stream(reverseArr(split[0])).mapToDouble(Double::parseDouble).toArray();
        Number[] numsArr = toNums(doubleArr);
        chart.addSeries("test", Arrays.asList(reverseArr(split[1])), new ArrayList<Number>(Arrays.asList(numsArr)));
        JPanel chartPanel = new XChartPanel<CategoryChart>(chart);
        chartPanel.setVisible(true);
        chartPanel.setSize(1429, 722);
        return chartPanel;
    }

    private String[][] splitArray(String[][] array) { //Splits a 2d array with many arrays that hold 2 values into a 2d array with 2 sub arrays where the first contains all the values that were first in each pair and the second contains all the values that were second in the original pairs
        int length = array.length;
        String[] arr1 = new String[length];
        String[] arr2 = new String[length];
        for (int i = 0; i < length; i++) {
            arr1[i] = array[i][0];
            arr2[i] = array[i][1].substring(11, 19);
        }
        String[][] split = new String[2][length];
        split[0] = arr1;
        split[1] = arr2;
        return split;
    }

    private Number[] toNums(double[] d) {
        int length = d.length;
        Number[] nums = new Number[length];
        for (int i = 0; i < length; i++) {
            nums[i] = d[i];
        }
        return nums;
    }

    private String[] reverseArr(String[] arr) { //Reverses the order of an array
        int length = arr.length;
        String[] newArr = new String[length];
        for (int i = 0; i < length; i++) {
            newArr[i] = arr[length - 1 - i];
        }
        return newArr;
    }

    private Date[] toDateArr(String[] arr) { //Converts a String array of dates into a Date array in the HH:mm:ss format
        int length = arr.length;
        Date[] dateArr = new Date[length];
        for (int i = 0; i < length; i++) {
            try {
                String dateStr = arr[i].substring(11, 19);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                Date date = formatter.parse(dateStr);
                dateArr[i] = date;
            } catch (ParseException ex) {
                Error err = new Error();
                err.sendError("There was a problem creating graphs.");
            }
        }
        return dateArr;
    }

    private Date[] createTimeArr(int length) { //Creates an array of Date objects which represent every half an hour from the point in time when the method is run up to a limit in the parameter
        Date[] dateArr = new Date[length];
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusMinutes(30);
        ldt = ldt.truncatedTo(ChronoUnit.HOURS).plusMinutes(30 * (ldt.getMinute() / 30));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        DateFormat formatterDate = new SimpleDateFormat("dd/MM HH:mm");
        for (int i = 0; i < length; i++) {
            try {
                String formattedDate = ldt.format(formatter);
                Date date = formatterDate.parse(formattedDate);
                dateArr[i] = date;
                ldt = ldt.plusMinutes(30);
            } catch (ParseException ex) {
                Error err = new Error();
                err.sendError("There was a problem creating graphs.");
                return null;
            }
        }
        return dateArr;
    }
}
