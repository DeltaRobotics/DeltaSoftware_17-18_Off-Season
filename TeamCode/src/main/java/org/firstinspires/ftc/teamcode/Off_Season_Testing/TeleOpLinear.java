package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by User on 4/29/2017.
 */

@TeleOp (name = "TeleLinear", group = "")
public class TeleOpLinear extends LinearOpMode
{
    // Object of the robot's hardware
    PacmanHardware beast = new PacmanHardware();

    // Keeps the elapsed time
    ElapsedTime runtime = new ElapsedTime();

    // Powers for the left and right wheels
    double leftPower = 0.0;
    double rightPower = 0.0;

    // Power for the launcher
    double launcherPower = 0.0;

    // Positions popper will be set at
    double popperUp = 0.69;
    double popperDown = 0.94;
    double popperPosition = 0.94;

    // Power the collector will run at
    double collectorPower = 0.0;

    // Power the lift will run at
    double liftPower = 0.0;

    // Last known power the launcher had before it stopped
    double lastLauncherPower = 0.7;

    // State variables that get the current state of the d-pad down and up buttons
    boolean dpadUpState = false;
    boolean dpadDownState = false;

    // State variables that get the current state of the left and right bumpers
    boolean leftBumperState = false;
    boolean rightBumperState = false;

    // State of collector
    boolean collector = false;

    // Variable that keeps track of the collectors direction
    boolean collectorDirection = false;

    // State of the launcher
    boolean launcher = false;

    // Variable that determines if the program should display the telemetry relating to the color sensor
    boolean colorTelemetry = false;

    // Value the launcherPower variable increments/decrements by
    double incrementValue = 0.005;

    // False = regular increment
    // True = fast increment
    boolean incrementValueState = false;

    // State for the back button
    boolean backState = false;

    //Led Variables Under Here
    private boolean led_orange = false;
    private boolean led_blue= false;
    private boolean led_green= false;
    private boolean led_rainbow = false;
    private boolean led_white = false;
    private boolean orange_state = false;
    private boolean blue_state = false;
    private boolean green_state = false;
    private boolean white_state = false;
    private boolean rainbow_state = false;
    private double rainbow_red = 0;
    private double rainbow_green = 0;
    private double rainbow_blue = 0;
    boolean rainbowPhaseOne = false;
    boolean rainbowPhaseTwo = false;
    boolean rainbowPhaseThree = false;
    boolean start = true;
    //End LED Variables

    @Override
    // Runs opMode
    public void runOpMode() {
        // Inits the hardware on the robot
        beast.init(hardwareMap);

        //beast.rightColor.setI2cAddress(I2cAddr.create8bit(0x3c));
        beast.rightColor.setI2cAddress(I2cAddr.create8bit(0x2c));

        // Waits for user to press the start button
        waitForStart();

        // Verifys that the opMode is still active
        while (opModeIsActive()) {
            // Sets the leftPower and rightPower variables to the current value of the joysticks y value corresponding to their appropriate sides
            leftPower = -gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            if (gamepad1.dpad_up && !dpadUpState && launcher) {
                dpadUpState = true;
                launcherPower += incrementValue;
                lastLauncherPower = launcherPower;

            } else if (!gamepad1.dpad_up) {
                dpadUpState = false;
            }

            if (gamepad1.dpad_down && !dpadDownState && launcher) {
                dpadDownState = true;
                launcherPower -= incrementValue;
                lastLauncherPower = launcherPower;
            } else if (!gamepad1.dpad_down) {
                dpadDownState = false;
            }

            if (gamepad1.right_trigger > .8) {
                popperPosition = popperUp;
            } else {
                popperPosition = popperDown;
            }

            if (gamepad1.b) {
                launcher = false;
                launcherPower = 0.0;
            }

            if (gamepad1.a) {
                launcher = true;
                launcherPower = lastLauncherPower;
            }

            if (incrementValueState) {
                incrementValue = 0.005;
            } else {
                incrementValue = 0.05;
            }

            if (gamepad1.back && backState == false) {
                backState = true;
                incrementValueState = !incrementValueState;
            } else if (!gamepad1.back) {
                backState = false;
            }

            if (gamepad1.left_bumper && !leftBumperState) {
                leftBumperState = true;
                collector = !collector;
            } else if (!gamepad1.left_bumper) {
                leftBumperState = false;
            }

            if (collector) {
                beast.collector.setPower(collectorPower);
                beast.lift.setPower(liftPower);
            } else {
                collectorPower = 0.0;
                liftPower = 0.0;
                beast.collector.setPower(collectorPower);
                beast.lift.setPower(liftPower);
            }

            if (gamepad1.right_bumper && !rightBumperState) {
                rightBumperState = true;
                collectorDirection = !collectorDirection;
            } else if (!gamepad1.right_bumper) {
                rightBumperState = false;
            }

            if (collectorDirection && collector) {
                collectorPower = 0.3;
                liftPower = 0.3;
            } else if (!collectorDirection && collector) {
                collectorPower = -0.3;
                liftPower = -0.3;
            }

            //LED Stuff Under Here
            if (gamepad2.y && !orange_state) {
                led_orange = true;
                orange_state = true;
                led_blue = false;
                led_green = false;
                led_rainbow = false;
                green_state = false;
                blue_state = false;
                rainbow_state = false;
                white_state = false;
                led_white = false;
                sleep(250);
            } else if (gamepad2.y) {
                orange_state = false;
                led_orange = false;
                sleep(250);
            }

            if (gamepad2.x && !blue_state) {
                led_blue = true;
                blue_state = true;
                led_orange = false;
                orange_state = false;
                led_green = false;
                green_state = false;
                led_rainbow = false;
                rainbow_state = false;
                white_state = false;
                led_white = false;
                sleep(250);
            } else if (gamepad2.x) {
                blue_state = false;
                led_blue = false;
                sleep(250);
            }

            if (gamepad2.a && !green_state) {
                led_green = true;
                green_state = true;
                led_blue = false;
                led_rainbow = false;
                blue_state = false;
                led_orange = false;
                orange_state = false;
                rainbow_state = false;
                white_state = false;
                led_white = false;
                sleep(250);
            } else if (gamepad2.a) {
                green_state = false;
                led_green = false;
                sleep(250);
            }

            if (gamepad2.b && !rainbow_state) {
                led_rainbow = true;
                rainbow_state = true;
                led_green = false;
                green_state = false;
                led_blue = false;
                blue_state = false;
                led_orange = false;
                orange_state = false;
                white_state = false;
                led_white = false;
                sleep(250);
            } else if (gamepad2.b) {
                led_rainbow = false;
                rainbow_state = false;
                start = true;
                sleep(250);
            }

            if (gamepad2.right_stick_button && !white_state) {
                led_orange = false;
                orange_state = false;
                led_blue = false;
                led_green = false;
                led_rainbow = false;
                green_state = false;
                blue_state = false;
                rainbow_state = false;
                white_state = true;
                led_white = true;
                sleep(250);
            } else if (gamepad2.right_stick_button) {
                white_state = false;
                led_white = false;
                sleep(250);
            }


            if (led_orange) {

                beast.LEDRed.setPower(1.0);
                beast.LEDGreen.setPower(0.03);
                beast.LEDBlue.setPower(0.0);

                telemetry.addData("LED", "orange");
                telemetry.update();
            } else if (led_blue) {
                beast.LEDRed.setPower(.01);
                beast.LEDGreen.setPower(0.0);
                beast.LEDBlue.setPower(1.0);

                telemetry.addData("LED", "blue");
                telemetry.update();
            } else if (led_green) {
                beast.LEDRed.setPower(.01);
                beast.LEDGreen.setPower(1.0);
                beast.LEDBlue.setPower(0.0);

                telemetry.addData("LED", "green");
                telemetry.update();
            } else if (led_white) {
                beast.LEDRed.setPower(1.0);
                beast.LEDGreen.setPower(1.0);
                beast.LEDBlue.setPower(1.0);

                telemetry.addData("LED", "white");
                telemetry.update();
            } else if (led_rainbow) {
                sleep(100);
                Range.clip(rainbow_red, 0.02, 1.0);
                Range.clip(rainbow_green, 0.02, 1.0);
                Range.clip(rainbow_blue, 0.02, 1.0);

                if (start) {
                    //sleep(2000);
                    rainbow_red = 1.0;
                    rainbow_green = 0.0;
                    rainbow_blue = 0.0;
                    beast.LEDRed.setPower(1.0);
                    beast.LEDGreen.setPower(0.0);
                    beast.LEDBlue.setPower(0.0);
                    start = false;
                    rainbowPhaseOne = true;
                    rainbowPhaseTwo = false;
                    rainbowPhaseThree = false;
                }

                if (rainbowPhaseOne && rainbow_red > 0.01) {
                    rainbow_red -= 0.01;
                    rainbow_green += 0.01;
                } else if (rainbowPhaseOne) {
                    rainbowPhaseOne = false;
                    rainbowPhaseTwo = true;
                }

                if (rainbowPhaseTwo && rainbow_green > 0.01) {
                    rainbow_green -= 0.01;
                    rainbow_blue += 0.01;
                } else if (rainbowPhaseTwo) {
                    rainbowPhaseTwo = false;
                    rainbowPhaseThree = true;
                }

                if (rainbowPhaseThree && rainbow_blue > 0.01) {
                    rainbow_blue -= 0.01;
                    rainbow_red += 0.01;
                } else if (rainbowPhaseThree) {
                    rainbowPhaseThree = false;
                    rainbowPhaseOne = true;
                }

                if (rainbow_red < .01) {
                    rainbow_red = +.01;
                }
                if (rainbow_green < .01) {
                    rainbow_green = +.01;
                }
                if (rainbow_blue < .01) {
                    rainbow_blue = +.01;
                }

                beast.LEDRed.setPower(rainbow_red);
                beast.LEDGreen.setPower(rainbow_green);
                beast.LEDBlue.setPower(rainbow_blue);

                telemetry.addData("LED", "Rainbow");
                telemetry.addData("Rainbow Red", rainbow_red);
                telemetry.addData("Rainbow Green", rainbow_green);
                telemetry.addData("Rainbow Blue", rainbow_blue);
                telemetry.update();
            } else {
                beast.LEDRed.setPower(0.0);
                beast.LEDGreen.setPower(0.0);
                beast.LEDBlue.setPower(0.0);

                telemetry.addData("LED", "Dark");
                telemetry.update();
            }
            //End LED Stuff Here

            Range.clip(launcherPower, 0.005, 1.0);


            beast.motorL.setPower(leftPower);
            beast.motorLF.setPower(leftPower);
            beast.motorR.setPower(rightPower);
            beast.motorRF.setPower(rightPower);
            beast.launcher.setPower(-launcherPower);
            beast.popper.setPosition(popperPosition);

            telemetry.addData("Launcher Power", launcherPower);
            telemetry.addData("Collector Power", collectorPower);
            telemetry.addData("Last Launcher Power", lastLauncherPower);
            telemetry.addData("incrementValueState", incrementValueState);

            if (colorTelemetry) {
                telemetry.addData("Left Red", beast.leftColor.red());
                telemetry.addData("Left Green", beast.leftColor.green());
                telemetry.addData("Left Blue", beast.leftColor.blue());
                telemetry.addData("Right Red", beast.rightColor.red());
                telemetry.addData("Right Green", beast.rightColor.green());
                telemetry.addData("Right Blue", beast.rightColor.blue());
            }

            telemetry.update();
        }
    }
    }


