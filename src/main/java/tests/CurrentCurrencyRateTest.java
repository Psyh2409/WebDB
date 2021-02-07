package tests;

import com.gmail.psyh2409.utils.CurrentCurrencyRate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CurrentCurrencyRateTest {


    @Test
    public void getEURandUSDRangeNowReturnsDoubleArrayTest(){
        Double[] rate = CurrentCurrencyRate.getEURandUSDrangeNow();
        assertNotNull(rate);
        assertEquals(2, rate.length);
        assertNotNull(rate[0]);
        assertNotNull(rate[1]);
    }
}