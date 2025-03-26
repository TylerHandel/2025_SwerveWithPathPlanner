package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Climber implements Subsystem {

    private final TalonFX m_climberMotor;
    
    // Climber positions for top and bottom of arm
    private static final Slot0Configs climberGains = new Slot0Configs()
    .withKP(2.4).withKI(0).withKD(0.1); // was 1, 0, 0

    /** Creates a new Climber. */
    public Climber() {
        m_climberMotor = new TalonFX(Constants.Climber.kClimberMotorId);
        m_climberMotor.getConfigurator().apply(climberGains);
    }

    // An accessor method to set the position of the climber
    public void setClimberPosition(double position) {
        m_climberMotor.setControl(new PositionVoltage(position));
    }
    
    // A helper method to stop the climber.
    public void stop() {
        m_climberMotor.setControl(new StaticBrake());
    }

}
