package org.firstinspires.ftc.teamcode;

//Created by Mikko on 2017-09-08

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TestOpMode extends OpMode
{
    public void init()
    {
        telemetry.addData("State", "Initialized");
        telemetry.update();
    }

    public void loop()
    {
        telemetry.addData("State", "Running");
        telemetry.update();
    }
}
