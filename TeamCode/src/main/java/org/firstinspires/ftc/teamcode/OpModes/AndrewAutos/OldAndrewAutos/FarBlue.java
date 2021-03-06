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
        drive = new Drive(this);
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
        if(jewel.leftSensorIsBlue() || jewel.rightSensorIsRed()){
            whereIsDatBall = true;
        }
        jewel.hitBalls(
                jewel.leftSensorIsBlue(),
                jewel.leftSensorIsRed(),
                jewel.rightSensorIsBlue(),
                jewel.rightSensorIsRed()
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
