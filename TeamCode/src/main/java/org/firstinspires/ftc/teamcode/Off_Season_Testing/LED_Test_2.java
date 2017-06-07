package org.firstinspires.ftc.teamcode.Off_Season_Testing;

import android.content.Context;
import android.media.AudioManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by User on 6/6/2017.
 */
@TeleOp (name = "LED_Test_2", group = "")
public class LED_Test_2 extends LinearOpMode {

    PacmanHardware beast = new PacmanHardware();

    private int loop = 100000;

    public void runOpMode() {

        beast.init(hardwareMap);

        AudioManager audioManager = (AudioManager)

        while (loop >= 0)
        {

            beast.LEDRed.setPower(1.0);
            beast.LEDGreen.setPower(0.03);
            beast.LEDBlue.setPower(0.0);

            telemetry.addData("Loop Count", loop);
            telemetry.update();
            loop--;
        }

    }
}
