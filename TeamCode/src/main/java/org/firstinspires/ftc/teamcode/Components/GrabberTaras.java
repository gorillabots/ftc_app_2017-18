package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//Created by Mikko on 12018-01-17

public class GrabberTaras
{
    private final double UL_CLOSE = 0;
    private final double UR_CLOSE = 0;
    private final double BL_CLOSE = 0;
    private final double BR_CLOSE = 0;
    private final double UL_OPEN = 0;
    private final double UR_OPEN = 0;
    private final double BL_OPEN = 0;
    private final double BR_OPEN = 0;

    Servo ul; //Upper left servo
    Servo ur; //Upper right servo
    Servo bl; //Bottom left servo
    Servo br; //Bottom right servo

    public GrabberTaras(HardwareMap hardwareMap)
    {
        ul = hardwareMap.servo.get("ulGrabber");
        ur = hardwareMap.servo.get("urGrabber");
        bl = hardwareMap.servo.get("blGrabber");
        br = hardwareMap.servo.get("brGrabber");
    }

    public void upperClose()
    {
        ul.setPosition(UL_CLOSE);
        ur.setPosition(UR_CLOSE);
    }

    public void upperOpen()
    {
        ul.setPosition(UL_OPEN);
        ur.setPosition(UR_OPEN);
    }

    public void lowerClose()
    {
        bl.setPosition(BL_CLOSE);
        br.setPosition(BR_CLOSE);
    }

    public void lowerOpen()
    {
        bl.setPosition(BL_OPEN);
        br.setPosition(BR_OPEN);
    }
}
