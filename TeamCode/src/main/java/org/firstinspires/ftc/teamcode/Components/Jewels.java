package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Jewels
{
    final double RESET_BASE = .08;
    final double RESET_OTHER = 0;

    final double SCAN_BASE = .78;
    final double SCAN_OTHER = .83;

    final double UPTHING_BASE = .7;

    final double BETWEEN_BASE = .83;
    final double BETWEEN_OTHER = 1;

    final double HIT_OTHER = 0;

    public Servo baseServo;
    public Servo otherServo;
    public ColorSensor color;

    public Jewels(HardwareMap hm)
    {
        baseServo = hm.servo.get("arm");
        otherServo = hm.servo.get("rotateArm");
        color = hm.colorSensor.get("ballColor");
        color.enableLed(false);
        color.enableLed(true);
        color.enableLed(false);
    }

    public void reset()
    {
        baseServo.setPosition(RESET_BASE);
        otherServo.setPosition(RESET_OTHER);
    }

    public void scanPosition()
    {
        baseServo.setPosition(SCAN_BASE);
        otherServo.setPosition(SCAN_OTHER);
    }

    public void betweenPosition()
    {
        try
        {
            baseServo.setPosition(UPTHING_BASE); //Go up to avoid breaking everything
            otherServo.setPosition(BETWEEN_OTHER); //Go between horizontally
            Thread.sleep(500);
            baseServo.setPosition(BETWEEN_BASE);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void hitRight()
    {
        otherServo.setPosition(HIT_OTHER);
    }

    public boolean isRed()
    {
        try
        {
            color.enableLed(true);
            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.red() >= color.blue();

            color.enableLed(false);

            return isRed;
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return false; //Should never happen
    }
}
