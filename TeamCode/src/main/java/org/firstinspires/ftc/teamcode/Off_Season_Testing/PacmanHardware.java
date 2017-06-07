package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by User on 4/18/2017.
 */

public class PacmanHardware
{
    public DcMotor motorL = null;
    public DcMotor motorR = null;
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;

    public ColorSensor leftColor = null;
    public ColorSensor rightColor = null;

    public DcMotor launcher = null;

    public Servo popper = null;

    public DcMotor collector = null;

    public DcMotor lift = null;

    public DcMotor LEDRed = null;
    public DcMotor LEDGreen = null;
    public DcMotor LEDBlue = null;

    HardwareMap hwMap = null;

    PacmanHardware()
    {

    }

    public void init(HardwareMap hMap)
    {
        hwMap = hMap;

        motorL = hwMap.dcMotor.get("motorL");
        motorR = hwMap.dcMotor.get("motorR");
        motorLF = hwMap.dcMotor.get("motorLF");
        motorRF = hwMap.dcMotor.get("motorRF");

        launcher = hwMap.dcMotor.get("launcherWheel");

        collector = hwMap.dcMotor.get("collector");

        popper = hwMap.servo.get("popper");

        lift = hwMap.dcMotor.get("motorLift");

        LEDRed = hwMap.dcMotor.get("LEDRed");
        LEDGreen = hwMap.dcMotor.get("LEDGreen");
        LEDBlue = hwMap.dcMotor.get("LEDBlue");

        leftColor = hMap.colorSensor.get("leftColor");
        rightColor = hMap.colorSensor.get("rightColor");

        motorL.setPower(0.0);
        motorR.setPower(0.0);
        motorLF.setPower(0.0);
        motorRF.setPower(0.0);

        launcher.setPower(0.0);

        popper.setPosition(0.94);

        LEDRed.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LEDGreen.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LEDBlue.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        LEDRed.setPower(0.01);
        LEDGreen.setPower(0.01);
        LEDBlue.setPower(0.01);

        leftColor.enableLed(true);
        rightColor.enableLed(true);
    }
}
