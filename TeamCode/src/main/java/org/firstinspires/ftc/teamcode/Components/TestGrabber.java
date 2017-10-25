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
    public void init(){
        spin.getPosition();
    }


    @Override
    public void rotate(double degrees) {
        spin.setPosition(.25);

    }

    @Override
    public void open() {
        claw.setPosition(.8);

    }

    @Override
    public void close() {
        claw.setPosition(0);

    }

    @Override
    public boolean isHolding() {
        return false;
    }{

    }

}