package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-10-24

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArbitraryDirectionDrive
{
    LinearOpMode opmode;
    DcMotor m1, m2, m3, m4;
    double backLeftPower = 0;
    double frontRightPower = 0;
    double backRightPower = 0;
    double frontLeftPower = 0;
    Telemetry telemetry;
    HardwareMap hardwareMap;
    //add.drive(Direction.N, .5, 10);

    public ArbitraryDirectionDrive(HardwareMap hMap, Telemetry telemetry)
    {
        
        this.telemetry = telemetry;
        this.hardwareMap = hMap;
        init();
    }

    public void init(){

        m1=hardwareMap.dcMotor.get("m1");
        m2=hardwareMap.dcMotor.get("m2");
        m3=hardwareMap.dcMotor.get("m3");
        m4=hardwareMap.dcMotor.get("m4");

    }

    public void drive(double speed, double direction) //No rotation fixing from gyro data!
    {
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg);
        double m13, m24; //Amount to actually drive


        //Apply power to motors;





        m13 = limitToOne(Math.sin(facingRad) * speed);
        m24 = limitToOne(Math.cos(facingRad) * speed);

        telemetry.addData("speed", speed);
        telemetry.addData("direction",direction);
        telemetry.addData("m13", m13);
        telemetry.addData("m24",m24);
        telemetry.update();

        m1.setPower((m13));
        m2.setPower((m24));
        m3.setPower((-m13));
        m4.setPower((-m24));

    }

    public void drivePolar(double speed, double direction) //No rotation fixing from gyro data!
    {
        double yComp =  speed * Math.sin(Math.toRadians(direction));
        double xComp = speed * Math.cos(Math.toRadians(direction));
        driveCartesian(xComp,yComp,0);

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

    public void driveCartesian(double stickX, double stickY, float stickRot){
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg); // Convert to radians

        double cs = Math.cos(facingRad);
        double sn = Math.sin(facingRad);

        double headX = stickX * cs - stickY * sn; //Rotated vector (Relative heading)
        double headY = stickX * sn + stickY * cs; //Each is in range -root 2 to root 2

        headX /= Math.sqrt(2); //In range -1 to 1
        headY /= Math.sqrt(2);

        telemetry.addData("absHead", "(" + stickX + ", " + stickY + ")");

        telemetry.addData("relHead", "(" + headX + ", " + headY + ")");

        double backLeftPower = limitToOne(-headX + stickRot);
        double frontRightPower = limitToOne(headX + stickRot);
        double backRightPower = limitToOne(headY + stickRot);
        double frontLeftPower = limitToOne(-headY + stickRot);

        m4.setPower(backLeftPower);
        m2.setPower(frontRightPower);
        m3.setPower(backRightPower);
        m1.setPower(frontLeftPower);
    }


    public double limitToOne(double in)
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




