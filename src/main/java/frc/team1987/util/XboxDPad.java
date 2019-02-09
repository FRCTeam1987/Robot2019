package frc.team1987.util;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class XboxDPad extends Button{

    public static enum Direction {
		Down(180),
		Left(270),
		Right(90),
		Up(0);
		
		private final int value;
		
		Direction(final int value) {
			this.value = value;
		}
		
		public int get() {
			return this.value;
		}
	}
	
	private final XboxController xbox;
	private final Direction direction;
	
	public XboxDPad(final XboxController xbox, final Direction direction) {
		this.xbox = xbox;
		this.direction = direction;
    }
    
	@Override
	public boolean get() {
		return this.xbox.getPOV() == this.direction.get();
	}

}

