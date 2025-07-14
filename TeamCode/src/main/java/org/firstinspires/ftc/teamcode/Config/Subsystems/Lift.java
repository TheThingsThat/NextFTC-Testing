package org.firstinspires.ftc.teamcode.Config.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

import org.firstinspires.ftc.teamcode.Config.RobotConstants;

public class Lift extends Subsystem{
    public static final Lift INSTANCE = new Lift();
    private Lift() { }

    public MotorEx liftLeft, liftRight, pivot;

    public void initialize() {
        liftLeft = new MotorEx("viper1"); // TODO: check motor orientation
        liftRight = new MotorEx("viper2"); // TODO: check motor orientation
    }

    public PIDFController liftController = new PIDFController(
            RobotConstants.liftP,
            RobotConstants.liftI,
            RobotConstants.liftD); // TODO: add dynamic feedforward
    public PIDFController pivotController = new PIDFController(
            RobotConstants.pivotP,
            RobotConstants.pivotI,
            RobotConstants.pivotD); // TODO: add dynamic feedforward

    public Command retractLeftLift() {
        return new RunToPosition(liftLeft, RobotConstants.liftRetract, liftController, this);
    }
    public Command retractRightLift() {
        return new RunToPosition(liftRight, RobotConstants.liftRetract, liftController, this);
    }
    public Command extendLeftLift() {
        return new RunToPosition(liftLeft, RobotConstants.liftExtend, liftController, this);
    }
    public Command extendRightLift() {
        return new RunToPosition(liftRight, RobotConstants.liftExtend, liftController, this);
    }

    public Command pivotUp() {
        return new RunToPosition(pivot, RobotConstants.pivotUp, pivotController, this);
    }
    public Command pivotDown() {
        return new RunToPosition(pivot, RobotConstants.pivotDown, pivotController, this);
    }
}