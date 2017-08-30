package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

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

    ColorSensor revCSensor;

    Servo servo;

    boolean leftDPadState = false;
    boolean  rightDPadState = false;

    double servoPos = 0.5;


    public void init()
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorR = hardwareMap.dcMotor.get("motorR");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorL = hardwareMap.dcMotor.get("motorL");

        revCSensor = hardwareMap.colorSensor.get("revCSensor");

        servo = hardwareMap.servo.get("servo");

        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servo.setPosition(servoPos);
    }

    public void loop()
    {
        motorRF.setPower(-gamepad1.right_stick_y);
        motorR.setPower(-gamepad1.right_stick_y);
        motorLF.setPower(gamepad1.left_stick_y);
        motorL.setPower(gamepad1.left_stick_y);

        /*
        if(gamepad1.a)
        {
            servo.setPosition(0.0);
        }
        else if(gamepad1.b)
        {
            servo.setPosition(1.0);
        }
        */

        if (gamepad1.dpad_left && !leftDPadState)
        {
            leftDPadState = true;
            servoPos += 0.05;
        }
        else if (!gamepad1.dpad_left)
        {
            leftDPadState = false;
        }

        if (gamepad1.dpad_right && !rightDPadState)
        {
            rightDPadState = true;
            servoPos -= 0.05;
        }
        else if (!gamepad1.dpad_right)
        {
            rightDPadState = false;
        }

        if (servoPos > 1.0)
        {
            servoPos = 1.0;
        }
        else if (servoPos < 0.0)
        {
            servoPos = 0.0;
        }

        servo.setPosition(servoPos);

        telemetry.addData("Red", revCSensor.red());
        telemetry.addData("Green", revCSensor.green());
        telemetry.addData("Blue", revCSensor.blue());

        telemetry.addData("argb", revCSensor.argb());

        telemetry.addData("Servo Position", servo.getPosition());

        telemetry.update();

    }


}
