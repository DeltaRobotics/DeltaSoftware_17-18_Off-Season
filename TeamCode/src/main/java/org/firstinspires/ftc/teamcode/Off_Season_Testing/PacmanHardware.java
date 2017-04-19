package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by User on 4/18/2017.
 */

public class PacmanHardware
{
    public DcMotor motorL = null;
    public DcMotor motorR = null;
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    HardwareMap hwMap = null;

    PacmanHardware()
    {

    }

    public void init(HardwareMap hMap)
    {
        hMap = hwMap;

        motorL = hMap.dcMotor.get("motorL");
        motorR = hMap.dcMotor.get("motorR");
        motorLF = hMap.dcMotor.get("motorLF");
        motorRF = hMap.dcMotor.get("motorRF");

        motorL.setPower(0.0);
        motorR.setPower(0.0);
        motorLF.setPower(0.0);
        motorRF.setPower(0.0);
    }
}
