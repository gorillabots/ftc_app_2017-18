package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Constants;
import org.firstinspires.ftc.teamcode.Components.ExtenderAndrew;
import org.firstinspires.ftc.teamcode.Components.GrabberAndrew;
import org.firstinspires.ftc.teamcode.Components.GrabberTaras;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;

/**
 * Created by Owner on 10/6/2017.
 */

@TeleOp(name = "driveSecondwTaras", group = "Backup")
public class TeleOpSecondBotTaras extends LinearOpMode {

    ArbitraryDirectionDrive driveTrain;
    ExtenderAndrew armExtender;
    GrabberAndrew grabber;
    private LinearOpMode opMode;
    JewelsAndrew jewels;
    GrabberTaras grabberT;
    DcMotor extend;
    DcMotor rotateOne;
    DcMotor rotateTwo;

    double oneOpen = Constants.leftOpen;
    double oneClose = Constants.leftClose;
    double twoOpen = Constants.rightOpen;
    double twoClose = Constants.rightClose;

    Servo clawOne;
    Servo clawTwo;

    ColorSensor ballColor;

    Servo arm;

    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        armExtender = new ExtenderAndrew(hardwareMap, telemetry);
        grabber = new GrabberAndrew(hardwareMap, telemetry);
        extend = hardwareMap.dcMotor.get("extend");
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewels = new JewelsAndrew(this.hardwareMap,this.telemetry);
        jewels.reset();
        jewels.toogleSwing(false);


        clawOne = hardwareMap.servo.get("clawOne");
        clawTwo = hardwareMap.servo.get("clawTwo");

        clawOne.setPosition(oneOpen);
        clawTwo.setPosition(twoOpen);

        ballColor = hardwareMap.colorSensor.get("ballColor");
        //ballColor.setI2cAddress(I2cAddr.create8bit(0x3A)); //58 in decimal

        arm = hardwareMap.servo.get("arm");
        arm.setPosition(.22);

        ballColor.enableLed(false);
        ballColor.enableLed(true);

    }

    @Override
    public void runOpMode() throws InterruptedException {
        init_();
        boolean relicTog = false;
        waitForStart();

        while (opModeIsActive()) {
                //b is relicTog button
            if (relicTog) {                              //original autonomous w/ relic arm
                driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

                if (gamepad2.left_bumper) {

                } else if (gamepad2.left_trigger >= .5) {

                }

                if (gamepad2.right_bumper) {
                    grabber.closeinst1();
                } else if (gamepad2.right_trigger >= .5) {
                    grabber.openinst1();
                }


                if (gamepad2.dpad_up) {
                    armExtender.extend(-1);
                } else if (gamepad2.dpad_down) {
                    armExtender.extend(1);
                } else {
                    armExtender.stop();
                }

                grabber.rotateOne(gamepad2.left_stick_y);
                grabber.rotateTwo(gamepad2.right_stick_y);

                if (gamepad2.b) {
                    relicTog = false;
                }
            } else {                                                                     //taras autonomus w/out relic arm
                driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

                grabberT.raise(gamepad2.right_stick_y);

                if (gamepad2.right_bumper) {
                    grabberT.upperClose();
                }
                if (gamepad2.right_trigger > .5) {
                    grabberT.upperOpen();
                }
                if (gamepad2.left_bumper) {
                    grabberT.lowerClose();
                }
                if (gamepad2.left_trigger > .5) {
                    grabberT.lowerOpen();
                }
                if (gamepad2.y) {
                    grabberT.upperOpenWide();
                }
                if (gamepad2.a) {
                    grabberT.lowerOpenWide();
                }
                if (gamepad2.b) {
                    relicTog = true;
                }
                sleep(20);

                grabberT.raise(0);
            }
        }
    }
}
