package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimbUp extends Command{
    private Climber m_Climber;
    public ClimbUp(Climber climber) {
        m_Climber = climber;
        addRequirements(m_Climber);
    }

    @Override
    public void initialize() {
        m_Climber.setClimberMotor(0);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_Climber.stop();
    }
}
