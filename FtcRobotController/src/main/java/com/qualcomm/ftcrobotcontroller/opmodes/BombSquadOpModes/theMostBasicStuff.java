package com.qualcomm.ftcrobotcontroller.opmodes.BombSquadOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
/**
 * Created by bridgetj18 on 12/21/15.
 * literally just drives forward
 */
public class theMostBasicStuff extends OpMode{
    DcMotor right;
    DcMotor left;

    @Override
    public void init(){
        right = hardwareMap.dcMotor.get("m1");
        left = hardwareMap.dcMotor.get("m2");
        right.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){
        left.setPower(1);
        right.setPower(1);
    }
}
