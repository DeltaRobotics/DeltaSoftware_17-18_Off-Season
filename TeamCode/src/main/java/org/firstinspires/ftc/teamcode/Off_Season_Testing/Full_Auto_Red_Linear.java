package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 4/18/2017.
 */

public class Full_Auto_Red_Linear extends LinearOpMode
{
    PacmanHardware beast = new PacmanHardware();

    public void runOpMode()
    {
        //Initializing the Beast's motors and servos
        beast.init(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        //Getting the Beast's motor encoders ready and set to zero.
        beast.motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        beast.motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        beast.motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        beast.motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Starting at Encoders: %7d : %7d", beast.motorL.getCurrentPosition(), beast.motorR.getCurrentPosition());
        telemetry.update();

        waitForStart();


    }

    public void driveEncoder(double leftPower, double rightPower, int leftEncoder, int rightEncoder)
    {
        int leftEncoderTarget;
        int rightEncoderTarget;

        if(opModeIsActive())
        {
            leftEncoderTarget = beast.motorL.getCurrentPosition() + leftEncoder;
            rightEncoderTarget = beast.motorR.getCurrentPosition() + rightEncoder;
            beast.motorL.setTargetPosition(leftEncoderTarget);
            beast.motorR.setTargetPosition(rightEncoderTarget);

            beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
