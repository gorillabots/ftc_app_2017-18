package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;

/**
 * Created by Jarred on 12/22/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="ColorCheck", group="Backup")
public class ColorTest extends OpMode {
    JewelsAndrew jewel;

    @Override
    public void init(){
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        jewel.reset();

    }

    @Override
    public void loop(){

        telemetry.addData("Red? ", jewel.isRed());
        telemetry.addData("BLue?", jewel.isBlue());
        telemetry.addData("Red #", jewel.color.red());
        telemetry.addData("Blue #", jewel.color.blue());
        telemetry.update();
    }
}
