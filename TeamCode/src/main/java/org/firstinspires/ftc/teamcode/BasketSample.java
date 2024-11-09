
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous (name = "BasketSample", group="Linear OpMode")
public class BasketSample extends LinearOpMode {


    @Override
    public void runOpMode() {

        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);

        movement.init();
        armServos.init();

        armServos.closeServoTurn();
        armServos.intakeClose();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        waitForStart();
        movement.forwardDistance(.4,150);
        armServos.openServoTurn();
        armServos.intakeClose();
        movement.strafeLeft(.4,900);
        sleep(100);
        movement.turnLeft(.5,1300);
        movement.stopMotors();
        armServos.openServoTurn();
        armServos.arm(.8,1000);









        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













