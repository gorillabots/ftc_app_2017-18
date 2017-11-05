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
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;
    double position = (MAX-MIN)/2;

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
        spin.setPosition(degrees);

    }

    @Override
    public void open() {
        position += INCREMENT;
        if(position >= MAX){
            position = MAX;
        }

    }

    @Override
    public void close() {
        position -= INCREMENT;
        if(position <= MIN){
            position = MIN;
        }

    }

    @Override
    public boolean isHolding() {
        return false;
    }{

    }

}