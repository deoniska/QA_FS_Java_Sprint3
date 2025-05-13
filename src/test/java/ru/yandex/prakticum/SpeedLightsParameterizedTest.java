package ru.yandex.prakticum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SpeedLightsParameterizedTest {

    private final int speed;
    private final String expectedColor;
    private SpeedLights speedLights;

    public SpeedLightsParameterizedTest(int speed, String expectedColor) {
        this.speed = speed;
        this.expectedColor = expectedColor;
    }

    @Before
    public void setUp() {
        speedLights = new SpeedLights();
    }

    @Parameterized.Parameters(name = "Speed: {0} km/h -> Expected color: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {50, "green"},   // Ниже лимита
                {59, "green"},   // Граница зеленой зоны
                {60, "yellow"},  // Начало желтой зоны
                {70, "yellow"},  // Середина желтой зоны
                {80, "yellow"}, // Граница желтой зоны
                {81, "red"},     // Начало красной зоны
                {100, "red"}    // Выше лимита
        });
    }

    @Test
    public void testShowLightWithParameters() {
        assertEquals(expectedColor, speedLights.showLight(speed));
    }
}