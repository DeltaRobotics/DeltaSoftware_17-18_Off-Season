package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import OpModeDrive.DR_Drive;
/**
 * Created by User on 4/8/2017.
 */

@TeleOp(name = "FourMotorTesting", group = "")
public class FourMotorTesting extends LinearOpMode
{

    double leftPower = 0.0;
    double rightPower = 0.0;
    FourMotorJustDriveHardware robot = new FourMotorJustDriveHardware();

    public void runOpMode()
    {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
            leftPower = gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            robot.motorL.setPower(-leftPower);
            robot.motorLF.setPower(leftPower);
            robot.motorR.setPower(rightPower);
            robot.motorRF.setPower(-rightPower);
        }
    }
}
