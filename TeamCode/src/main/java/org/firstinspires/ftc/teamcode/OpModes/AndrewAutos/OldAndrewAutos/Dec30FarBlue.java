package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

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
@Autonomous(name = "blueAndy", group = "AndrewBot")
public class Dec30FarBlue extends LinearOpMode {
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

        drive = new Drive(this.hardwareMap, this.telemetry);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);
        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");

        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        int goodCol = vuMark.getVuMark();
        //-------------------------------------------jewel↓↓↓↓
        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("blue left", jewel.isBlueLeft());
        telemetry.addData("red left", jewel.isRedLeft());
        telemetry.addData("blue right", jewel.isBlueRight());
        telemetry.addData("red right", jewel.isRedRight());
        telemetry.addData("col", goodCol);
        telemetry.update();
        sleep(500);
        //jewel.hitBalls(jewel.isRedRight(), jewel.isBlueLeft());
        //jewel.AHEhitBallsVariablesForBlue(jewel.first_color_sensor_the_ball_is_seen_as_blue(), jewel.first_color_sensor_the_ball_is_seen_as_red(), jewel.second_color_sensor_the_ball_is_seen_as_blue(), jewel.second_color_sensor_the_ball_is_seen_as_red());
        jewel.AHEhitBallsVariablesForBlueVersionTwo(
                jewel.first_color_sensor_the_ball_is_seen_as_red(),
                jewel.first_color_sensor_the_ball_is_seen_as_blue(),
                jewel.second_color_sensor_the_ball_is_seen_as_red(),
                jewel.second_color_sensor_the_ball_is_seen_as_blue()
        )
        ;
        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toogleSwing(false);
        sleep(500);

        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        //---------------------------------jewel↑↑↑

        //------↓ raise arm to prevent damage ↓----------
        runtime.reset();
        while (runtime.seconds() < 0.00001) {
            grabber.rotateTwo(0.2);
        }
        sleep(3000);
        grabber.rotateTwo(0);
        runtime.reset();
        //------↑ raise arm to prevent damage ↑----------

        //------↓ post platform aligning ↓---------------
        drive.encoderMoveMRGyro2(90, .8, .3, 0.5);
        //↑ go forward
        drive.encoderMoveMRGyro2(270, .20, .3, 0.5);
        //↑ align via platform
        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);
        sleep(500);
        //↑ slightly fowrward
        //------↑ post platform aligning ↑-------
        drive.turn(-90, 2, 1, .1);

        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(2000);
        stopMotors();

        //------↓ align to the correct column ↓---   //.16 between columns
        if (goodCol == 3) {
            telemetry.addData("Going for", "R");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, 1.25, .6, 0.5);
            drive.turn(90, 2, .5, .1);
            sleep(500);
        } else if (goodCol == 1) {
            telemetry.addData("Going for", "L");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, .75, .6, 0.5);
            drive.turn(90, 2, .5, .1);
            sleep(500);
        } else {
            telemetry.addData("Going for", "C");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, 1, .6, 0.5);
            drive.turn(90, 2, .5, .1);
            sleep(500);
        }
        //------↑ align to the correct column ↑---

        //------↓ dropping and pushing in glyph ↓---
        telemetry.addData("Step", "A");
        telemetry.update();
        drive.encoderMoveMRGyro2(90, .3, .3, 0.5);
        sleep(500);

        grabber.openinst1();
        grabber.openinst2();

        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);

        sleep(500);
        drive.encoderMoveMRGyro2(270, .2, .3, 0.5);
        sleep(500);

        /*telemetry.addData("Step", "C");      //unnecessary
        telemetry.update();
        drive.turn(180, 2, .5 , .1);
        sleep (500);

        telemetry.addData("Step", "D");
        telemetry.update();
        drive.encoderMoveMRGyro2(270, 1.2, .5, 0.5);
        sleep (500);
        //------↑ dropping and pushing in glyph ↑---*/
    }

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }


}
