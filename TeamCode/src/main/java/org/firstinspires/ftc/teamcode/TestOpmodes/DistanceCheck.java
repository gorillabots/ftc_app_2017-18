package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Jarred on 11/19/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="distanceCheck", group="Backup")
public class DistanceCheck extends LinearOpMode {
    ArbitraryDirectionDrive driveTrain;
    ArmExtender armExtender;
    //Grabber grabber;
    private LinearOpMode opMode;

    //Servo claw;
    //Servo spin;

    Drive drive;


    public void init_() {

        //driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        drive = new Drive(this);
        // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new GrabberAndrew(hardwareMap, telemetry);




    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        drive.encoderMoveMRGyro2(90,2,.5,.5);
        sleep(2000);
        drive.encoderMoveMRGyro2(270,2,.5,.5);

        }
    }

