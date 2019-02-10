package frc.robot.util.limelight;

public enum CameraMode {

    VISION(0),
    DRIVERCAMERA(1);

    private Number value;

    private CameraMode(final Number value) {
        this.value = value;
    }

    public Number get() {
        return this.value;
    }
}
