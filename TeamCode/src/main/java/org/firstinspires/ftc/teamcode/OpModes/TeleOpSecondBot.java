package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.ExtenderAndrew;
import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Constants;
import org.firstinspires.ftc.teamcode.Components.GrabberJack;

/**
 * Created by Owner on 10/6/2017.
 */
@Disabled
@TeleOp(name="driveSecond", group="Backup")
public class TeleOpSecondBot extends LinearOpMode{

    ArbitraryDirectionDrive driveTrain;
    ExtenderAndrew armExtender;
    GrabberJack grabber;
    private LinearOpMode opMode;


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

    ColorSensor ballColor;

    Servo arm;

    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        armExtender = new ExtenderAndrew(hardwareMap, telemetry);
        grabber = new GrabberJack(hardwareMap, telemetry);
        extend = hardwareMap.dcMotor.get("extend");
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");



        clawOne = hardwareMap.servo.get("clawOne");
        clawTwo = hardwareMap.servo.get("clawTwo");

        clawOne.setPosition(oneOpen);
        clawTwo.setPosition(twoOpen);
        lineSensor = hardwareMap.colorSensor.get("lineSensor");
        lineSensor.setI2cAddress(I2cAddr.create8bit(0x44)); //68 in decimal

        ballColor = hardwareMap.colorSensor.get("ballColor");
        //ballColor.setI2cAddress(I2cAddr.create8bit(0x3A)); //58 in decimal

        arm = hardwareMap.servo.get("arm");
        arm.setPosition(.22);
        lineSensor.enableLed(false);
        lineSensor.enableLed(true);

        ballColor.enableLed(false);
        ballColor.enableLed(true);




    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        while(opModeIsActive()) {
            //arm.setPosition(gamepad1.left_trigger);

            driveTrain.drive(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

            if(gamepad2.left_bumper){
                grabber.close2();
            }
            else if(gamepad2.left_trigger >= .5){
                grabber.open2();
            }


            ColorHelper.printColorHSV(this.telemetry,lineSensor);
            ColorHelper.printColorHSV(this.telemetry,ballColor);
            telemetry.addData("Red state", ColorHelper.isLineRed(lineSensor));
            telemetry.addData("Blue state", ColorHelper.isLineBlue(lineSensor));
            telemetry.update();



            if(gamepad2.right_bumper){
                grabber.close1();
            }
            else if(gamepad2.right_trigger >= .5){
                grabber.open1();
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
