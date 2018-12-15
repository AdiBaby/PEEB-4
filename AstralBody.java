/**
 * Aditya Prerepa (c) 12-15-18
 * Uses java.util.LinkedHashMap
 * Use freely for educational purpose only.
 */

import java.util.LinkedHashMap;

public class AstralBody {

    /*
     * All the variables and constants are defined here,
     * stephanBoltzConst is the only constant.
     */

    double albedo, S_ave;
    final double stephanBoltzConst = 5.67 * Math.pow(10,-23);

    /*
     * This is the AstralBody constructor, and takes in the albedo
     * and S_ave of the certain planet.
     */

    public AstralBody(double albedo, double S_ave) {
        this.albedo = albedo;
        this.S_ave = S_ave;
    }

    /*
     * This method generates the dependent variable, or
     * T_p in this case. Takes in the double emissivity
     * and does the computations necessary for generating
     * T_p. Emmisivity is increased by the step of the
     * x - axis.
     */

    private double gen_T_p(double emmisivity) {
        double t_p_init = ((2 * (1-albedo) * S_ave) / (stephanBoltzConst*(2-emmisivity)));
        double T_p = Math.pow(t_p_init, 0.25);
        double T_a = T_p * Math.pow(0.5, 0.25);
        return T_a;
    }

    /*
     * This LinkedHashMap defined method takes in the variables start,
     * end, and step, which are the increments for the x-axis.
     * We use a LinkedHashMap because we dont want the stuff
     * to be mixed up.
     */

    public LinkedHashMap<Double, Double> gen_T_p_map(double start, double end, double step) {
        LinkedHashMap<Double, Double> get_T_p_vals_Map = new LinkedHashMap<>();

        for (;  start <= end; start += step) {
            double t_p_val = gen_T_p(start);
            get_T_p_vals_Map.put(start, t_p_val);
        }

        return get_T_p_vals_Map;
    }




}
