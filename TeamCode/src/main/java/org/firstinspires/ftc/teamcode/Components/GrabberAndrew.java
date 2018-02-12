package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.Constant;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 11/10/2017.
 */

public class GrabberAndrew  {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    double INCREMENT = 0.01;
    double MAXLeft = Constants.leftOpen;
    double MAXRight = Constants.rightOpen;
    double MINLeft = Constants.leftClose;
    double MINRight = Constants.rightClose;

    double positionOne = (MAXLeft - MINLeft) / 2;
    double positionTwo = (MAXRight - MINRight) / 2;


    Servo claw1;

    DcMotor rotateOne;
    DcMotor rotateTwo;

    public GrabberAndrew(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        claw1 = hardwareMap.servo.get("clawOne");

        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        claw1.setPosition(Constants.leftOpen);

    }

    
    public void init() {

    }

    
    public void open1() {
        positionOne += INCREMENT;
        if (positionOne >= MAXLeft) {
            positionOne = MAXLeft;
        }
        claw1.setPosition(positionOne);

    }
    public void wide1(){
        claw1.setPosition(0);
    }

    
    public void open2() {
        positionTwo += INCREMENT;
        if (positionTwo >= MAXRight) {
            positionTwo = MAXRight;
        }


    }

    
    public void close1() {
        positionOne -= INCREMENT;
        if (positionOne <= MINLeft) {
            positionOne = MINLeft;
        }
        claw1.setPosition(positionOne);
     }

    
    public void close2() {
        positionTwo -= INCREMENT;
        if (positionTwo <= MINRight) {
            positionTwo = MINRight;
        }

    }

    
    public void openinst1() {
        claw1.setPosition(MAXLeft);
    }

    


    
    public void closeinst1() {
claw1.setPosition(MINLeft);
    }

    

    
    public void rotate(double degrees) {

    }

    
    public boolean isHolding() {
        return false;
    }

    
    public void rotateOne(double power) {
        rotateOne.setPower(power*.75);
    }

    
    public void rotateTwo(double power) {
        rotateTwo.setPower(power*.75);
    }
}
