
import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histogramme {
	 private static final int BINS = 256;
	    private static BufferedImage image = getImage();
	    private static HistogramDataset dataset;
	    private XYBarRenderer renderer;

/************************************Selecting the buffered Image************************************************************************/	    
	  
	    private static BufferedImage getImage() {
	        	try {
	    			image = ImageIO.read(new File("img.png"));
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
				return image;  
	    }
	    
	    
/**********************************Extracting the values of each color on the "img.png" image*******************************************/

	    static HistogramDataset ExtractColorValues(HistogramDataset dataset) {  
     
	    Raster raster = image.getRaster();
     final int w = image.getWidth();
     final int h = image.getHeight();
     double[] r = new double[w * h];
     r = raster.getSamples(0, 0, w, h, 0, r);
     dataset.addSeries("Red", r, BINS);
     r = raster.getSamples(0, 0, w, h, 1, r);
     dataset.addSeries("Green", r, BINS);
     r = raster.getSamples(0, 0, w, h, 2, r);
     dataset.addSeries("Blue", r, BINS);
     return dataset;
     
	   }
	    
/*************************************Creating a charPanel *****************************************************************************/	    
	    
	   private ChartPanel createChartPanel() {
	        dataset = new HistogramDataset();
	        ExtractColorValues(dataset);
	        
	        // chart
	        
	        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value","Count", dataset, PlotOrientation.VERTICAL, true, true, false);
	        XYPlot plot = (XYPlot) chart.getPlot();
	        renderer = (XYBarRenderer) plot.getRenderer();
	        renderer.setBarPainter(new StandardXYBarPainter());

	        // translucent red, green & blue
	        
	       Paint[] paintArray = {new Color(0x80ff0000, true),new Color(0x8000ff00, true),new Color(0x800000ff, true)};
	        plot.setDrawingSupplier(new DefaultDrawingSupplier(
	            paintArray,
	            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
	            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
	        ChartPanel panel = new ChartPanel(chart);
	        panel.setMouseWheelEnabled(true);
	        return panel;
	    }
	    

/**************************************exhibiting the histogram******************************************************************************/
	    
	    void display() {
	        JFrame f = new JFrame("Histogram");
	        f.add(createChartPanel());
	        f.pack();
	        f.setLocationRelativeTo(null);
	        f.setVisible(true);
	    }

}
