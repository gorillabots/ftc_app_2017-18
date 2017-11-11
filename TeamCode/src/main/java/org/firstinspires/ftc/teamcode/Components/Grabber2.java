package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 11/10/2017.
 */

public class Grabber2 implements Grabber {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;
    double position = (MAX - MIN) / 2;

    Servo claw1;
    Servo claw2;

    public Grabber2(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        claw1 = hardwareMap.servo.get("claw1");
        claw2 = hardwareMap.servo.get("claw2");


    }

    @Override
    public void init() {

    }

    @Override
    public void open1() {
        position += INCREMENT;
        if (position >= MAX) {
            position = MAX;
        }
        claw1.setPosition(position);

    }

    @Override
    public void open2() {
        position += INCREMENT;
        if (position >= MAX) {
            position = MAX;
        }
        claw2.setPosition(position);

    }

    @Override
    public void close1() {
        position -= INCREMENT;
        if (position <= MIN) {
            position = MIN;
        }
        claw1.setPosition(position);
     }

    @Override
    public void close2() {
        position -= INCREMENT;
        if (position <= MIN) {
            position = MIN;
        }
        claw2.setPosition(position);
    }


    @Override
    public void rotate(double degrees) {

    }

    @Override
    public boolean isHolding() {
        return false;
    }
}
