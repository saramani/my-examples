/*
 *  This is the Collabrator Class that calls the Monte Carlo Simulator.It
 *  Collects the Inputs and pass it to the Simulator
 */
package com.myexamples;

import java.text.DecimalFormat;

public class PortFolioCalculator {

    /**
     * This is the Main Method to Perform Monte Carlo Simulations. It calculates
     * the Portfolio value given an Initial Investment, AverageReturn, Risk,
     * InflationRate, NoOfYears, Number Of Simulations. It Prints the Median,
     * 90th percentile, and the 10th Percentile of the Portfolio.
     *
     * @param args
     *            The input Values
     */
    public static void main(String[] args) {
        try {
            double initialInvestment = 0.0;
            double averageReturns = 0.0;
            double risk = 0.0;
            double inflationRate = 0.0;
            int noOfYears = 0;
            int simulations = 0;

            initialInvestment = Double.parseDouble(args[0]);
            averageReturns = Double.parseDouble(args[1]);
            risk = Double.parseDouble(args[2]);
            inflationRate = Double.parseDouble(args[3]);
            noOfYears = Integer.parseInt(args[4]);
            simulations = Integer.parseInt(args[5]);

            DecimalFormat dFormat = new DecimalFormat("####,###,###.00");

            System.out.println("Initial investment: $" + dFormat.format(initialInvestment));
            System.out.println("Average Return       " + averageReturns + "%");
            System.out.println("Risk                 " + risk + "%");
            System.out.println("Inflation            " + inflationRate + "%");
            System.out.println("Number Of Years      " + noOfYears);
            System.out.println("No Of Simulations    " + simulations);
            System.out.println("***************************************\n");

            double[] portfolioValues = MonteCarloSimulator.calculatePortfolio(initialInvestment, averageReturns, risk,
                    inflationRate, noOfYears, simulations);

            System.out.println(
                    "Median Case   : $" + dFormat.format(MonteCarloSimulator.getPercentile(portfolioValues, 50)));
            System.out.println(
                    "10% Best Case : $" + dFormat.format(MonteCarloSimulator.getPercentile(portfolioValues, 90)));
            System.out.println(
                    "10% Worst Case: $" + dFormat.format(MonteCarloSimulator.getPercentile(portfolioValues, 10)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException excp) {
            System.out.println(
                    "USAGE : [InitialInvestment] [AverageReturn] [Risk] [inflationRate] [NoOfYears] [Simulations]");
            excp.printStackTrace();
        }
    }

}
