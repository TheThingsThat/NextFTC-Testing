package org.firstinspires.ftc.teamcode.Config.Commands;

import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;

import org.firstinspires.ftc.teamcode.Config.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Config.Subsystems.Lift;

public class ToBasket {

    public static Command move(Intake intake, Lift lift) {
            return new SequentialGroup(
                    intake.closeClaw(),
                    new ParallelGroup(
                            new ParallelGroup(
                                    intake.intakeUp1(),
                                    intake.intakeUp2()
                            ), new ParallelGroup(
                                    lift.retractLeftLift(),
                                    lift.retractRightLift()
                            )
                    ),
                    lift.pivotUp(),
                    intake.clawVer()
        );
    }
}