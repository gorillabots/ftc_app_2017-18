package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.Drive;

/**
 * Created by Jarred on 10/29/2017.
 */
@Disabled
@Autonomous(name="FarBlue", group="AndrewBot")
public class FarBlue extends LinearOpMode
{
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;

    JewelsAndrew jewel;
    //VuMarkRecognition vuMarks;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        //drive.init(0);


        //telemetry.addData("Status", "Initializing Vuforia");
        //telemetry.update();

        //vuMarks = new VuMarkRecognition();
        //vuMarks.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();   

        //int vuMark = vuMarks.getVuMark();

        //telemetry.addData("VuMark", vuMark);
        //telemetry.update();

       jewel.lowerArm();
        sleep(500);

        jewel.hitBalls(drive,jewel.isBlue());
        sleep(500);

        jewel.reset();
        sleep(500);



        drive.close();
    }
}
