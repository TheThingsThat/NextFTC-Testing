package org.firstinspires.ftc.teamcode.Config.Commands;

import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;

import org.firstinspires.ftc.teamcode.Config.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Config.Subsystems.Lift;

public class ToIntake {

    public static Command move(Intake intake, Lift lift) {
            return new SequentialGroup(
                    new ParallelGroup(
                            lift.extendLeftLift(),
                            lift.extendRightLift()
                    ),
                    new ParallelGroup(
                            intake.intakeDown1(),
                            intake.intakeDown2()
                    )
        );
    }
}