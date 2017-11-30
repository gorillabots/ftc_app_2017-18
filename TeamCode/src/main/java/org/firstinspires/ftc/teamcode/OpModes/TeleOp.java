package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import junit.framework.Test;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.Grabber1;
import org.firstinspires.ftc.teamcode.Components.Grabber2;
import org.firstinspires.ftc.teamcode.Components.ArmExtender1;
import org.firstinspires.ftc.teamcode.Components.TestShoppingCartD;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "drive", group = "Backup")
public class TeleOp extends LinearOpMode {

    ArbitraryDirectionDrive driveTrain;
    ArmExtender1 armExtender;
    Grabber2 grabber;
    private LinearOpMode opMode;

    Servo claw1;
    Servo claw2;

    DcMotor extend1;
    DcMotor extend2;

    Servo wrist1;
    Servo wrist2;

    CRServo intake1;
    CRServo intake2;

    DcMotor belt;
    DcMotor rotate;

    Servo arm;
    Servo rotateArm;

    ElapsedTime wristTime;
    boolean switchBol = false;
    double speed  = 1;

    boolean waitRelease = true;

    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap, this.telemetry);
        telemetry.addData("done","gyro");
        telemetry.update();

        belt = hardwareMap.dcMotor.get("belt");
        claw1 = hardwareMap.servo.get("claw1");
        claw2 = hardwareMap.servo.get("claw2");

        extend1 = hardwareMap.dcMotor.get("extend1");
        extend2 = hardwareMap.dcMotor.get("extend2");

        wrist1 = hardwareMap.servo.get("wrist1");
        wrist2 = hardwareMap.servo.get("wrist2");

        intake1 = hardwareMap.crservo.get("leftWheel");
        intake2 = hardwareMap.crservo.get("rightWheel");


        rotate = hardwareMap.dcMotor.get("rotate");
        claw1.setPosition(0);
        claw2.setPosition(.78);

        arm = hardwareMap.servo.get("arm");
        rotateArm = hardwareMap.servo.get("rotateArm");

        rotateArm.setPosition(.08);

        wristTime = new ElapsedTime();

        wristTime.startTime();
        ElapsedTime waitTime;

        waitTime = new ElapsedTime();


    }

    @Override
    public void runOpMode() throws InterruptedException {

        init_();
        telemetry.addData("Done", "init");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {
                speed = 1;
            } else if (gamepad1.dpad_down) {
                speed = .75;
            } else if (gamepad1.dpad_left) {
                speed = .25;
            }

            driveTrain.drive(gamepad1.left_stick_x * speed, gamepad1.left_stick_y * speed, gamepad1.right_stick_x * speed);

            rotate.setPower(gamepad2.right_stick_y);
            if (gamepad2.left_bumper) {
                claw1.setPosition(.33);
                claw2.setPosition(.52);
            } else if (gamepad2.left_trigger > .5) {
                claw1.setPosition(.098);
                claw2.setPosition(.705);
            }


            extend1.setPower(-gamepad2.left_stick_y);
            extend2.setPower(-gamepad2.left_stick_y);

            wrist1.setPosition((-.57 * gamepad2.right_trigger) + .78);
            wrist2.setPosition((.57 * gamepad2.right_trigger) + .21);


            if (gamepad1.right_bumper) {
                intake1.setPower(-1);
                intake2.setPower(1);
                belt.setPower(1);
            } else if (gamepad1.right_trigger > .5) {
                intake1.setPower(1);
                intake2.setPower(-1);
                belt.setPower(-1);

            } else {
                intake1.setPower(0);
                intake2.setPower(0);
                belt.setPower(0);
            }

        }


    }

}

