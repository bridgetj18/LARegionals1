package com.qualcomm.ftcrobotcontroller.opmodes.BombSquadOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by bridgetj18 on 12/21/15.
 */
public class encoderTest1 extends OpMode{
    DcMotor left;
    DcMotor right;

    //encoder stuff (example values from tutorial)
    final static int ENCODER_CPR = 1440;
    final static double GEAR_RATIO = 2;
    final static int WHEEL_DIAMETER = 4;
    final static int DISTANCE = 50;

    final static double CIRCUMFRENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE / CIRCUMFRENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

    @Override
    public void init(){
        left = hardwareMap.dcMotor.get("m1");
        right = hardwareMap.dcMotor.get("m2");

        left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop(){
        left.setPower(1);
        right.setPower(1);

        left.setTargetPosition((int)COUNTS);
        right.setTargetPosition((int)COUNTS);

        left.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        right.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        telemetry.addData("MotorTarget", COUNTS);
        telemetry.addData("LeftPosition", left.getCurrentPosition());
        telemetry.addData("RightPosition", right.getCurrentPosition());
    }
}
