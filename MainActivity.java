/**
 * Aditya Prerepa (c) 12-15-18
 * Uses JFreeChart API and java.util.Map
 * Use frrely for educational purpose only.
 */

import java.util.LinkedHashMap;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Map;


public class MainActivity extends ApplicationFrame{

    /*
     * Constructor for Main Class (MainActivity), this class needs
     * to extend ApplicationFrame. This does all the initialization
     * for the chart and frame stuff. Dimensions set here too.
     */
    public MainActivity(String applicationTitle, String chartTitle, LinkedHashMap<Double,Double> T_p_ValuesInArray) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "emmisivity",
                "T_p", createDataset(T_p_ValuesInArray), PlotOrientation.VERTICAL, true,
                true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension(1280, 960));
        setContentPane(chartPanel);

    }

    /*
     * DefaultCategoryDataset that takes a LinkedHashMap of the Values
     * (because a normal HashMap has no linking) and the key and value
     * are the x and y axis (Emissivity and T_p) that we have to plot.
     * We add it to the Object DefaultCategoryDataset.
     */

    private DefaultCategoryDataset createDataset( LinkedHashMap<Double, Double> T_p_ValuesInArray) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(Map.Entry<Double, Double> entry : T_p_ValuesInArray.entrySet()) {
            Double emmisivity = entry.getKey();
            Double T_p = entry.getValue();
            dataset.addValue(T_p, "Earth", emmisivity);
            System.out.println("Emmisivity : " + emmisivity + "     T_p " + T_p.toString());
        }
        return dataset;
    }

    /*
     * Main method, we create the AstralBody Object with the albedo and
     * S_ave of planet. We also create another LinkedHashMap that is created
     * from the earth object, where the math and values are being generated.
     * We also give the step of the x axis here (0-1 incrementing 0.1), so it knows when
     * to add the values. We also call the MainActivity class's constructor where we give
     * the title of the application, the title of the chart, and the LinkedHashMap of the
     * values.
     */

    public static void main(String[] args) {
        AstralBody earth = new AstralBody(0.3, 342);
        LinkedHashMap<Double, Double> t_p_vals = earth.gen_T_p_map(0,1,0.1);
        MainActivity chart = new MainActivity("PEEB 4 Graph" , "PEEB-4 RESULTS", t_p_vals);
        System.out.println("Generating Graph.....");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    /*
     * When the main method runs, there is a call to the LinkedHashMap being
     * generated in the AstralBody class, which is then immediately passed to
     * the MainActivity constructor. There, the constructor calls the DefaultCatagoryDataset
     * instance, and passes the LinkedHashMap we generated in main(). From there, it
     * gets the keys and values and adds them the graph.
     */
}
