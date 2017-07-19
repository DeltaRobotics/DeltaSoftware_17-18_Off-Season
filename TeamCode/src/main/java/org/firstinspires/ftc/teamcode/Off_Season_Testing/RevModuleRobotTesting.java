package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 7/18/2017.
 */
@TeleOp (name =  "revModuleRobotTesting", group = "")
public class RevModuleRobotTesting extends OpMode
{
    DcMotor motorRF;
    DcMotor motorR;
    DcMotor motorL;
    DcMotor motorLF;

    public void init()
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorR = hardwareMap.dcMotor.get("motorR");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorL = hardwareMap.dcMotor.get("motorL");

        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop()
    {
        motorRF.setPower(-gamepad1.right_stick_y);
        motorR.setPower(-gamepad1.right_stick_y);
        motorLF.setPower(gamepad1.left_stick_y);
        motorL.setPower(gamepad1.left_stick_y);


    }


}
