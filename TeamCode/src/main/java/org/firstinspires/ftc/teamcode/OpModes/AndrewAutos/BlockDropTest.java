package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Drive.Drive;

/**
 * Created by Owner on 12/15/2017.
 */
@Autonomous(name="BlockDropTest")
public class BlockDropTest extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88
    final double DIST_TOUCH = 30;
    final double DIST_COLUMN = 20;


    Drive drive;
    Servo arm;
    ColorSensor armColor;
    ModernRoboticsI2cRangeSensor range;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drive(this.hardwareMap, this.telemetry);
        arm = hardwareMap.servo.get("arm");
        armColor = hardwareMap.colorSensor.get("ballColor");
        armColor.enableLed(false);
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        range.getDistance(DistanceUnit.CM);
        while (range.getDistance(DistanceUnit.CM) >= DIST_TOUCH){
            drive.encoderMoveMRGyro(270,.3,.2); //straight foward
        }
        wait(90);
        drive.encoderMoveMRGyro(180,.5,.2);
        while (range.getDistance(DistanceUnit.CM) >= DIST_COLUMN){
            drive.encoderMoveMRGyro(0,.05,.2);
            telemetry.addData("distance", range.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        telemetry.addData("Status", "1st time");
        telemetry.update();
        wait(90);
        while (range.getDistance(DistanceUnit.CM) >= DIST_COLUMN){
            drive.encoderMoveMRGyro(0,.05,.2);
            telemetry.addData("distance", range.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        telemetry.addData("Status", "2nd time");
        telemetry.update();
        wait(90);
        while (range.getDistance(DistanceUnit.CM) >= DIST_COLUMN){
            drive.encoderMoveMRGyro(0,.05,.2);
            telemetry.addData("distance", range.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        telemetry.addData("Status", "3rd time");
        telemetry.update();
        wait(90);
        while (range.getDistance(DistanceUnit.CM) >= DIST_COLUMN){
            drive.encoderMoveMRGyro(0,.05,.2);
            telemetry.addData("distance", range.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        telemetry.addData("Status", "4th time");
        telemetry.update();
        wait(90);



        drive.close();
    }

}
