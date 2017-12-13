package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive;

public class JewelsAndrew{

    public ColorSensor color;
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;
    public Servo jewelArm;
    Telemetry telemetry;

    public JewelsAndrew(HardwareMap hm, Telemetry tele) {
        telemetry = tele;
        telemetry.addData("status","init jewels");
        telemetry.update();

        color = hm.colorSensor.get("ballColor");
        jewelArm = hm.servo.get("arm");

        color.enableLed(false);
        color.enableLed(true);


        telemetry.addData("status","finished jewel init");
        telemetry.update();
    }

    public void reset() {
        jewelArm.setPosition(ARM_RAISED);
        color.enableLed(false);
    }

    public void lowerArm(){
        jewelArm.setPosition(ARM_LOWERED);
        color.enableLed(true);
    }

    public void hitBalls(Drive drive, boolean color){
        if(color){
            drive.encoderMoveMRGyro(90,.2,.5);
        }
        else{
            drive.encoderMoveMRGyro(270,.2,.5);
        }
    }



    public boolean isRed() {

        try {
            color.enableLed(true);
            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.red() >= color.blue();

            color.enableLed(false);

            return isRed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean isBlue() {
        try {
            color.enableLed(true);
            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.blue() >= color.red();

            color.enableLed(false);

            return isRed;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }
}
