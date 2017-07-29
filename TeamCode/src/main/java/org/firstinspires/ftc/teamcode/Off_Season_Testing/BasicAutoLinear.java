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


        /*if(stopAtColor(.70, false, beast.leftColor, 10000, beast.motorL, 16, 16, 19, true))
        {
            telemetry.addData("Result", "Complete");
            telemetry.update();
            sleep(2000);
        }*/

        encoderTurn(1.0,800,turnStyle.PIVOT_LEFT_FORWARD,1,true);

        telemetry.addData("Status", "Done with method : encoderTurn");
        telemetry.update();



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

    /*
      **********************************************************************************************
        Method : encoderTurn (public; void)
        Purpose : for easy turning using encoders as well as multiple styles of turning
        Author(s) : Emmy Petersen, Laura Evans, and Nolan Eastburn
        Created on : 7/25/17
      **********************************************************************************************
     */
    public void encoderTurn(double turnPower, int encoder, turnStyle turn, double turnPercent, boolean telemetryOn) {

        //This creates variables for use in the encoderTurn method
        int leftEncoderTarget = 0;
        int rightEncoderTarget = 0;
        double motorLeftSign = 0;
        double motorRightSign = 0;
        double motorLeftFrontSign = 0;
        double motorRightFrontSign = 0;
        double encoderLeftSign = 0;
        double encoderRightSign = 0;

        if (opModeIsActive()) {
            //This is setting specific variables for each individual style of turn
            if ((turn == turnStyle.PIVOT_LEFT_FORWARD) || (turn == turnStyle.PIVOT_RIGHT_BACKWARD))
            {
                encoderLeftSign = -1;
                encoderRightSign = -1;
                motorLeftFrontSign = -1;
                motorLeftSign = -1;
                motorRightFrontSign = -1;
                motorRightSign = -1;
            }
            else if ((turn == turnStyle.PIVOT_RIGHT_FORWARD) || (turn == turnStyle.PIVOT_LEFT_BACKWARD))
            {
                encoderLeftSign = 1;
                encoderRightSign = 1;
                motorRightFrontSign= 1;
                motorLeftFrontSign = 1;
                motorRightSign = 1;
                motorLeftSign = 1;
            }
            else if (turn == turnStyle.PERCENT_LEFT_FORWARD)
            {
                encoderLeftSign = turnPercent;
                encoderRightSign = -1;
                motorRightSign = -1;
                motorLeftSign = turnPercent;
                motorRightFrontSign = -1;
                motorLeftFrontSign = turnPercent;
            }
            else if (turn == turnStyle.PERCENT_LEFT_BACKWARD)
            {
                encoderLeftSign = -turnPercent;
                encoderRightSign = 1;
                motorLeftFrontSign = -turnPercent;
                motorRightFrontSign = 1;
                motorRightSign = 1;
                motorLeftSign = -turnPercent;
            }
            else if (turn == turnStyle.PERCENT_RIGHT_FORWARD)
            {
                encoderLeftSign = 1;
                encoderRightSign = -turnPercent;
                motorLeftFrontSign = 1;
                motorRightFrontSign = -turnPercent;
                motorLeftSign = 1;
                motorRightSign = -turnPercent;
            }
            else if (turn == turnStyle.PERCENT_RIGHT_BACKWARD)
            {
                encoderLeftSign = -1;
                encoderRightSign = turnPercent;
                motorLeftFrontSign =-1;
                motorLeftSign = -1;
                motorRightFrontSign = turnPercent;
                motorRightSign = turnPercent;
            }

            //This takes current position + input value to equal the target value
            leftEncoderTarget = beast.motorL.getCurrentPosition() + ((int) encoderLeftSign * Math.abs(encoder));
            rightEncoderTarget = beast.motorR.getCurrentPosition() + ((int)(encoderRightSign * Math.abs(encoder)));

            //This tells the motors what the encoder target value
            beast.motorL.setTargetPosition(leftEncoderTarget);
            beast.motorR.setTargetPosition(rightEncoderTarget);

            //This prepares the motors to go to a specific encoder position
            beast.motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            beast.motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //This prepares the LinearOpMode to run the motors
            runtime.reset();

            //This tells the motors to run at the power and direction derived from input parameters
            beast.motorR.setPower(turnPower * motorRightSign);
            beast.motorRF.setPower(turnPower * motorRightFrontSign);
            beast.motorL.setPower(turnPower * motorLeftSign);
            beast.motorLF.setPower(turnPower * motorLeftFrontSign);

            while (opModeIsActive() && beast.motorL.isBusy() && beast.motorR.isBusy()) {
                if(telemetryOn)
                {
                    telemetry.addData("Target Positions", "Running to %7d : %7d", leftEncoderTarget, rightEncoderTarget);
                    telemetry.addData("Encoder Positions", "Running to %7d : %7d", beast.motorL.getCurrentPosition(), beast.motorR.getCurrentPosition());
                    telemetry.addData("motorL Power", beast.motorL.getPower());
                    telemetry.addData("motorR Power", beast.motorR.getPower());
                    telemetry.update();
                }
            }

            //Stops the motors
            beast.motorL.setPower(0.0);
            beast.motorLF.setPower(0.0);
            beast.motorR.setPower(0.0);
            beast.motorRF.setPower(0.0);

            //This resets the encoders before future use
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