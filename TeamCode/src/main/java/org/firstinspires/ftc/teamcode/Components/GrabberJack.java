package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 10/6/2017.
 */

public class GrabberJack  {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;
    double position = (MAX - MIN) / 2;

    Servo claw1;
    Servo claw2;
    Servo spin;

    DcMotor belt;

    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    public GrabberJack(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        claw1 = hardwareMap.servo.get("claw1");
        claw2 = hardwareMap.servo.get("claw2");
        spin = hardwareMap.servo.get("spin");
        belt = hardwareMap.dcMotor.get("belts");
        //modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");



    }

    
    public void init() {
        spin.getPosition();
    }


    
    public void rotate(double degrees) {
        spin.setPosition(degrees);

    }

    
    public void open1() {
        position += INCREMENT;
        if (position >= MAX) {
            position = MAX;
        }
        claw1.setPosition(position);
        claw2.setPosition(position);

    }

    
    public void open2() {
        open1();
    }

    
    public void close1() {
        position -= INCREMENT;
        if (position <= MIN) {
            position = MIN;
        }
        claw1.setPosition(position);
    }

    
    public void close2() {
        position -= INCREMENT;
        if (position <= MIN) {
            position = MIN;
        }
        claw2.setPosition(position);
    }


    
    public boolean isHolding() {
        return false;
    }

    {

    }

    
    public void rotateOne(double murica){}
    
    public void rotateTwo(double murica){}

    public void runBelts(double power, boolean toogle){
        if(toogle){
            belt.setPower(-power);
        }
        else{
            belt.setPower(power);
        }
    }
}