package com.qualcomm.ftcrobotcontroller.opmodes.BombSquadOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class First extends LinearOpMode{

    DcMotor right;
    DcMotor left;

    //encoder stuff (example values from tutorial)
    final static int ENCODER_CPR = 1440;
    final static double GEAR_RATIO = 2;
    final static int WHEEL_DIAMETER = 4;
    final static int DISTANCE = 25;

    final static double CIRCUMFRENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE / CIRCUMFRENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

    double speed = 1;

    //distances
    int a;
    int b;
    int c;
    int d;

    //target headings
    int targetHeadingA = 0;
    int TargetHeadingB = -135;

    //pid calculations
    double gain = 0.1;
    double steeringError;
    double leftPower;
    double rightPower;
    int currentHeading = 0;
    double steeringAdjustment = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("m1");
        left = hardwareMap.dcMotor.get("m2");
        right.setDirection(DcMotor.Direction.REVERSE);

        //encoders
        left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        left.setTargetPosition((int) COUNTS);
        right.setTargetPosition((int) COUNTS);

        left.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        right.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

//        gyro setup & calibration
        GyroSensor sensorGyro;
        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        sensorGyro.calibrate();
        waitForStart();
        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);

        //forward b
        while (this.getRuntime() < 5){
            currentHeading = sensorGyro.getHeading();
            if (currentHeading > 180) {
                currentHeading -= 360;
            }

            //PID Stuff
            steeringError = currentHeading - targetHeadingA;
            steeringAdjustment = steeringError * gain;
            rightPower = (speed + steeringAdjustment);
            leftPower = (speed - steeringAdjustment);
            if (rightPower < 0.0) {
                rightPower = 0.0;
            }
            if (leftPower < 0.0) {
                leftPower = 0.0;
            }
            if (rightPower > 1.0) {
                rightPower = 1.0;
            }
            if (leftPower > 1.0) {
                leftPower = 1.0;
            }

            //Setting powers
            right.setPower(-rightPower);
            left.setPower(-leftPower);
            telemetry.addData("1. h", String.format("%03d", currentHeading));

            Thread.sleep(50);
        }

            this.resetStartTime();

            //-135
        while (currentHeading < TargetHeadingB){
            currentHeading = sensorGyro.getHeading();


            right.setPower(-1);
            left.setPower(0);

            Thread.sleep(50);
        }

            this.resetStartTime();

        //forward d
            while (this.getRuntime() < 5){
                currentHeading = sensorGyro.getHeading();
                if (currentHeading > 180) {
                    currentHeading -= 360;
                }

                //PID Stuff
                steeringError = currentHeading - targetHeadingA;
                steeringAdjustment = steeringError * gain;
                rightPower = (speed + steeringAdjustment);
                leftPower = (speed - steeringAdjustment);
                if (rightPower < 0.0) {
                    rightPower = 0.0;
                }
                if (leftPower < 0.0) {
                    leftPower = 0.0;
                }
                if (rightPower > 1.0) {
                    rightPower = 1.0;
                }
                if (leftPower > 1.0) {
                    leftPower = 1.0;
                }

                //Setting powers
                right.setPower(-rightPower);
                left.setPower(-leftPower);
                telemetry.addData("1. h", String.format("%03d", currentHeading));

                Thread.sleep(50);
            }
        }
    }
}

