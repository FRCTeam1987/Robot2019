package frc.robot.util.limelight;

public enum StreamMode {
    STANDARD(0),
    PIPMAIN(1),
    PIPSECONDARY(2);

    private Number value;

    private StreamMode(final Number value) {
        this.value = value;
    }

    public Number get() {
        return this.value;
    }
}
