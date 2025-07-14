package org.firstinspires.ftc.teamcode.Opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.CommandManager;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.pedro.DriverControlled;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.Config.Commands.ToBasket;
import org.firstinspires.ftc.teamcode.Config.Commands.ToIntake;
import org.firstinspires.ftc.teamcode.Config.Commands.ToSub;
import org.firstinspires.ftc.teamcode.Config.Commands.ToDeposit;
import org.firstinspires.ftc.teamcode.Config.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Config.Subsystems.Lift;

@TeleOp (name = "NextFTC TeleOp")
public class TeleOpProgram extends PedroOpMode {

    public TeleOpProgram() {
        super (Intake.INSTANCE, Lift.INSTANCE);
    }

    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    @Override
    public void onInit() {
        frontLeftMotor = new MotorEx("frontLeftName");   // TODO: add name
        backLeftMotor = new MotorEx("backLeftName");     // TODO: add name
        backRightMotor = new MotorEx("backRightName");   // TODO: add name
        frontRightMotor = new MotorEx("frontRightName"); // TODO: add name

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);  // TODO: check direction
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);   // TODO: check direction
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD); // TODO: check direction
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);  // TODO: check direction

        motors = new MotorEx[] {frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
    }

    @Override
    public void onStartButtonPressed() {

        CommandManager.INSTANCE.scheduleCommand(new DriverControlled(gamepadManager.getGamepad1(), true));

        // CLAW VERTICAL
        gamepadManager.getGamepad1().getDpadUp().setPressedCommand(
                Intake.INSTANCE::clawVer
        );
        // CLAW HORIZONTAL
        gamepadManager.getGamepad1().getTriangle().setPressedCommand(
                Intake.INSTANCE::clawHor
        );

        // ToIntake
        gamepadManager.getGamepad1().getLeftTrigger().setPressedCommand(
                value -> ToIntake.move(Intake.INSTANCE, Lift.INSTANCE)
        );
        // ToBasket
        gamepadManager.getGamepad1().getLeftBumper().setPressedCommand(
                () -> ToBasket.move(Intake.INSTANCE, Lift.INSTANCE)
        );
        // ToDeposit
        gamepadManager.getGamepad1().getRightBumper().setPressedCommand(
                () -> ToDeposit.move(Intake.INSTANCE, Lift.INSTANCE)
        );
        // ToSub
        gamepadManager.getGamepad1().getRightTrigger().setPressedCommand(
                value -> ToSub.move(Intake.INSTANCE, Lift.INSTANCE)
        );



        // –––––––––– TeleOp Independent Command Implementation (OLD) ––––––––––
        /*

        // ToIntake
        gamepadManager.getGamepad1().getLeftTrigger().setPressedCommand(
                value -> new SequentialGroup(
                        new ParallelGroup(
                                Lift.INSTANCE.extendLeftLift(),
                                Lift.INSTANCE.extendRightLift()
                        ),
                        new ParallelGroup(
                                Intake.INSTANCE.intakeDown1(),
                                Intake.INSTANCE.intakeDown2()
                        )
                )
        );
        // ToBasket
        gamepadManager.getGamepad1().getLeftBumper().setPressedCommand(
                () -> new SequentialGroup(
                        Intake.INSTANCE.closeClaw(),
                        new ParallelGroup(
                                new ParallelGroup(
                                        Intake.INSTANCE.intakeUp1(),
                                        Intake.INSTANCE.intakeUp2()
                                ),
                                new ParallelGroup(
                                        Lift.INSTANCE.retractLeftLift(),
                                        Lift.INSTANCE.retractRightLift()
                                )
                        ),
                        Lift.INSTANCE.pivotUp(),
                        Intake.INSTANCE.clawVer()
                )
        );
        // ToDeposit
        gamepadManager.getGamepad1().getRightBumper().setPressedCommand(
                () -> new SequentialGroup(
                        new ParallelGroup(
                                Lift.INSTANCE.extendLeftLift(),
                                Lift.INSTANCE.extendRightLift()
                        ),
                        new ParallelGroup(
                                Intake.INSTANCE.intakeDeposit1(),
                                Intake.INSTANCE.intakeDeposit2()
                        )
                )
        );
        // ToSub
        gamepadManager.getGamepad1().getRightTrigger().setPressedCommand(
                value -> new SequentialGroup(
                        Intake.INSTANCE.openClaw(),
                        new Delay(0.2),
                        new ParallelGroup(
                                Intake.INSTANCE.intakeUp1(),
                                Intake.INSTANCE.intakeUp2(),
                                Lift.INSTANCE.retractLeftLift(),
                                Lift.INSTANCE.retractRightLift()
                        ),
                        Lift.INSTANCE.pivotDown()
                )
        );
         */
    }
}