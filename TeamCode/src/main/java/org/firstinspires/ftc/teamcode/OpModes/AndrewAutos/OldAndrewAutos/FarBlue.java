package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 10/29/2017.
 */

@Autonomous(name="FarBlue", group="AndrewBot")
public class FarBlue extends LinearOpMode
{
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    boolean whereIsDatBall;
    JewelsAndrew jewel;
    VuMarkRecognition vuMarks;
    GrabberJack grabber;
    @Override
    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        //drive.init(0);


        //telemetry.addData("Status", "Initializing Vuforia");
        //telemetry.update();

        vuMarks = new VuMarkRecognition(this.hardwareMap, this.telemetry);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        jewel.reset();
        waitForStart();
        int goodCol = vuMarks.getVuMark();
        grabber = new GrabberJack(this.hardwareMap,this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();


        //int vuMark = vuMarks.getVuMark();

        //telemetry.addData("VuMark", vuMark);
        //telemetry.update();

        drive.encoderMoveMRGyro(270,.5,.5);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);
        if(jewel.isBlueLeft() || jewel.isRedRight()){
            whereIsDatBall = true;
        }
        jewel.hitBalls(
                jewel.first_color_sensor_the_ball_is_seen_as_blue(),
                jewel.first_color_sensor_the_ball_is_seen_as_red(),
                jewel.second_color_sensor_the_ball_is_seen_as_blue(),
                jewel.second_color_sensor_the_ball_is_seen_as_red()
        );


        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        sleep(500);
        if(jewel.moveLeft){
            drive.encoderMoveMRGyro(90,1.86,.2);
        }




    }
}
