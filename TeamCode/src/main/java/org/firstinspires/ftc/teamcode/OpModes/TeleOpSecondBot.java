package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.ArmExtender2;
import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Constants;
//import org.firstinspires.ftc.teamcode.Components.TestArmExtender;
import org.firstinspires.ftc.teamcode.Components.Grabber2;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="driveSecond", group="Backup")
public class TeleOpSecondBot extends LinearOpMode{

    ArbitraryDirectionDrive driveTrain;
    ArmExtender2 armExtender;
    Grabber2 grabber;
    private LinearOpMode opMode;


    ModernRoboticsI2cColorSensor ballColor;
    DcMotor extend;
    DcMotor rotateOne;
    DcMotor rotateTwo;

    double oneOpen = Constants.leftOpen;
    double oneClose = Constants.leftClose;
    double twoOpen = Constants.rightOpen;
    double twoClose = Constants.rightClose;

    Servo clawOne;
    Servo clawTwo;
    ColorSensor lineSensor;


    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        armExtender = new ArmExtender2(hardwareMap, telemetry);
        grabber = new Grabber2(hardwareMap, telemetry);
        extend = hardwareMap.dcMotor.get("extend");
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");



        clawOne = hardwareMap.servo.get("clawOne");
        clawTwo = hardwareMap.servo.get("clawTwo");

        clawOne.setPosition(oneOpen);
        clawTwo.setPosition(twoOpen);

        lineSensor = hardwareMap.colorSensor.get("lineSensor");
        lineSensor.enableLed(true);



    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        while(opModeIsActive()) {


            driveTrain.drive(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

            if(gamepad2.left_bumper){
                grabber.close1();
            }
            else if(gamepad2.left_trigger >= .5){
                grabber.open1();
            }


            ColorHelper.printColorHSV(this.telemetry,lineSensor);
            telemetry.update();





            if(gamepad2.right_bumper){
                grabber.close2();
            }
            else if(gamepad2.right_trigger >= .5){
                grabber.open2();
            }


            if(gamepad2.dpad_up){
                armExtender.extend(1);
            }
            else if(gamepad2.dpad_down){
                armExtender.extend(-1);
            }
            else{
                armExtender.stop();
            }

            grabber.rotateOne(gamepad2.left_stick_y);
            grabber.rotateTwo(gamepad2.right_stick_y);


                /*
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
}
