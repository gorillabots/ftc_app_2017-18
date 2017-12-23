package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive;

public class JewelsAndrew{

    public ColorSensor color;
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;
    public Servo jewelArm;
    Telemetry telemetry;
    public boolean moveLeft;

    public ColorSensor secondColor;
    public JewelsAndrew(HardwareMap hm, Telemetry tele) {
        telemetry = tele;
        telemetry.addData("status","init jewels");
        telemetry.update();

        color = hm.colorSensor.get("ballColor");
        jewelArm = hm.servo.get("arm");

        color.enableLed(false);
        color.enableLed(true);

        secondColor =hm.colorSensor.get("leftColor");
        secondColor.setI2cAddress(I2cAddr.create8bit(68));


        telemetry.addData("status","finished jewel init");
        telemetry.update();
    }

    public void ledState(boolean led){
        color.enableLed(led);
        secondColor.enableLed(led);
    }
    public void reset() {
        jewelArm.setPosition(ARM_RAISED);
        ledState(true);

    }

    public void lowerArm(){
        jewelArm.setPosition(ARM_LOWERED);
        color.enableLed(true);
    }

    public void hitBalls(Drive drive, boolean color, boolean opposColor){
        if(color || opposColor ){
            drive.turn(349,2,.2,.1);
            this.reset();
            try {
                Thread.sleep(100,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drive.turn(11,2,.2,.1);
           // drive.encoderMoveMRGyro(90,.2,.5);
        }
        else{
            drive.turn(11,2,.2,.1);
            this.reset();
            try {
                Thread.sleep(100,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            drive.turn(349,2,.2,.1);
            //drive.encoderMoveMRGyro(270,.2,.5);
            moveLeft = true;
        }
    }



    public boolean isRedRight() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.red() >= color.blue();



            return isRed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }
    public boolean isRedLeft() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = secondColor.red() >= secondColor.blue();



            return isRed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean isBlueRight() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.blue() >= color.red();



            return isRed;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }
    public boolean isBlueLeft() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = secondColor.blue() >= secondColor.red();



            return isRed;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }
}
