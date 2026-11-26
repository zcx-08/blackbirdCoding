package blackbirdCoding;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class testGraph {
	//TAKEN FROM https://locusit.se/techpost/technology/jfreechart-for-basic-data-visualizations-in-java/
	  public static void main(String[] args) {
	        // Create dataset
	        DefaultCategoryDataset dataset = createDataset();
	        
	        // Create chart
	        JFreeChart chart = createChart(dataset);

	        // Display chart
	        ChartPanel chartPanel = new ChartPanel(chart); //makes a panel of a chart
	        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); //size
	        JFrame frame = new JFrame("Line Chart Example"); //creates frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(chartPanel);
	        frame.pack();
	        frame.setVisible(true); //you can see it
	    }

	    private static DefaultCategoryDataset createDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	        // Add data (Category, Series, Value)
	        		//FIGURE OUT WAY TO IMPORT INFO FROM ARDUINO TO THIS AND MAKE IT MORE COMPANT VIA VARIABLES AND CLASSES
	        dataset.addValue(1.0, "Series1", "Jan");
	        dataset.addValue(4.0, "Series1", "Feb");
	        dataset.addValue(3.0, "Series1", "Mar");
	        dataset.addValue(5.0, "Series1", "Apr");
	        dataset.addValue(4.0, "Series1", "May");
	        
	        dataset.addValue(2.0, "Series2", "Jan");
	        dataset.addValue(3.0, "Series2", "Feb");
	        dataset.addValue(4.0, "Series2", "Mar");
	        dataset.addValue(5.0, "Series2", "Apr");
	        dataset.addValue(8.0, "Series2", "May");

	        return dataset;
	    }

	    private static JFreeChart createChart(DefaultCategoryDataset dataset) {
	        return ChartFactory.createLineChart(
	            "Monthly Data",  // Title
	            "Month",         // X-axis Label
	            "Value",         // Y-axis Label
	            dataset          // Dataset
	        );
	    }
}