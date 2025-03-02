package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Climber implements Subsystem {

    private final TalonFX m_climberMotor;
    
    // Climber positions for top and bottom of arm
    private static final double kClimberTopPositionRevolutions = 2; // num rotations to get to top of climber
    private static final double kClimberBottomPositionRevolutions = 0;

    /** Creates a new Climber. */
    public Climber() {
        m_climberMotor = new TalonFX(Constants.Climber.kClimberMotorId);
        
    }

    /**
     * This method is an example of the 'subsystem factory' style of command creation. A method inside
     * the subsytem is created to return an instance of a command. This works for commands that
     * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
     * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
     * method used here, to create these commands.
     */
    public Command getClimberUpCommand() {
        // The startEnd helper method takes a method to call when the command is
        // initialized and one to call when it ends
        return this.startEnd(
               
                () -> {
                    setClimberMotor(kClimberTopPositionRevolutions);
                },
                // When the command stops, stop the climber
                () -> {
                    stop();
                });
    }

    public Command getClimberDownCommand() {
        // The startEnd helper method takes a method to call when the command is
        // initialized and one to
        // call when it ends
        return this.startEnd(
                
                () -> {
                    setClimberMotor(kClimberBottomPositionRevolutions);
                },
                // When the command stops, stop the climber
                () -> {
                    stop();
                });
    }

    // An accessor method to set the position of the climber
    public void setClimberMotor(double position) {
        
        
        
        m_climberMotor.setControl(new PositionVoltage(position));

        
        
        
    }
    

    // A helper method to stop the climber.
    public void stop() {
        m_climberMotor.set(0);
    }

}
