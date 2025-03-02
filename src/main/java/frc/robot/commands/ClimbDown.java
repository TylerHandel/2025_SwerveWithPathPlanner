package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimbDown extends Command{
    private Climber m_Climber;
    public ClimbDown(Climber climber) {
        m_Climber = climber;
        addRequirements(m_Climber);
    }

    @Override
    public void initialize() {
        m_Climber.setClimberMotor(-0.1);
    }
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        m_Climber.stop();
    }

    public void stopOuttake() {
        m_Climber.stop();
    }
}
