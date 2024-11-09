package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "simplerTheBetter", group="Linear OpMode")
public class simplerTheBetter extends LinearOpMode {

    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // Don't delete the two lines below
        movement.init();
        armServos.init();
        waitForStart();


        //Don't delete the 3 lines below don't know what it is might be important
//        backward(.4,100);
//        strafeLeft(.4,3100);
//        strafeRight(.4,6000);
        movement.forwardDistance(.5, 500);
        movement.stopMotors();
        movement.strafeLeft(.5, 500);
        armServos.stopMotors();
        armServos.arm(1,500);


        telemetry.addData("Status", "Running");
        telemetry.update();

    }
}





