package frc.robot.util.limelight;

public enum LedMode {

    PIPELINE(0),
    OFF(1),
    BLINK(2),
    ON(3);

    private Number value;

    private LedMode(final Number value) {
        this.value = value;
    }

    public Number get() {
        return this.value;
    }
}
