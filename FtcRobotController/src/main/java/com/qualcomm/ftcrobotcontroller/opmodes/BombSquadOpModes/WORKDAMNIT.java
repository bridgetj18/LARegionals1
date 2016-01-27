package com.qualcomm.ftcrobotcontroller.opmodes.BombSquadOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class WORKDAMNIT extends LinearOpMode {
    DcMotor right;
    DcMotor left;

    int a;
    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("m1");
        left = hardwareMap.dcMotor.get("m2");

        right.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        right.setPower(0);
        left.setPower(0);

        this.resetStartTime();
        do {
            right.setPower(1);
            left.setPower(1);
            telemetry.addData("Distance", left.getCurrentPosition());
            Thread.sleep(50);
        } while (this.getRuntime() < 2);

        this.resetStartTime();
        do {
            right.setPower(0);
            left.setPower(0);
            Thread.sleep(50);
        } while (this.getRuntime() < 2);

        left.setChannelMode(DcMotorController.RunMode .RESET_ENCODERS);
        right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        do {
            right.setPower(1);
            left.setPower(1);
            telemetry.addData("Distance", left.getCurrentPosition());
            Thread.sleep(50);
        } while (right.getCurrentPosition() > -1440 );


        right.setPower(0);
        left.setPower(0);
    }
}
