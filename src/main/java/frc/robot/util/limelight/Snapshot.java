package frc.robot.util.limelight;

public enum Snapshot {
    STOPSNAPSHOTS(0),
    TAKESNAPSHOTS(1);

    private Number value;

    private Snapshot(final Number value) {
        this.value = value;
    }

    public Number get() {
        return this.value;
    }
}
