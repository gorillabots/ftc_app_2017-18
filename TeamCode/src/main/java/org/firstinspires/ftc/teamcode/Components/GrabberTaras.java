package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//Created by Mikko on 12018-01-17

public class GrabberTaras
{
    public DcMotor raise;

    private Servo ul; //Upper left servo
    private final double UL_CLOSE = .28;
    private final double UL_OPEN = .50;

    private Servo ur; //Upper right servo
    private final double UR_CLOSE = .52;
    private final double UR_OPEN = .27;

    private Servo bl; //Bottom left servo
    private final double BL_CLOSE = .29;
    private final double BL_OPEN = .08;

    private Servo br; //Bottom right servo
    private final double BR_CLOSE = .40;
    private final double BR_OPEN = .7;

    private final double UL_OPEN_WIDE = .75;
    private final double UR_OPEN_WIDE = .02;
    private final double BL_OPEN_WIDE = 0;
    private final double BR_OPEN_WIDE = .88;

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
    public void lowerOpenWide()
    {
        bl.setPosition(BL_OPEN_WIDE);
        br.setPosition(BR_OPEN_WIDE);
        lowerOpen = true;
    }
    public void upperOpenWide()
    {
        ul.setPosition(UL_OPEN_WIDE);
        ur.setPosition(UR_OPEN_WIDE);
        upperOpen = true;
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
