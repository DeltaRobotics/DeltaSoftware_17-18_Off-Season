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

    boolean dpadUpState = false;
    boolean dpadDownState = false;

    @Override
    public void runOpMode()
    {
        beast.init(hardwareMap);

        waitForStart();

        while(opModeIsActive())
        {
            leftPower = -gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            if(gamepad2.a)
            {
                launcherPower = 0.5;
            }

            if(gamepad2.b)
            {
                launcherPower = 0.0;
            }

            if(gamepad2.dpad_up && !dpadUpState)
            {
                dpadUpState = true;
                launcherPower += 0.005;
            }
            else if(!gamepad2.dpad_up)
            {
                dpadUpState = false;
            }

            if(gamepad2.dpad_down && !dpadDownState)
            {
                dpadDownState = true;
                launcherPower -= 0.005;
            }
            else if(!gamepad2.dpad_down)
            {
                dpadDownState = false;
            }

            if(gamepad2.right_trigger > .8)
            {
                popperPosition = popperUp;
            }
            else
            {
                popperPosition = popperDown;
            }

            beast.motorL.setPower(leftPower);
            beast.motorLF.setPower(leftPower);
            beast.motorR.setPower(rightPower);
            beast.motorRF.setPower(rightPower);
            beast.launcher.setPower(-launcherPower);
            beast.popper.setPosition(popperPosition);

            telemetry.addData("Launcher Power", launcherPower);
            telemetry.update();
        }
    }
}
