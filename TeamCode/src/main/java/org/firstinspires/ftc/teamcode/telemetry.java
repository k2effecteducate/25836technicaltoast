package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Sensors;


@TeleOp(name = "telemetry", group = "Linear OpMode")

public class telemetry extends LinearOpMode {


    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        // Movement movement = new Movement(this);
        //  Motors motors = new Motors(this);
        // Servos servos = new Servos(this);
        //  IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        Sensors sensors = new Sensors(this);

        //  movement.init();
        //  servos.init();
        //  motors.init();
        // intoTheDeep.init();
        sensors.init();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {
            //   telemetry.addData("servo3", basket.servo3.());
//            telemetry.addData("on", "on");
            //     telemetry.addData("touchSensor", motors.slideTouch.getState());
//            telemetry.addData("gamepad.2,y", gamepad2.y);
//            telemetry.addData("d-pad_up", gamepad2.dpad_up);
//            telemetry.addData("d-pad_down", gamepad2.dpad_down);
//            telemetry.addData("d-pad_left", gamepad2.dpad_left);
//            telemetry.addData("d-pad_right", gamepad2.dpad_right);
//            telemetry.addData("slide", motors.slideMotor.getCurrentPosition());
//            telemetry.addData("armMotor", motors.armMotor.getCurrentPosition());
//            telemetry.addData("armStick", gamepad2.right_stick_y);
            telemetry.addData("Distance", sensors.isObjectDetected());
            telemetry.update();
        }
    }
}










