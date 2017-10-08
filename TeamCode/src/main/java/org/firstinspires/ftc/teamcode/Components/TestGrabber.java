package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 10/6/2017.
 */

public class TestGrabber implements Grabber{

    public TestGrabber(HardwareMap hardwareMap, Telemetry telemetry){

    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isHolding() {
        return false;
    }
}
