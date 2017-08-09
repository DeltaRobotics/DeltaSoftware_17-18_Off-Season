package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by User on 4/18/2017.
 */

public class FourMotorJustDriveHardware
{
    public DcMotor motorL = null;
    public DcMotor motorR = null;
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;


    HardwareMap hwMap = null;

    FourMotorJustDriveHardware()
    {

    }

    public void init(HardwareMap hMap)
    {
        hwMap = hMap;

        motorL = hwMap.dcMotor.get("motorL");
        motorR = hwMap.dcMotor.get("motorR");
        motorLF = hwMap.dcMotor.get("motorLF");
        motorRF = hwMap.dcMotor.get("motorRF");

        motorL.setPower(0.0);
        motorR.setPower(0.0);
        motorLF.setPower(0.0);
        motorRF.setPower(0.0);

    }
}
