
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous (name = "Specimin", group="Linear OpMode")
public class Specimen extends LinearOpMode {




    @Override
    public void runOpMode() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");

        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);

        //servo3 = hardwareMap.get(Servo.class, "servo3");
        //  servo2.setDirection(Servo.Direction.REVERSE);
        movement.init();
        armServos.init();

        armServos.closeServoTurn();
        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        waitForStart();

        armServos.intakeOpen();
        movement.forward(.4, 970);
        movement.stopMotors();
        armServos.arm(.89, 860);
        // armMotor.setPower(0);
        // arm(-.3,200);
       armServos.arm(0,0);
        //servo2.setPosition(-.7);
       armServos.intakeOpen();
        sleep(1000);
        movement.forward(.35, 500);
        sleep(200);
       armServos.closeServoTurn();
        movement.backward(.4, 100);
        armServos.arm(-.4, 800);
       movement.backward(.1, 100);
        armServos.arm(-.2, 300);
        movement.forward(.2, 100);
        movement.strafeRight(.38, 3000);

        // backward(.2, 100);


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}













