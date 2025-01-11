
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "BasketSample", group = "Linear OpMode")
public class BasketSample extends LinearOpMode {


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
        waitForStart();
        //basket drop
        basket.servoBasketNormal();
        armServos.closeServoTurn();
        movement.backwardDistance(.5, 150);
        movement.strafeRightDistance(.5, 800);
        basket.SlidePID();
        basket.servoBasketNormal();
        sleep(1000);
        movement.turnLeft(.2, 100);
        sleep(300);
        basket.servoBasketDrop();
        sleep(1000);
        basket.servoBasketNormal();
        basket.closeSlideTouch();
        sleep(1000);
        movement.forwardDistance(.3, 500);


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













