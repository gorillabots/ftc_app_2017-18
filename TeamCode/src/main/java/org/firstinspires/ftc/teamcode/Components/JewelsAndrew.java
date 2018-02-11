package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive;

public class JewelsAndrew
{
    private final double ARM_UP = .15;
    private final double ARM_DOWN = .9;
    private final double SWING_STOWED = .63;
    private final double SWING_CENTER = .39;
    private final double SWING_LEFT = .59;
    private final double SWING_RIGHT = .28;

    private Servo arm;
    private Servo swing;

    private ColorSensor colorL;
    private ColorSensor colorR;

    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public enum BallColor
    {
        RED,
        BLUE,
        ERROR
    }

    private BallColor invColor(BallColor bc)
    {
        if(bc == BallColor.RED)
        {
            return BallColor.BLUE;
        }
        else if(bc == BallColor.BLUE)
        {
            return BallColor.RED;
        }
        else
        {
            return BallColor.ERROR;
        }
    }

    public JewelsAndrew(LinearOpMode opMode)
    {
        this.opMode = opMode;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        telemetry.addData("Status", "Inititalizing Jewels");
        telemetry.update();

        arm = hardwareMap.servo.get("arm");
        swing = hardwareMap.servo.get("swing");

        colorL = hardwareMap.colorSensor.get("ballColor");

        colorR = hardwareMap.colorSensor.get("leftColor");
        colorR.setI2cAddress(I2cAddr.create8bit(68));

        enableLEDs(false);
        enableLEDs(true);

        stow();

        telemetry.addData("Status", "Initialized Jewels");
        telemetry.update();
    }

    public void enableLEDs(boolean led)
    {
        colorL.enableLed(led);
        colorR.enableLed(led);
    }

    public void stow()
    {
        if(!opMode.opModeIsActive())
        {
            return;
        }

        arm.setPosition(ARM_UP);
        swing.setPosition(SWING_STOWED);
    }

    public void upright()
    {
        if(!opMode.opModeIsActive())
        {
            return;
        }

        arm.setPosition(ARM_UP);
        swing.setPosition(SWING_CENTER);
    }

    public void lowerArm()
    {
        if(!opMode.opModeIsActive())
        {
            return;
        }

        arm.setPosition(ARM_DOWN);
        swing.setPosition(SWING_CENTER);
    }

    public void swingRight()
    {
        if(!opMode.opModeIsActive())
        {
            return;
        }

        arm.setPosition(ARM_DOWN);
        swing.setPosition(.28);
    }

    public void swingLeft()
    {
        if(!opMode.opModeIsActive())
        {
            return;
        }

        arm.setPosition(ARM_DOWN);
        swing.setPosition(.59);
    }

    public void hitBalls(BallColor toHit)
    {
        enableLEDs(true);

        opMode.sleep(50); //Let LED's enable, let arm settle

        BallColor l = sensorL();
        BallColor r = sensorR();

        enableLEDs(false);

        if(l == toHit && r == invColor(toHit)) //Hit left
        {
            swingLeft();
        }
        else if(r == toHit && l == invColor(toHit)) //Hit right
        {
            swingRight();
        }
        else if(l == BallColor.ERROR && r == BallColor.ERROR) //Both failed
        {
            swingLeft(); //Hit both
            opMode.sleep(100);
            swingRight();
        }
        else if(l == BallColor.ERROR) //Left failed
        {
            if(r == toHit) //Hit right
            {
                swingRight();
            }
            else //Hit left
            {
                swingLeft();
            }
        }
        else if(r == BallColor.ERROR) //Right failed
        {
            if(l == toHit) //Hit left
            {
                swingLeft();
            }
            else //Hit right
            {
                swingRight();
            }
        }

        opMode.sleep(100);
    }

    private BallColor sensorL()
    {
        int r = colorL.red();
        int b = colorL.blue();

        if(r > b)
        {
            return BallColor.RED;
        }
        else if(b > r)
        {
            return BallColor.BLUE;
        }
        else
        {
            return BallColor.ERROR;
        }
    }

    private BallColor sensorR()
    {
        int r = colorR.red();
        int b = colorR.blue();

        if(r > b)
        {
            return BallColor.RED;
        }
        else if(b > r)
        {
            return BallColor.BLUE;
        }
        else
        {
            return BallColor.ERROR;
        }
    }
}
