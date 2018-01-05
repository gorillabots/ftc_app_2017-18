package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
@Autonomous(name = "farBlueAndy", group = "AndrewBot")
public class FarBlue extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    double centerColumnDistance = .754;
    double distanceBetween = .151;

    double rightColumnDistance = centerColumnDistance + distanceBetween;
    double leftColumnDistance = centerColumnDistance - distanceBetween;
    //1 inch should be .01875
    //eq for determining distance is y = 0.02x - 0.05833333333
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
        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();
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

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);
        grabber.closeinst2();
        grabber.closeinst1();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        grabber.closeinst2();
        grabber.closeinst1();
        waitForStart();

        int goodCol = vuMark.getVuMark();
        runtime.reset();
        while (runtime.seconds() < 0.00001) {
            grabber.rotateTwo(0.35);
        }
        //-------------------------------------------jewel↓↓↓↓
        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(200);
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
        sleep(400);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toogleSwing(false);
        grabber.rotateTwo(0);
        runtime.reset();

        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        //---------------------------------jewel↑↑↑
        //------↓ post platform aligning ↓---------------
        drive.encoderMoveMRGyro2(90, .7, .5, 0.5);
        //↑ go forward
        // drive.encoderMoveMRGyro2(270, .20, .3, 0.5);

        m1.setPower(.2);
        m2.setPower(-.2);
        m3.setPower(-.2);
        m4.setPower(.2);
        sleep(1000);
        stopMotors();
        sleep(400);
        //↑ align via platform ↑----
        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);
        sleep(300);
        //↑ slightly fowrward
        //------↑ post platform aligning ↑-------
        drive.turn(-90, 2, 1, .1);

        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(1500);
        stopMotors();
        sleep(300);

        //------↓ align to the correct column ↓---
        if (goodCol == 3) {
            telemetry.addData("Going for", "R");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, rightColumnDistance, .4, 0.5);
            drive.turn(90, 1, 1, .1);
            sleep(400);
        } else if (goodCol == 1) {
            telemetry.addData("Going for", "L");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, leftColumnDistance, .4, 0.5);
            drive.turn(90, 1, 1, .1);
            sleep(400);
        } else {
            telemetry.addData("Going for", "C");
            telemetry.update();
            drive.encoderMoveMRGyro2(90, centerColumnDistance, .4, 0.5);
            drive.turn(90, 1, 1, .1);
            sleep(400);
        }
        //------↑ align to the correct column ↑---

        //------↓ dropping and pushing in glyph ↓---
        telemetry.addData("Step", "A");
        telemetry.update();
        drive.encoderMoveMRGyro2(90, .15, .3, 0.5);

        grabber.openinst1();
        grabber.openinst2();

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep(1000);
        stopMotors();
        sleep(500);
        drive.encoderMoveMRGyro2(270, .15, .3, 0.5);
        sleep(200);
        drive.turn(180, 1, 1, .1);
        sleep(200);

        drive.driveTrain.stopMotors();
        drive.driveTrain.m1.setPower(.3);
        drive.driveTrain.m2.setPower(-.3);
        drive.driveTrain.m3.setPower(-.3);
        drive.driveTrain.m4.setPower(.3);
        sleep(2000);
        drive.driveTrain.stopMotors();
        sleep(300);

        drive.encoderMoveMRGyro2(90, .15, .3, .5);

        if (goodCol == 3) {
            drive.encoderMoveMRGyro2(180, .3, .6, .5);
        } else if (goodCol == 1) {
            drive.encoderMoveMRGyro2(0, .15, .6, .5);
        } else {

        }
    }

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }


}
