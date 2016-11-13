package com.myexamples;

import java.util.Random;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@PrepareForTest(MonteCarloSimulator.class)
public class MonteCarloSimulatorTest extends PowerMockTestCase {

    /**
     * This method creates the Sample data for calculating the Percentile Data.
     * This is a constant data as we do not want it to changed for unit testing
     */
    @DataProvider(name = "PercentileData")
    public Object[][] createPercentileData() {
        double[] a = { 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0 };
        Object[][] percentileData = { { a, 10, 11.0 }, { a, 50.0, 55.0 }, { a, 90.00, 99.0 } };
        return percentileData;
    }

    @Test(dataProvider = "PercentileData")
    public void test_percentile(double[] values, double percentile, double result) {
        double result10th = MonteCarloSimulator.getPercentile(values, percentile);
        double result50th = MonteCarloSimulator.getPercentile(values, percentile);
        double result90th = MonteCarloSimulator.getPercentile(values, percentile);
        Assert.assertEquals(result10th, result);
        Assert.assertEquals(result50th, result);
        Assert.assertEquals(result90th, result);
    }

    /**
     * We are keeping the Normal Distribution a Constant to test the Portfolio Value.
     */
    @Test
    public void test_calculatePortfolio() throws Exception {
        Random mockedRandom = PowerMockito.mock(Random.class);
        PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(mockedRandom);
        PowerMockito.doReturn(1.0).when(mockedRandom).nextGaussian();

        double portfolioValue = 100_000.00;
        double averageReturn = 2.0;
        double risk = 1.0;
        double inflationRate = 3.0;
        int noOfYears = 1;
        int simmulations = 1;

        double[] result = MonteCarloSimulator.calculatePortfolio(portfolioValue, averageReturn, risk, inflationRate,
                noOfYears, simmulations);
        Assert.assertEquals(result[0], 100000.0);
    }

    /**
     * We are keeping the Normal Distribution a Constant to test the Portfolio Value.
     */
    @Test
    public void test_calculatePortfolioValueForAYear() throws Exception {
        Random mockedRandom = PowerMockito.mock(Random.class);
        PowerMockito.whenNew(Random.class).withNoArguments().thenReturn(mockedRandom);
        PowerMockito.doReturn(1.0).when(mockedRandom).nextGaussian();

        double portfolioValue = 100_000.00;
        double averageReturn = 2.0;
        double risk = 1.0;
        double inflationRate = 3.0;

        double result1 = MonteCarloSimulator.calculatePortfolioValueForAYear(portfolioValue, averageReturn, risk,
                inflationRate);
        Assert.assertEquals(result1, 100000.0);
    }

}
