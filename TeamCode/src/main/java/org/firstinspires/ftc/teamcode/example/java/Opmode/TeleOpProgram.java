package org.firstinspires.ftc.teamcode.example.java.Opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.CommandManager;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.pedro.DriverControlled;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.example.java.Config.Intake;
import org.firstinspires.ftc.teamcode.example.java.Config.Lift;

@TeleOp (name = "Pedro TeleOp")
public class TeleOpProgram extends PedroOpMode {

    public TeleOpProgram() {
        super (Intake.INSTANCE, Lift.INSTANCE);
    }

    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    public Command driverControlled;

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

        // W/ SAMPLE TRAVEL STATE
        gamepadManager.getGamepad1().getTriangle().setPressedCommand(
                () -> new SequentialGroup( // TODO: check if '()' syntax is valid instead of 'value'
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
        // DEPOSIT STATE
        gamepadManager.getGamepad1().getCircle().setPressedCommand(
                () -> new SequentialGroup( // TODO: check if '()' syntax is valid instead of 'value'
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
        // W/O SAMPLE MOVE STATE
        gamepadManager.getGamepad1().getX().setPressedCommand(
                () -> new SequentialGroup( // TODO: check if '()' syntax is valid instead of 'value'
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
        // INTAKE FROM SUB STATE
        gamepadManager.getGamepad1().getSquare().setPressedCommand(
                () -> new SequentialGroup( // TODO: check if '()' syntax is valid instead of 'value'
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

    }
}