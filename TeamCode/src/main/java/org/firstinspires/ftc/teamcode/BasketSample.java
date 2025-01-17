
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Servos;

@Autonomous(name = "BasketSample", group = "Linear OpMode")
public class BasketSample extends LinearOpMode {


    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        movement.init();
        motors.init();
        servos.init();
        intoTheDeep.closeServoTurn();
        intoTheDeep.intakeClose();
        servos.servoBasketNormal();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        waitForStart();
        //basket drop
        servos.servoBasketNormal();
        intoTheDeep.closeServoTurn();
        movement.backwardDistance(.5, 150);
        movement.strafeRightDistance(.5, 800);
        intoTheDeep.SlidePID();
        servos.servoBasketNormal();
        sleep(1000);
        movement.turnLeft(.2, 100);
        sleep(300);
        servos.servoBasketDrop();
        sleep(1000);
        servos.servoBasketNormal();
        intoTheDeep.closeSlideTouch();
        sleep(1000);
        movement.forwardDistance(.3, 500);


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













