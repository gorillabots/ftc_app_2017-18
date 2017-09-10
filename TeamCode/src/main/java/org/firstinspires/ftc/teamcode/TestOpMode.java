package org.firstinspires.ftc.teamcode;

//Created by Mikko on 2017-09-08

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name="TestOpMode", group="Test")
public class TestOpMode extends OpMode
{
    @Override
    public void init()
    {
        telemetry.addData("State", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        telemetry.addData("State", "Running");
        telemetry.update();
    }
}
