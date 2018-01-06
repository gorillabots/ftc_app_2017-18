package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Andy on 12/15/2017.
 */
@Disabled
@Autonomous(name = "Romeo", group = "AAA")
public class R extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    RangeCrypto rangeCrypto;
    DcMotor rotateOne;
    DcMotor rotateTwo;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        drive = new Drive(this);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toggleSwing(false);
        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        jewel.toggleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("blue 1", jewel.first_color_sensor_the_ball_is_seen_as_blue());
        telemetry.addData("red 1", jewel.first_color_sensor_the_ball_is_seen_as_red());
        telemetry.addData("blue 2", jewel.second_color_sensor_the_ball_is_seen_as_blue());
        telemetry.addData("red 2", jewel.second_color_sensor_the_ball_is_seen_as_red());
        telemetry.update();
        sleep(500);
        jewel.hitBalls(
                jewel.first_color_sensor_the_ball_is_seen_as_blue(),
                jewel.first_color_sensor_the_ball_is_seen_as_red(),
                jewel.second_color_sensor_the_ball_is_seen_as_blue(),
                jewel.second_color_sensor_the_ball_is_seen_as_red()
        );
        sleep(500);
        jewel.reset();
        jewel.toggleSwing(false);
        sleep(500);
    }
}
