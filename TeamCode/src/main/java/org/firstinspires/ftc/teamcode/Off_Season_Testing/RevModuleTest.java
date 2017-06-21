package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by User on 6/20/2017.
 */

@TeleOp (name = "RevModuleTest", group = "")
public class RevModuleTest extends LinearOpMode
{

    DcMotor motorL;
    DcMotor motorR;

    Servo servo;

    ColorSensor cSensor;

    double leftMotorPower = 0.0;
    double rightMotorPower = 0.0;

    @Override
    public void runOpMode()
    {
        motorL = hardwareMap.dcMotor.get("motorL");
        motorR = hardwareMap.dcMotor.get("motorR");

        servo = hardwareMap.servo.get("servo");

        cSensor = hardwareMap.colorSensor.get("cSensor");

        cSensor.enableLed(true);

        waitForStart();
        while (opModeIsActive())
        {
            leftMotorPower = -gamepad1.left_stick_y;
            rightMotorPower = gamepad1.right_stick_y;

            if (gamepad1.a)
            {
                servo.setPosition(0.0);
            }

            if (gamepad1.b)
            {
                servo.setPosition(1.0);
            }

            motorL.setPower(leftMotorPower);
            motorR.setPower(rightMotorPower);

            telemetry.addData("Red", cSensor.red());
            telemetry.addData("Green", cSensor.green());
            telemetry.addData("Blue", cSensor.blue());
            telemetry.update();
        }
    }
}
