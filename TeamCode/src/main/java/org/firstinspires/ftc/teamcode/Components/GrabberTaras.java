package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//Created by Mikko on 12018-01-17

public class GrabberTaras
{
    DcMotor raise;

    Servo ul; //Upper left servo
    private final double UL_CLOSE =.5;
    private final double UL_OPEN = 0;

    Servo ur; //Upper right servo
    private final double UR_CLOSE = .705;
    private final double UR_OPEN = 1;

    Servo bl; //Bottom left servo
    private final double BL_CLOSE = .7843;
    private final double BL_OPEN = .2;

    Servo br; //Bottom right servo
    private final double BR_CLOSE = .66;
    private final double BR_OPEN = 1;

    boolean upperOpen;
    boolean lowerOpen;

    public GrabberTaras(HardwareMap hardwareMap)
    {
        ul = hardwareMap.servo.get("ulGrabber");
        ur = hardwareMap.servo.get("urGrabber");
        bl = hardwareMap.servo.get("blGrabber");
        br = hardwareMap.servo.get("brGrabber");

        raise = hardwareMap.dcMotor.get("grabberRaise");

        upperOpen();
        lowerOpen();
    }

    public void raise(double power)
    {
        raise.setPower(power);
    }

    public void upperClose()
    {
        ul.setPosition(UL_CLOSE);
        ur.setPosition(UR_CLOSE);
        upperOpen = false;
    }

    public void upperOpen()
    {
        ul.setPosition(UL_OPEN);
        ur.setPosition(UR_OPEN);
        upperOpen = true;
    }

    public void upperToggle()
    {
        if(upperOpen)
        {
            upperClose();
        }
        else
        {
            upperOpen();
        }
    }

    public void lowerClose()
    {
        bl.setPosition(BL_CLOSE);
        br.setPosition(BR_CLOSE);
        lowerOpen = false;
    }

    public void lowerOpen()
    {
        bl.setPosition(BL_OPEN);
        br.setPosition(BR_OPEN);
        lowerOpen = true;
    }

    public void lowerToggle()
    {
        if(lowerOpen)
        {
            lowerClose();
        }
        else
        {
            lowerOpen();
        }
    }
}
