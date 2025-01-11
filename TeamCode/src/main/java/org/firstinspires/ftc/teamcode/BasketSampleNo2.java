package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "BasketPark", group = "Linear OpMode")
public class BasketSampleNo2 extends LinearOpMode {


    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);
        Basket basket = new Basket(this);
        movement.init();
        armServos.init();
        basket.init();
        armServos.closeServoTurn();
        armServos.intakeClose();
        basket.servoBasketNormal();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // wait for start
        waitForStart();
        basket.servoBasketNormal();
        armServos.closeServoTurn();
        movement.backwardDistance(.5, 150);
        movement.strafeRightDistance(.5, 800);
        basket.SlidePID();
        basket.servoBasketNormal();
        sleep(1000);
        movement.turnLeft(.2, 90);
        sleep(300);
        basket.servoBasketDrop();
        sleep(1500);
        basket.servoBasketNormal();
        basket.closeSlideTouch();
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



