package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by User on 4/18/2017.
 */
@Autonomous (name = "BasicAutoLinear", group = "")
public class BasicAutoLinear extends LinearOpMode {
    //Declaring an object of the robots hardware
    PacmanHardware beast = new PacmanHardware();

    //Declaring an object of the programs elapsed time
    private ElapsedTime runtime = new ElapsedTime();

    enum turnStyle {PIVOT_LEFT_FORWARD, PIVOT_LEFT_BACKWARD, PIVOT_RIGHT_FORWARD, PIVOT_RIGHT_BACKWARD, PERCENT_LEFT_FORWARD, PERCENT_LEFT_BACKWARD, PERCENT_RIGHT_FORWARD, PERCENT_RIGHT_BACKWARD}
    enum turnImplement {A, B, C, D, E, F}
    turnImplement tImp;

    @Override
    public void runOpMode() {
        //Initializing the Beast's motors and servos
        beast.init(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        //Getting the Beast's motor encoders ready and set to zero.
        beast.motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        beast.motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        //Allowing motors to run to a certain position
        beast.motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        beast.motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Displaying where the encoders are when they initialize
        telemetry.addData("Status", "Starting at Encoders: %7d : %7d", beast.motorL.getCurrentPosition(), beast.motorR.getCurrentPosition());
        telemetry.update();

        //Waits till the user presses the start button on the Driver Station
        waitForStart();


        if(stopAtColor(.70, false, beast.leftColor, 10000, beast.motorL, 16, 16, 19, true))
        {
            telemetry.addData("Result", "Complete");
            telemetry.update();
            sleep(2000);
        }


    }

    public void driveEncoder(double leftPower, double rightPower, int encoder, boolean direction) {
        //Initializing variables that will store the left and right encoder target positions
        int leftEncoderTarget;
        int rightEncoderTarget;

        //Verifying that the opMode is still running
        if (opModeIsActive()) {
            //Calculating the target positions for each motor encoder and setting their target positions to those variables
            if (direction) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs(encoder);
            } else {
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs(encoder);
            }
            beast.motorL.setTargetPosition(leftEncoderTarget);
            beast.motorR.setTargetPosition(rightEncoderTarget);

            //Allowing motors to run to a certain position
            beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Resets runtime
            runtime.reset();

            //Setting the motors to the power specified in the arguments above
            beast.motorL.setPower(leftPower);
            beast.motorR.setPower(rightPower);

            if (direction) {
                beast.motorLF.setPower(leftPower);
                beast.motorRF.setPower(-rightPower);
            } else {
                beast.motorLF.setPower(-leftPower);
                beast.motorRF.setPower(rightPower);
            }

            //Verifies that the motors are running and that the opMode is still running
            while (opModeIsActive() && beast.motorL.isBusy() && beast.motorR.isBusy()) {
                //Displays target positions of both motor encoders and their current positions
                //First value is left motor and second value is right motor
                telemetry.addData("Target Positions", "Running to %7d : %7d", leftEncoderTarget, rightEncoderTarget);
                telemetry.addData("Encoder Positoins", "Running to %7d : %7d", beast.motorL.getCurrentPosition(), beast.motorR.getCurrentPosition());
                telemetry.update();
            }

            //Stops the motors
            beast.motorL.setPower(0.0);
            beast.motorLF.setPower(0.0);
            beast.motorR.setPower(0.0);
            beast.motorRF.setPower(0.0);

            //Stops encoders from running
            beast.motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            beast.motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //sleep(250);


        }
    }

    public void encoderTurn(double turnPower, int encoder, turnStyle turn, double turnPercent, boolean telemetryOn) {
        int leftEncoderTarget = 0;
        int rightEncoderTarget = 0;
        if (opModeIsActive()) {
            if ((turn == turnStyle.PIVOT_LEFT_FORWARD) || (turn == turnStyle.PIVOT_RIGHT_BACKWARD))
            {
                tImp = turnImplement.A;
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs(encoder);
            }
            else if ((turn == turnStyle.PIVOT_RIGHT_FORWARD) || (turn == turnStyle.PIVOT_LEFT_BACKWARD))
            {
                tImp = turnImplement.B;
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs(encoder);
            }
            else if (turn == turnStyle.PERCENT_LEFT_FORWARD)
            {
                tImp = turnImplement.C;
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs((int) (encoder * turnPercent));
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs(encoder);
            }
            else if (turn == turnStyle.PERCENT_LEFT_BACKWARD)
            {
                tImp = turnImplement.D;
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs((int) (encoder * turnPercent));
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs(encoder);
            }
            else if (turn == turnStyle.PERCENT_RIGHT_FORWARD)
            {
                tImp = turnImplement.E;
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs((int) (encoder * turnPercent));
            }
            else if (turn == turnStyle.PERCENT_RIGHT_BACKWARD)
            {
                tImp = turnImplement.F;
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs((int) (encoder * turnPercent));
            }

            beast.motorL.setTargetPosition(leftEncoderTarget);
            beast.motorR.setTargetPosition(rightEncoderTarget);

            beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();

            if(tImp == turnImplement.A)
            {
                beast.motorR.setPower(-turnPower);
                beast.motorRF.setPower(-turnPower);
                beast.motorL.setPower(-turnPower);
                beast.motorLF.setPower(-turnPower);
            }







            if ((turn == turnStyle.PIVOT_LEFT_FORWARD) || (turn == turnStyle.PIVOT_RIGHT_BACKWARD)) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs(encoder);

                beast.motorL.setTargetPosition(leftEncoderTarget);
                beast.motorR.setTargetPosition(rightEncoderTarget);

                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorR.setPower(-turnPower);
                beast.motorRF.setPower(-turnPower);
                beast.motorL.setPower(-turnPower);
                beast.motorLF.setPower(-turnPower);
            }

            if ((turn == turnStyle.PIVOT_RIGHT_FORWARD) || (turn == turnStyle.PIVOT_LEFT_BACKWARD)) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs(encoder);

                beast.motorL.setTargetPosition(leftEncoderTarget);
                beast.motorR.setTargetPosition(rightEncoderTarget);

                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorR.setPower(turnPower);
                beast.motorRF.setPower(turnPower);
                beast.motorL.setPower(turnPower);
                beast.motorLF.setPower(turnPower);
            }

            if (turn == turnStyle.PERCENT_LEFT_FORWARD) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs((int) (encoder * turnPercent));
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs(encoder);

                beast.motorR.setTargetPosition(rightEncoderTarget);
                beast.motorL.setTargetPosition(leftEncoderTarget);

                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorR.setPower(-turnPower);
                beast.motorRF.setPower(-turnPower);
                beast.motorL.setPower(turnPower * turnPercent);
                beast.motorLF.setPower(turnPower * turnPercent);
            }

            if (turn == turnStyle.PERCENT_LEFT_BACKWARD) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs((int) (encoder * turnPercent));
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs(encoder);

                beast.motorR.setTargetPosition(rightEncoderTarget);
                beast.motorL.setTargetPosition(leftEncoderTarget);

                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorR.setPower(turnPower);
                beast.motorRF.setPower(turnPower);
                beast.motorL.setPower(-turnPower * turnPercent);
                beast.motorLF.setPower(-turnPower * turnPercent);
            }

            if (turn == turnStyle.PERCENT_RIGHT_FORWARD) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() + Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() - Math.abs((int) (encoder * turnPercent));

                beast.motorL.setTargetPosition(leftEncoderTarget);
                beast.motorR.setTargetPosition(rightEncoderTarget);

                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorL.setPower(turnPower);
                beast.motorLF.setPower(turnPower);
                beast.motorR.setPower(-turnPower * turnPercent);
                beast.motorRF.setPower(-turnPower * turnPercent);
            }

            if (turn == turnStyle.PERCENT_RIGHT_BACKWARD) {
                leftEncoderTarget = beast.motorL.getCurrentPosition() - Math.abs(encoder);
                rightEncoderTarget = beast.motorR.getCurrentPosition() + Math.abs((int) (encoder * turnPercent));

                beast.motorL.setTargetPosition(leftEncoderTarget);
                beast.motorR.setTargetPosition(rightEncoderTarget);

                beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();

                beast.motorL.setPower(-turnPower);
                beast.motorLF.setPower(-turnPower);
                beast.motorR.setPower(turnPower * turnPercent);
                beast.motorRF.setPower(turnPower * turnPercent);
            }

            while (opModeIsActive() && beast.motorL.isBusy() && beast.motorR.isBusy()) {
                telemetry.addData("Target Positions", "Running to %7d : %7d", leftEncoderTarget, rightEncoderTarget);
                telemetry.addData("Encoder Positoins", "Running to %7d : %7d", beast.motorL.getCurrentPosition(), beast.motorR.getCurrentPosition());
                telemetry.addData("motorL Power", beast.motorL.getPower());
                telemetry.addData("motorR Power", beast.motorR.getPower());
                telemetry.update();
            }

            //Stops the motors
            beast.motorL.setPower(0.0);
            beast.motorLF.setPower(0.0);
            beast.motorR.setPower(0.0);
            beast.motorRF.setPower(0.0);

            //Stops encoders from running
            beast.motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            beast.motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    //Runs motors until robot runs over a color on the field (color specified by the "red", "green", and "blue" variables)
    public boolean stopAtColor(double motorPower, boolean direction, ColorSensor cSensor, int encTimeout, DcMotor encMotor, int red, int green, int blue, boolean telemetryOn)
    {
        //Determines which way the motors should go based on the "direction" argument
        if (direction)
        {
            beast.motorL.setPower(motorPower);
            beast.motorLF.setPower(motorPower);
            beast.motorR.setPower(-motorPower);
            beast.motorRF.setPower(-motorPower);
        }
        else
        {
            beast.motorL.setPower(-motorPower);
            beast.motorLF.setPower(-motorPower);
            beast.motorR.setPower(motorPower);
            beast.motorRF.setPower(motorPower);
        }

        //Checks to see if the robot has run over the color or has reached the timeout encoder value
        while(!compareColor(cSensor, red, green, blue, 5) && (Math.abs(encMotor.getCurrentPosition())) < encTimeout)
        {
            //Determines to show telemetry or not based on the "telemetryOn" argument
            if(telemetryOn)
            {
                //Sends back to the user the red, green, and blue values of the "cSensor" color sensor
                telemetry.addData("Red", cSensor.red());
                telemetry.addData("Green", cSensor.green());
                telemetry.addData("Blue", cSensor.blue());

                //Sends back to the user the current encoder value of the "encTimeoutMotor" motor
                telemetry.addData("Encoder", encMotor.getCurrentPosition());
                telemetry.update();
            }
        }

        //Stops all drivetrain motors
        beast.motorL.setPower(0.0);
        beast.motorLF.setPower(0.0);
        beast.motorR.setPower(0.0);
        beast.motorRF.setPower(0.0);

        //Checks to see if the robot stopped due to the encoder timeout
        if (encMotor.getCurrentPosition() > encTimeout)
        {
            return false;
        }

        //Checks to see if the robot stopped because it ran over the specified color
        if(compareColor(cSensor, red, green, blue, 5))
        {
            return true;
        }

        // Makes code recognize that the program will indeed return a value
        else
        {
            telemetry.addData("Something's Wrong", "Neither if statement was true");
            return false;
        }

    }

    //Checks to see if the "cSensor" color sensor's red, green, and blue values match values specified by the arguments below
    public boolean compareColor(ColorSensor cSensor, int red, int green, int blue, int range)
    {
        if((cSensor.red() < red + range && cSensor.red() > red - range)  &&
           (cSensor.green() < green + range && cSensor.green() > green - range) &&
           (cSensor.blue() < blue + range && cSensor.blue() > blue - range))
        {
             return true;
        }
        else
        {
            return false;
        }

    }
}