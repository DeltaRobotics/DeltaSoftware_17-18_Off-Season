package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by User on 5/20/2017.
 */

@TeleOp (name = "TeleLearning", group = "")
public class TeleOpLinearLearning extends LinearOpMode
{
    // Object for the robot's hardware
    PacmanHardware beast = new PacmanHardware();

    // Variable that stores the motors powers
    double leftPower;
    double rightPower;

    // Variable that stores the launchers powers
    double launcherPower = 0.0;

    // Variable that stores the poppers powers
    double popperUp = 0.69;
    double popperDown = 0.94;

    // Variable that stores the collectors powers
    double collectorPower = 0.0;

    // Runs OpMode
    @Override
    public void runOpMode()
    {
        // Initialization of the robot's hardware
        beast.init(hardwareMap);

        // Wait for the driver to press the start button on the drivers station
        waitForStart();

        // Check to see that the OpMode is running
        while(opModeIsActive())
        {
            // Sets the wheel motor power variables to the y values of the left and right joysticks
            leftPower = -gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            // If the user presses the 'b' button, the launcher stops
            if(gamepad1.b)
            {
                launcherPower = 0.0;
            }

            // If the user presses the 'a' button, the launcher starts at power 7.0
            if(gamepad1.a)
            {
                launcherPower = 7.0;
            }

            // If the user presses the right trigger down, the power will go up
            if(gamepad1.right_trigger > 0.8)
            {
                beast.popper.setPosition(popperUp);
            }

            // If the user is not pressing the right trigger down, the popper goes down
            else
            {
                beast.popper.setPosition(popperDown);
            }

            // If the user presses the left bumper, the collector gets set to a power of 0.3
            if(gamepad1.left_bumper)
            {
                collectorPower = 0.3;
            }

            // If the user presses the left bumper, the collector stops
            if(gamepad1.right_bumper)
            {
               collectorPower = 0.0;
            }

            //  Allows motor to set the collective powers
            beast.motorL.setPower(leftPower);
            beast.motorLF.setPower(leftPower);
            beast.motorR.setPower(rightPower);
            beast.motorRF.setPower(rightPower);
            beast.launcher.setPower(-launcherPower);
            beast.collector.setPower(-collectorPower);
            beast.lift.setPower(-collectorPower);

            // Reads and sends data to the drivers station
            telemetry.addData("Launcher Power", launcherPower);
            telemetry.update();

        }
    }

}
