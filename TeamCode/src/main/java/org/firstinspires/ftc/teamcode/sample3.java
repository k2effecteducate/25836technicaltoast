package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Servos;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "sample3", group = "Linear OpMode")
public class sample3 extends LinearOpMode {


    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        movement.init();
        motors.init();
        servos.init();
        intoTheDeep.init();
        intoTheDeep.slideInTouch();
        telemetry.addData("Init", "It is ON!");
        telemetry.update();
        // wait for start
        waitForStart();
        //change values for the 3 below
        movement.forwardDistance(.5, 150);
        movement.turnLeftDistance(.5, 500);
//        movement.forwardDistance(.5, 200);
//        intoTheDeep.armUp();
//        intoTheDeep.slidePIDUp();
//        intoTheDeep.intakeOpen();
//        // change values for the 1 below
//        movement.forwardDistance(.3, 200);
//        intoTheDeep.servo2SpinClockwiseSlow();
//        intoTheDeep.intakeOpen();
//        // change values for the 1 below
//        movement.backwardDistance(.3, 200);
//        intoTheDeep.slideInTouch();
//        intoTheDeep.straitArm();
//        // change values for the 1 below
//        movement.turnLeftDistance(.3, -200);
//        intoTheDeep.slidePIDOut();
//        intoTheDeep.intakeCollect();


    }
}
