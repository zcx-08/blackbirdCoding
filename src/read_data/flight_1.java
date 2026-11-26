package read_data;

import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import javax.swing.*;

public class flight_1 {

	public static void printData(int lim, String name, String[][] data ){
		System.out.println(name);
		double[][]gyro_data = new double[lim][3];
		for (int i = 0; i < lim; i++) {
			for (int j = 0; j < 3; j++) {
				gyro_data[i][j] = Double.parseDouble(data[i][j+1]); //Converts from string to double and fills array
				System.out.print(gyro_data[i][j] + " \t "); //Prints
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Scanner in = new Scanner (new File ("IMU_EKF_flight_data_1.csv"));
		String data_name = in.nextLine();
		System.out.println(data_name); //Prints the header (time,gryx,gryy....)

		//Determine the no. columns 
		String[] name = data_name.split(",");
		int col = name.length;  
		int temp_lim = 100;

		//Add a count to read the amount of lines there are 
		String data[][] = new String [temp_lim][col];
		for(int i = 0; i < temp_lim; i++) { //replace limit later
			String lines = in.nextLine();
			String[] split_lines = lines.split(",");

			for (int j = 0; j < col; j++) {
				data[i][j] = split_lines[j];
			}
		}

		//Prints out data
		/*
		for (int i = 0; i < temp_lim; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}
		 */

		//Add array for gyro data and change it into double 

		printData(100,"gyro_data", data);

		//Add array for accel data and change it into double 
		System.out.println("Acceleration Data");
		double[][]accel_data = new double[temp_lim][3];
		for (int i = 0; i < temp_lim; i++) {
			for (int j = 0; j < 3; j++) {
				accel_data[i][j] = Double.parseDouble(data[i][j+4]); //Converts from string to double and fills array					
				System.out.print(accel_data[i][j] + " \t "); //Prints
			}
			System.out.println();
		}
		System.out.println();

		//Add array for orientation data and change it into double 
		System.out.println("Orientation Data");
		double[][] orientation_data = new double[temp_lim][3];
		for (int i = 0; i < temp_lim; i++) {
			for (int j = 0; j < 3; j++) {
				orientation_data[i][j] = Double.parseDouble(data[i][j + 7]); //Converts from string to double and fills array
				System.out.print(orientation_data[i][j] + " \t "); //Prints
			}
			System.out.println();
		}
		in.close();
		
		//makegraph(gyro_data,data, "Gyro Data");
		makegraph(accel_data,data, "Acceleration Data");
		makegraph(orientation_data,data, "Orientation Data");
	}
	public static void makegraph (double data_type[][], String time[][],String name) {
		DefaultCategoryDataset dataset = createDataset(data_type, time);

		// Create chart
		JFreeChart chart = createChart(dataset);

		// Display chart
		ChartPanel chartPanel = new ChartPanel(chart); //makes a panel of a chart
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); //size
		JFrame frame = new JFrame(name); //creates frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setVisible(true); //you can see it

		CategoryPlot plot = chart.getCategoryPlot();

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setAutoRange(true);
		yAxis.setAutoRangeIncludesZero(false);

	}
	private static DefaultCategoryDataset createDataset(double data[][], String time[][]) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String variable = "x";
		// Add data (Category, Series, Value)
		for (int i = 0; i < data.length; i++) {
			dataset.addValue(data[i][0], "X", time[i][0]);
		}
		for (int i = 0; i < data.length; i++) {
			dataset.addValue(data[i][1], "Y", time[i][0]);
		}
		for (int i = 0; i < data.length; i++) {
			dataset.addValue(data[i][2], "Z", time[i][0]);
		}
		for (int i = 0; i < data.length; i++) {
			dataset.addValue(Math.sqrt(data[i][0]*data[i][0]+data[i][1]*data[i][1]+data[i][2]*data[i][2]), "Normal", time[i][0]);
		}

		return dataset;
	}

	private static JFreeChart createChart(DefaultCategoryDataset dataset) {
		return ChartFactory.createLineChart(
				"Data",  // Title
				"Time",         // X-axis Label
				"Value",         // Y-axis Label
				dataset          // Dataset
				);


	}

}
