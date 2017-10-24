package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 10/6/2017.
 */

public class TestGrabber implements Grabber{

    HardwareMap hardwareMap;
    Telemetry telemetry;

    Servo claw;
    Servo spin;

    public TestGrabber(HardwareMap hardwareMap, Telemetry telemetry){

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        claw = hardwareMap.servo.get("claw");
        spin = hardwareMap.servo.get("spin");

    }
    @Override

    public void init(){}

    @Override
    public void open(int degrees, float seconds) {

    }

    @Override
    public void close(int degrees, float seconds) {

    }

    @Override
    public boolean isHolding() {
        return false;
    }


    public void rotate(int degrees, float seconds) {

    }
}
