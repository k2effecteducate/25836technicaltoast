package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Servos;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "BasketPark", group = "Linear OpMode")
public class BasketSampleNo2 extends LinearOpMode {


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
        intoTheDeep.closeServoTurn();
        intoTheDeep.intakeClose();
        servos.servoBasketNormal();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // wait for start
        waitForStart();
        servos.servoBasketNormal();
        intoTheDeep.closeServoTurn();
        movement.backwardDistance(.5, 150);
        movement.strafeRightDistance(.5, 800);
        intoTheDeep.SlidePID();
        servos.servoBasketNormal();
        sleep(1000);
        movement.turnLeft(.2, 90);
        sleep(300);
        servos.servoBasketDrop();
        sleep(1500);
        servos.servoBasketNormal();
        intoTheDeep.closeSlideTouch();
        sleep(1000);
        movement.forwardDistance(.3, 1000);
        sleep(9000);
        movement.strafeRightDistance(.3, 200);
        sleep(9000);
        movement.forwardDistance(.3, 1000);
        sleep(9000);
        movement.strafeRightDistance(.3, 200);
        sleep(1000);

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}



