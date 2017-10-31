package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-10-24

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ArbitraryDirectionDrive
{
    LinearOpMode opmode;
    DcMotor m1, m2, m3, m4;

    //add.drive(Direction.N, .5, 10);

    public ArbitraryDirectionDrive(LinearOpMode opmode)
    {
        this.opmode = opmode;
    }

    public void init(){

        m1=opmode.hardwareMap.dcMotor.get("m1");
        m2=opmode.hardwareMap.dcMotor.get("m2");
        m3=opmode.hardwareMap.dcMotor.get("m3");
        m4=opmode.hardwareMap.dcMotor.get("m4");

    }

    public void drive(int direction, double speed) //No rotation fixing from gyro data!
    {
        double rad = Math.toRadians(direction + 45);

        int m1n, m2n, m3n, m4n; //New encoder pos, updated at beginning of loop
        int m1l = m1.getCurrentPosition(), m2l = m2.getCurrentPosition(), //Last encoder pos, updated at end of loop
                m3l = m3.getCurrentPosition(), m4l = m4.getCurrentPosition();
        int m1d, m2d, m3d, m4d; //Encoder pos change since last loop
        double m13a, m24a; //Average of two related encoder positions
        double length = 0; //Magnitude of above vector
        double m13, m24; //Amount to actually drive


        //Apply power to motors;

        m13 = Math.sin(rad) * speed;
        m24 = Math.cos(rad) * speed;

        m1.setPower(m13);
        m2.setPower(m24);
        m3.setPower(m13);
        m4.setPower(m24);

    }

    public boolean distanceCheck(double magnitude) {

        int m1n, m2n, m3n, m4n; //New encoder pos, updated at beginning of loop
        int m1l = m1.getCurrentPosition(), m2l = m2.getCurrentPosition(), //Last encoder pos, updated at end of loop
                m3l = m3.getCurrentPosition(), m4l = m4.getCurrentPosition();
        int m1d, m2d, m3d, m4d; //Encoder pos change since last loop
        double m13a, m24a; //Average of two related encoder positions
        double length = 0; //Magnitude of above vector
        double m13, m24; //Amount to actually drive
        //Update new encoder pos
        m1n = m1.getCurrentPosition();
        m2n = m2.getCurrentPosition();
        m3n = m3.getCurrentPosition();
        m4n = m4.getCurrentPosition();

        //Update encoder pos delta
        m1d = m1n - m1l;
        m2d = m2n - m2l;
        m3d = m3n - m3l;
        m4d = m4n - m4l;

        m1l = m1n;
        m2l = m2n;
        m3l = m3n;
        m4l = m4n;

        m13a = (m1d + m3d) / 2d; //Average of m1 and m3
        m24a = (m2d + m4d) / 2d;

        length = Math.sqrt(m13a * m13a + m24a * m24a);

        if (length >= magnitude) {
            return false;
        } else {
            return true;
        }
    }

    public void stopMotors(){
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
    public void oneStickLoop(float stickX, float stickY, float stickRot) //TODO: Fix multiple possible memory leaks
    {


         // Convert to radians





        double backLeftPower = limitToOne(-stickX + stickRot);
        double frontRightPower = limitToOne(stickX + stickRot);
        double backRightPower = limitToOne(stickY + stickRot);
        double frontLeftPower = limitToOne(-stickY + stickRot);

        m2.setPower(backLeftPower);
        m4.setPower(frontRightPower);
        m3.setPower(backRightPower);
        m1.setPower(frontLeftPower);
    }

    double limitToOne(double in)
    {
        if(in < -1)
        {
            return -1;
        }
        if(in > 1)
        {
            return 1;
        }

        return in;
    }
}




