package com.qualcomm.ftcrobotcontroller.opmodes.BombSquadOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by bridgetj18 on 12/21/15.
 */
public class encoderDeconstructed extends OpMode{
    DcMotor left1;

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
        left1 = hardwareMap.dcMotor.get("m1");

        left1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop(){
        left1.setPower(1);

        left1.setTargetPosition((int) COUNTS);

        left1.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        telemetry.addData("MotorTarget", COUNTS);
        telemetry.addData("LeftPosition", left1.getCurrentPosition());
        telemetry.addData("Rotations", ROTATIONS);
    }
}
