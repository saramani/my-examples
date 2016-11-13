/*
 *  This is the Main Class that does the Monte Carlo Smulations
 *
 */
package com.myexamples;

import java.util.Random;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class MonteCarloSimulator {

    private static final Random r = new Random();

    /**
     * Calculate the Portfolio using Monte Carlo Simulation
     *
     * @param initialInvestment Initial Investment of the Portfolio.
     * @param averageReturn Average Rate of the portfolio
     * @param risk Risk of the Portfolio
     * @param inflation Rate of Inflation
     * @param noOfYears Number of Years for Each Simulation.
     * @param simulations Number of simulations
     * @return the portfolio values
     */
    public static final double[] calculatePortfolio(double initialInvestment, double averageReturn,
            double risk, double inflation, int noOfYears, int simulations) {

        double[] portfolioValues = new double[simulations];

        for (int x = 0; x < simulations; x++) {
            double portfolioValue = initialInvestment;

            for (int y = 0; y < noOfYears; y++) {
                portfolioValue = calculatePortfolioValueForAYear(portfolioValue, averageReturn, risk, inflation);
            }
            portfolioValues[x] = portfolioValue;
        }

        return portfolioValues;
    }

    /**
     * Calculate the Portfolio using Monte Carlo Simulation
     *
     * @param initialInvestment Initial Investment of the Portfolio.
     * @param averageReturn Average Rate of the portfolio
     * @param risk Risk of the Portfolio
     * @param inflation Rate of Inflation
     * @param noOfYears Number of Years for Each Simulation.
     * @param simulations Number of simulations
     * @return the portfolio values
     */
    public static final double calculatePortfolioValueForAYear(double portfolioValue, double averageReturn,
            double risk, double inflationRate) {
        double anulaizedRate = r.nextGaussian() * risk + averageReturn;
        double annualReturn = portfolioValue * (anulaizedRate / 100.0);
        double inflationAdjustment = portfolioValue * (inflationRate / 100.0);
        return portfolioValue + (annualReturn - inflationAdjustment);
    }

    /**
     * Get the nth Percentile
     *
     * @param values The list of Values for which Percentile needs to be calculated
     * @param percentile the Percentile
     * @return the Nth Percentile
     */
    public static double getPercentile(double[] values, double percentile) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        int arrayLength = values.length;
        for( int i = 0; i < arrayLength; i++) {
            stats.addValue(values[i]);
        }
        return stats.getPercentile(percentile);
    }

}
