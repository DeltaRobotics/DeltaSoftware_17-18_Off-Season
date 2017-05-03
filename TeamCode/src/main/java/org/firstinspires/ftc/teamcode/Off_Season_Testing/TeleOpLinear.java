package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by User on 4/29/2017.
 */

@TeleOp (name = "TeleLinear", group = "")
public class TeleOpLinear extends LinearOpMode
{
    PacmanHardware beast = new PacmanHardware();

    ElapsedTime runtime = new ElapsedTime();

    double leftPower = 0.0;
    double rightPower = 0.0;

    double launcherPower = 0.0;
    double popperUp = 0.69;
    double popperDown = 0.94;
    double popperPosition = 0.94;

    double collectorPower = 0.0;

    double liftPower = 0.0;

    boolean dpadUpState = false;
    boolean dpadDownState = false;

    boolean leftBumperState = false;
    boolean rightBumperState = false;

    boolean leftTriggerState = false;

    boolean collector = false;

    boolean collectorDirection = false;

    boolean launcher = false;

    @Override
    public void runOpMode()
    {
        beast.init(hardwareMap);

        waitForStart();

        while(opModeIsActive())
        {
            leftPower = -gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            //if(gamepad1.left_trigger > 0.8)

            if(gamepad1.dpad_up && !dpadUpState)
            {
                dpadUpState = true;
                launcherPower += 0.005;
            }
            else if(!gamepad1.dpad_up)
            {
                dpadUpState = false;
            }

            if(gamepad1.dpad_down && !dpadDownState)
            {
                dpadDownState = true;
                launcherPower -= 0.005;
            }
            else if(!gamepad1.dpad_down)
            {
                dpadDownState = false;
            }

            if(gamepad1.right_trigger > .8)
            {
                popperPosition = popperUp;
            }
            else
            {
                popperPosition = popperDown;
            }

            if(gamepad1.left_bumper && !leftBumperState)
            {
                leftBumperState = true;
                collector = !collector;
            }
            else if(!gamepad1.left_bumper)
            {
                leftBumperState = false;
            }

            if (collector)
            {
                beast.collector.setPower(collectorPower);
                beast.lift.setPower(liftPower);
            }
            else
            {
                collectorPower = 0.0;
                liftPower = 0.0;
                beast.collector.setPower(collectorPower);
                beast.lift.setPower(liftPower);
            }

            if(gamepad1.right_bumper && !rightBumperState)
            {
                rightBumperState = true;
                collectorDirection = !collectorDirection;
            }
            else if(!gamepad1.right_bumper)
            {
                rightBumperState = false;
            }

            if(collectorDirection && collector)
            {
                collectorPower = 0.3;
                liftPower = 0.3;
            }
            else if(!collectorDirection && collector)
            {
                collectorPower = -0.3;
                liftPower = -0.3;
            }



            beast.motorL.setPower(leftPower);
            beast.motorLF.setPower(leftPower);
            beast.motorR.setPower(rightPower);
            beast.motorRF.setPower(rightPower);
            beast.launcher.setPower(-launcherPower);
            beast.popper.setPosition(popperPosition);

            telemetry.addData("Launcher Power", launcherPower);
            telemetry.addData("Colletor Power", collectorPower);
            telemetry.update();
        }
    }
}
