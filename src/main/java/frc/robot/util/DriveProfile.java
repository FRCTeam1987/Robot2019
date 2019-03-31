package frc.robot.util;

public class DriveProfile {
	
	private final double KP;
	private final double KD;
	private final double KI;
	private final double DT;
	private final double GP;
	private final double GD;
	private final double maxVelocity;
	private final double KV;
	private final double maxAcceleration;
	private final double KA;
	private final double maxJerk;
	
	public DriveProfile(
			final double KP,
			final double KD, 
			final double KI, 
			final double DT,
			final double GP, 
			final double GD, 
			final double maxVelocity, 
			final double KV, 
			final double maxAcceleration, 
			final double KA, 
			final double maxJerk) {
		
		this.KP = KP;
		this.KD = KD;
		this.KI = KI;
		this.DT = DT;
		this.GP = GP;
		this.GD = GD;
		this.maxVelocity = maxVelocity;
		this.KV = KV;
		this.maxAcceleration = maxAcceleration;
		this.KA = KA;
		this.maxJerk = maxJerk;		
	}
	
	public double getKP() {
		return KP;
	}
	
	public double getKD() {
		return KD;
	}
	
	public double getKI() {
		return KI;
	}
	
	public double getDT() {
		return DT;
	}
	
	public double getGP() {
		return GP;		
	}
	
	public double getGD() {
		return GD;
	}
	
	public double getMaxVelocity() {
		return maxVelocity;
	}
	
	public double getKV() {
		return KV;
	}
	
	public double getMaxAcceleration() {
		return maxAcceleration;
	}
	
	public double getKA() {
		return KA;
	}
	
	public double getMaxJerk() {
		return maxJerk;
	}
}