package ru.yandex.prakticum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeedLightsTest {
    private SpeedLights speedLights;

    @Before
    public void setUp() {
        this.speedLights = new SpeedLights();
    }

    @Test
    public void testShowLightGreenWhenSpeedBelowLimit() {
        assertEquals("green", speedLights.showLight(50));
    }

    @Test
    public void testShowLightGreenWhenSpeedAtLimit() {
        assertEquals("green", speedLights.showLight(59));
    }

    @Test
    public void testShowLightYellowWhenSpeedAtLowerBoundary() {
        assertEquals("yellow", speedLights.showLight(60));
    }

    @Test
    public void testShowLightYellowWhenSpeedAtUpperBoundary() {
        assertEquals("yellow", speedLights.showLight(80));
    }

    @Test
    public void testShowLightRedWhenSpeedAtLowerBoundary() {
        assertEquals("red", speedLights.showLight(81));
    }

    @Test
    public void testShowLightRedWhenSpeedAboveLimit() {
        assertEquals("red", speedLights.showLight(100));
    }
}