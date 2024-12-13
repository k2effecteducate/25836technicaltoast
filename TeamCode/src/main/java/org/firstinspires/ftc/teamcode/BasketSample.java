
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
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        waitForStart();
        //basket drop
        armServos.closeServoTurn();
        movement.backwardDistance(.5, 150);
        movement.strafeRightDistance(.5, 795);
        movement.stopMotors();
        basket.slideAuto(.5,1750);
        movement.turnRight(-.3,500);
        movement.stopMotors();
        basket.slideMotor.setPower(.2);
        basket.servoBasketDrop();
        sleep(1000);
        basket.servoBasketNormal();
        sleep(500);
        movement.turnLeft(-.3,370);
       movement.strafeLeftDistance(.3,3000);
       movement.stopMotors();
        basket.slideTeleOp(.7, -1500);
       movement.forwardDistance(.2,200);
//
//


        // This is old Low Basket code
//        movement.forwardDistance(.4, 150);
//        armServos.openServoTurn();
//        armServos.intakeClose();
//        movement.strafeLeft(.4, 900);
//        sleep(100);
//        movement.turnLeft(.5, 1350);
//        movement.stopMotors();
//        armServos.openServoTurn();
//        armServos.arm(.8, 1500);
//        armServos.stopMotors();
//        armServos.servo2.setPosition(0);
//        sleep(1000);
//        armServos.intakeOpen();
//        sleep(1100);
//        // new add on
//        armServos.arm(-.5, 1000);
//        armServos.intakeClose();
//        movement.turnRight(.4, 1700);
//        movement.stopMotors();
//        //line below time was 600
//        movement.strafeRight(.3, 800);
//        movement.forwardDistance(.3, 1350);
//        movement.strafeRight(.3, 800);
//        movement.stopMotors();
//        movement.turnRight(.4, 1300);
//        movement.stopMotors();
        // movement.forwardDistance(.3,350);
        // armServos.arm(.8,1500);
//        armServos.setArmPosition(.4, 1800, 0);


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













