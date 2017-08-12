package org.firstinspires.ftc.teamcode.Off_Season_Testing;


import android.support.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU.SensorMode;
import com.qualcomm.hardware.bosch.BNO055IMU.SystemStatus;
import com.qualcomm.robotcore.hardware.I2cDevice;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.MagneticFlux;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.TempUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Temperature;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


/**
 * Created by User on 7/29/2017.
 */

@TeleOp(name = "RevGyroTest", group = "Testing")
public class Testing_Gyro_Sensor extends LinearOpMode
{

   BNO055IMU thing = new BNO055IMU()

   {
      @Override
      public boolean initialize(@NonNull Parameters parameters)
      {
         return false;
      }

      @NonNull
      @Override
      public Parameters getParameters()
      {
         return null;
      }

      @Override
      public void close()
      {

      }

      @Override
      public Orientation getAngularOrientation()
      {
         return null;
      }

      @Override
      public Orientation getAngularOrientation(AxesReference reference, AxesOrder order, org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit)
      {
         return null;
      }

      @Override
      public Acceleration getOverallAcceleration()
      {
         return null;
      }

      @Override
      public AngularVelocity getAngularVelocity()
      {
         return null;
      }

      @Override
      public Acceleration getLinearAcceleration()
      {
         return null;
      }

      @Override
      public Acceleration getGravity()
      {
         return null;
      }

      @Override
      public Temperature getTemperature()
      {
         return this.getTemperature();
      }

      @Override
      public MagneticFlux getMagneticFieldStrength()
      {
         return null;
      }

      @Override
      public Quaternion getQuaternionOrientation()
      {
         return null;
      }

      @Override
      public Position getPosition()
      {
         return null;
      }

      @Override
      public Velocity getVelocity()
      {
         return null;
      }

      @Override
      public Acceleration getAcceleration()
      {
         return null;
      }

      @Override
      public void startAccelerationIntegration(Position initialPosition, Velocity initialVelocity, int msPollInterval)
      {

      }

      @Override
      public void stopAccelerationIntegration()
      {

      }

      @Override
      public SystemStatus getSystemStatus()
      {
         return null;
      }

      @Override
      public SystemError getSystemError()
      {
         return null;
      }

      @Override
      public CalibrationStatus getCalibrationStatus()
      {
         return null;
      }

      @Override
      public boolean isSystemCalibrated()
      {
         return false;
      }

      @Override
      public boolean isGyroCalibrated()
      {
         return false;
      }

      @Override
      public boolean isAccelerometerCalibrated()
      {
         return false;
      }

      @Override
      public boolean isMagnetometerCalibrated()
      {
         return false;
      }

      @Override
      public CalibrationData readCalibrationData()
      {
         return null;
      }

      @Override
      public void writeCalibrationData(CalibrationData data)
      {

      }

      @Override
      public byte read8(Register register)
      {
         return 0;
      }

      @Override
      public byte[] read(Register register, int cb)
      {
         return new byte[0];
      }

      @Override
      public void write8(Register register, int bVal)
      {

      }

      @Override
      public void write(Register register, byte[] data)
      {

      }

   };

   public void runOpMode()
   {

      waitForStart();

      while(opModeIsActive())
      {
         thing.getTemperature();
         telemetry.addData("Temperature?", thing.getTemperature().toUnit(TempUnit.CELSIUS));
         telemetry.update();
      }

   }
}


