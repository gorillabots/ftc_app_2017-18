package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Constants;
import org.firstinspires.ftc.teamcode.Components.ExtenderAndrew;
import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;

//Created by Jarred on 2017-10-06

@TeleOp(name="driveSecond", group="Backup")
public class TeleOpSecondBot extends LinearOpMode
{
    private ArbitraryDirectionDrive driveTrain;
    private ExtenderAndrew armExtender;
    private GrabberJack grabber;
    private JewelsAndrew jewels;

    DcMotor extend;
    DcMotor rotateOne;
    DcMotor rotateTwo;

    double oneOpen = Constants.leftOpen;
    double twoOpen = Constants.rightOpen;

    Servo clawOne;
    Servo clawTwo;

    ColorSensor ballColor;

    Servo arm;

    public void init_()
    {
        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        armExtender = new ExtenderAndrew(hardwareMap, telemetry);
        grabber = new GrabberJack(hardwareMap, telemetry);
        extend = hardwareMap.dcMotor.get("extend");
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewels = new JewelsAndrew(this.hardwareMap,this.telemetry);
        jewels.reset();
        jewels.toggleSwing(false);

        clawOne = hardwareMap.servo.get("clawOne");
        clawOne.setPosition(oneOpen);
        clawTwo = hardwareMap.servo.get("clawTwo");
        clawTwo.setPosition(twoOpen);

        ballColor = hardwareMap.colorSensor.get("ballColor");
        ballColor.enableLed(true);
        ballColor.enableLed(false);

        arm = hardwareMap.servo.get("arm");
        arm.setPosition(.22);
    }

    @Override
    public void runOpMode()
    {
        init_();

        waitForStart();
        while(opModeIsActive())
        {
            driveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if(gamepad2.left_bumper)
            {
                grabber.closeinst2();
            }
            else if(gamepad2.left_trigger >= .5)
            {
                grabber.openinst2();
            }

            ColorHelper.printColorHSV(this.telemetry,ballColor);
            telemetry.update();

            if(gamepad2.right_bumper)
            {
                grabber.closeinst1();
            }
            else if(gamepad2.right_trigger >= .5)
            {
                grabber.openinst1();
            }

            if(gamepad2.dpad_up)
            {
                armExtender.extend(-1);
            }
            else if(gamepad2.dpad_down)
            {
                armExtender.extend(1);
            }
            else
            {
                armExtender.stop();
            }

            grabber.rotateOne(gamepad2.left_stick_y);
            grabber.rotateTwo(gamepad2.right_stick_y);
        }
    }
}
