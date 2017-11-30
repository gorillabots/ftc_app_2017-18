package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Jewels
{
    final double BASE_UP = .08;
    final double BASE_SCAN = .6;
    final double BASE_HIT = .86;
    final double BASE_ERROR = .03;

    final double OTHER_IN = 0; //Retracted
    final double OTHER_SCAN = .5; //Scanning ??? ball
    final double OTHER_HIT_CENTER = .6; //Between the balls, ready to hit
    final double OTHER_HIT_LEFT = .6; //Hits the left jewel
    final double OTHER_HIT_RIGHT = .6 //Hits the right jewel
    final double OTHER_ERROR = .03;


    Servo baseServo;
    Servo otherServo;
    ColorSensor color;

    public Jewels(HardwareMap hm)
    {
        baseServo = hm.servo.get("rotateArm");
        otherServo = hm.servo.get("arm");
    }

    public void reset()
    {
        otherServo.setPosition(OTHER_IN); //Retract other servo
        waitForServoCloseEnough(otherServo, OTHER_IN, OTHER_ERROR);
        baseServo.setPosition(BASE_UP);
        waitForServoCloseEnough(baseServo, BASE_UP, BASE_ERROR);
    }

    public void scanPosition()
    {
        baseServo.setPosition(BASE_SCAN);
        waitForServoCloseEnough(baseServo, BASE_SCAN, BASE_ERROR);
        otherServo.setPosition(OTHER_SCAN);
        waitForServoCloseEnough(otherServo, OTHER_SCAN, OTHER_ERROR);
    }

    private void hitPosition()
    {
        otherServo.setPosition(OTHER_HIT_CENTER); //Get between the jewels horizontally
        waitForServoCloseEnough(otherServo, OTHER_HIT_CENTER, OTHER_ERROR);
        baseServo.setPosition(BASE_HIT); //Get between the jewels vertically
        waitForServoCloseEnough(baseServo, BASE_HIT, BASE_ERROR);
    }

    public void hitLeft()
    {
        hitPosition();
        otherServo.setPosition(OTHER_HIT_LEFT);
        waitForServoCloseEnough(otherServo, OTHER_HIT_LEFT, OTHER_ERROR);
        baseServo.setPosition(BASE_SCAN);
        waitForServoCloseEnough(baseServo, BASE_SCAN, BASE_ERROR);
    }

    public void hitRight()
    {
        hitPosition();
        otherServo.setPosition(OTHER_HIT_RIGHT);
        waitForServoCloseEnough(otherServo, OTHER_HIT_RIGHT, OTHER_ERROR);
        baseServo.setPosition(BASE_SCAN);
        waitForServoCloseEnough(baseServo, BASE_SCAN, BASE_ERROR);
    }

    private void waitForServoCloseEnough(Servo servo, double target, double error)
    {
        double min = target - error;
        double max = target + error;

        try
        {
            while(!inRange(servo.getPosition(), min, max))
            {
                Thread.sleep(50);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private boolean inRange(double value, double min, double max)
    {
        return (value > min) && (value < max);
    }
}
