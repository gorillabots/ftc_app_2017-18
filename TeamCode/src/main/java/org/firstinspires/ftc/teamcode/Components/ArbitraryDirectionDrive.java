package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-10-24

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ArbitraryDirectionDrive
{
    public static final int N = 0, NE = 45, E = 90, SE = 135, S = 180, SW = 215, W = 270, NW = 305;

    OpMode opmode;

    public ArbitraryDirectionDrive(OpMode opmode)
    {
        this.opmode = opmode;
    }

    public void drive()
    {
        while(true)
        {
            int heading = getHeadingBecauseImBadAtNavx();
        }
    }

    public int getHeadingBecauseImBadAtNavx()
    {
        return 0;
    }
}
