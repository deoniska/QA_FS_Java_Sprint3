package ru.yandex.prakticum;

public class SpeedLights {
    /**
     * Ограничение на скорость 60 км/ч
     * если нет превышения - color green
     * если превышение <= 20 - color yellow
     * если превышение > 20 - color red
     */

    public String showLight(int currentLight) {
        if (currentLight < 60) {
            return "green";
        } else if (currentLight <= 80) {
            return "yellow";
        } else {
            return "red";
        }
    }
}
