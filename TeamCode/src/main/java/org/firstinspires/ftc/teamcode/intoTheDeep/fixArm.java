package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Movement;


@TeleOp(name = "littleBot ", group = "Linear OpMode")

public class fixArm extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        //Motors motors = new Motors(this);
        // Servos servos = new Servos(this);
        //  IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        //  Sensors sensors = new Sensors(this);
        movement.init();
        // servos.init();
        //  motors.init();
        // intoTheDeep.init();
        // sensors.init();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        while (opModeIsActive()) {
            double slow = 1;
            movement.frontLeft.setPower((-gamepad1.left_stick_y) / slow);
            movement.frontRight.setPower((-gamepad1.right_stick_y) / slow);
            movement.frontRight.setPower((gamepad1.right_stick_x) / slow);
            movement.frontLeft.setPower((-gamepad1.left_stick_x) / slow);
            if (gamepad1.a) {
                double slow2 = 2;
                movement.frontLeft.setPower((gamepad1.left_stick_y) / slow2);
                movement.frontRight.setPower((gamepad1.left_stick_y) / slow2);
                movement.frontRight.setPower((-gamepad1.left_stick_x) / slow2);
                movement.frontLeft.setPower((gamepad1.left_stick_x) / slow2);
            }
        }
    }
}