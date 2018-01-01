package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive;

public class JewelsAndrew {

    public ColorSensor color;
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;
    public Servo jewelArm;
    public Servo swing;
    Telemetry telemetry;
    public boolean moveLeft;

    public ColorSensor secondColor;

    public JewelsAndrew(HardwareMap hm, Telemetry tele) {
        telemetry = tele;
        telemetry.addData("status", "init jewels");
        telemetry.update();

        color = hm.colorSensor.get("ballColor");
        jewelArm = hm.servo.get("arm");

        color.enableLed(false);
        color.enableLed(true);

        secondColor = hm.colorSensor.get("leftColor");
        secondColor.setI2cAddress(I2cAddr.create8bit(68));

        swing = hm.servo.get("swing");
        telemetry.addData("status", "finished jewel init");
        telemetry.update();

        reset();
    }

    public void ledState(boolean led) {
        color.enableLed(led);
        secondColor.enableLed(led);
    }

    public void reset() {
        jewelArm.setPosition(.2);
        swing.setPosition(.63);
        ledState(true);

    }

    public void toogleSwing(boolean bool) {
        if (bool) {
            swing.setPosition(.39);
        } else if (!bool) {
            swing.setPosition(.63);
        }
    }

    public void lowerArm() {
        jewelArm.setPosition(.9);

    }

    public void hitBallsOld(Drive drive, boolean color, boolean opposColor) {
        if (color || opposColor) {
            drive.turn(349, 2, .2, .1);
            this.reset();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drive.turn(11, 2, .2, .1);
            // drive.encoderMoveMRGyro(90,.2,.5);
        } else {
            drive.turn(11, 2, .2, .1);
            this.reset();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            drive.turn(349, 2, .2, .1);
            //drive.encoderMoveMRGyro(270,.2,.5);
            moveLeft = true;
        }
    }

    public void hitBalls(boolean color, boolean opposColor) {
        if (color || opposColor) {
            swing.setPosition(.59);
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toogleSwing(true);
        } else {

            swing.setPosition(.28);
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toogleSwing(true);
        }
    }

    public void AHEhitBallsVariablesForBlue(boolean leftSensorblue, boolean leftSensorred, boolean rightSensorblue, boolean rightSensorred) {
        if (leftSensorblue && leftSensorred && rightSensorblue && rightSensorred) {
            swingLeft();
            swingRight();
        } else {
            if (leftSensorblue) {
                if (rightSensorred) {
                    swingRight();
                    try {
                        Thread.sleep(100, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    toogleSwing(true);
                } else {
                    if (leftSensorred) {
                        if (rightSensorred) {
                            swingRight();
                            try {
                                Thread.sleep(100, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            toogleSwing(true);
                        } else {
                            swingLeft();
                            try {
                                Thread.sleep(100, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            toogleSwing(true);
                        }
                    } else {
                    }
                }
            } else {
                if (rightSensorblue) {
                    swingLeft();
                    try {
                        Thread.sleep(100, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    toogleSwing(true);
                } else {
                }
            }
        }
    }

    public void AHEhitBallsVariablesForBlueVersionTwo(boolean leftSensorblue, boolean leftSensorred, boolean rightSensorblue, boolean rightSensorred) {
        if (leftSensorblue && leftSensorred && rightSensorblue && rightSensorred) {          // color sensors all fail
            swingLeft();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            swingRight();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (leftSensorblue && leftSensorred) { //left sensor fails
            if (rightSensorblue) {
                swingLeft();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                swingRight();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (rightSensorblue && rightSensorred) { //right sensor fails
            if (leftSensorblue) {
                swingRight();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                swingLeft();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((rightSensorblue && leftSensorblue) || (leftSensorred && rightSensorred)) { //sensors both working but giving opposite readings
            // do nothing
        } else if (rightSensorblue) { //both sensors are reading
            swingLeft();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            swingRight();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void swingRight() {
        swing.setPosition(.28);
    }

    public void swingLeft() {
        swing.setPosition(.59);
    }

    public boolean isRedRight() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = secondColor.red() >= secondColor.blue();


            return isRed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean isRedLeft() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.red() >= color.blue();


            return isRed;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean isBlueRight() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = secondColor.red() >= secondColor.blue();


            return isRed;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean isBlueLeft() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean isRed = color.blue() >= color.red();


            return isRed;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean second_color_sensor_the_ball_is_seen_as_red() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean a = secondColor.red() >= secondColor.blue();


            return a;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean first_color_sensor_the_ball_is_seen_as_red() {

        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean b = color.red() >= color.blue();


            return b;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean second_color_sensor_the_ball_is_seen_as_blue() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean c = secondColor.blue() >= secondColor.red();

            return c;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }

    public boolean first_color_sensor_the_ball_is_seen_as_blue() {
        try {

            Thread.sleep(10); //Ensure LED is enabled

            boolean d = color.blue() >= color.red();

            return d;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false; //Should never happen

    }
}