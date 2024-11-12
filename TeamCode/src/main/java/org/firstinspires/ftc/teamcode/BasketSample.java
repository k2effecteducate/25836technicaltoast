
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous(name = "BasketSample", group = "Linear OpMode")
public class BasketSample extends LinearOpMode {
    // private DcMotor armMotor;


    @Override
    public void runOpMode() {
        // armMotor = hardwareMap.get(DcMotor.class,"armMotor");
        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);

        movement.init();
        armServos.init();

        armServos.closeServoTurn();
        armServos.intakeClose();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        waitForStart();
        //basket drop
        movement.forwardDistance(.4, 150);
        armServos.openServoTurn();
        armServos.intakeClose();
        movement.strafeLeft(.4, 900);
        sleep(100);
        movement.turnLeft(.5, 1350);
        movement.stopMotors();
        armServos.openServoTurn();
        armServos.arm(.8, 1500);
        armServos.stopMotors();
        armServos.servo2.setPosition(0);
        sleep(1000);
        armServos.intakeOpen();
        sleep(1100);
        // new add on
        armServos.arm(-.5, 1000);
        armServos.intakeClose();
        movement.turnRight(.4, 1700);
        movement.stopMotors();
        movement.strafeRight(.4, 600);
        movement.forwardDistance(.4, 1230);
        movement.strafeRight(.4, 800);
        movement.turnRight(.4, 500);
        movement.stopMotors();
        // armServos.arm(.8,1500);
        armServos.setArmPosition(.4, 1500, 0);


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













