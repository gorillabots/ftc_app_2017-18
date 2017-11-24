package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.Drive;
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

    Servo claw;
    Servo spin;

    Drive drive;


    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        drive = new Drive(this);
        // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new Grabber1(hardwareMap, telemetry);




    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();

            while(driveTrain.distanceCheck(1)){
                driveTrain.drivePolar(.5,90);
            }

            driveTrain.stopMotors();
            telemetry.addData("second step","");

            telemetry.update();


            drive.encoderMoveMRGyro(180,1,.5);
            /*
            driveTrain.stopMotors();
            while(driveTrain.distanceCheck(1)){
                driveTrain.drivePolar(.5,90);
            }
            driveTrain.stopMotors();
            while(driveTrain.distanceCheck(1)){
                driveTrain.drivePolar(.5,270);
            }
            driveTrain.stopMotors();
            */

            //driveTrain.drive(Math.sqrt(gamepad1.left_stick_x*gamepad1.left_stick_x + gamepad1.left_stick_y * gamepad1.left_stick_y), Math.atan2(gamepad1.left_stick_x,gamepad1.left_stick_y));

                /*
                //Graber
                if (gamepad2.right_bumper)
                    grabber.open();
                }
                else if (gamepad2.left_bumper) {
                    grabber.close();}

                if (gamepad2.right_trigger > .9) {
                    grabber.rotate(+.25);
                } else if (gamepad2.left_trigger > .9) {
                    grabber.rotate(-.25);
                } else {
                    grabber.rotate(0);
                }

                */

        }
    }

