package frc.robot.util.limelight;

import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.util.limelight.CameraMode;

public class Limelight {

    private final String tableName;

    public Limelight() {
        tableName = "limelight";
    }

    public Limelight(final String suffix) {
        tableName = "limelight-" + suffix;
    }

    public double get(final String entry){
        return NetworkTableInstance.getDefault().getTable(tableName).getEntry(entry).getDouble(0);
    }

    public double getCameraMode() {
        return this.get("cameraMode"); 
    }
    
    public double getLedMode() {
        return this.get("ledMode");
    }

    public double getPipeline() {
        return this.get("pipline");
    }

    public double getSnapshot() {
        return this.get("snapshot");
    }

    public double getStream() {
        return this.get("stream");
    }

    public double getTa() {
        return this.get("ta");
    }

    public double getThor() {
        return this.get("thor");
    }

    public double getTl() {
        return this.get("tl");
    }

    public double getTlong() {
        return this.get("tlong");
    }

    public double getTs() {
        return this.get("ts");
    }

    public double getTshort() {
        return this.get("tshort");
    }

    public double getTv() {
        return this.get("tv");
    }

    public double getTvert() {
        return this.get("tvert");
    }

    public double getTx() {
        return this.get("tx");
    }

    public double getTy() {
        return this.get("ty");
    }

    public void set(final String entry, final Number value) {
        NetworkTableInstance.getDefault().getTable(tableName).getEntry(entry).setNumber(value);
    }

    public void setCameraMode(final CameraMode mode) {
        this.set("cameraMode", mode.get());
    }

    public void setLedMode(final LedMode mode) {
        this.set("ledMode", mode.get());
    }

    public void setPipeline(final Number pipeline) {
        this.set("pipeline", pipeline);
    }

    public void setSnapshot(final Snapshot mode) {
        this.set("snapshot", mode.get());
    }

    public void setStream(final StreamMode mode) {
        this.set("stream", mode.get());
    }
}
