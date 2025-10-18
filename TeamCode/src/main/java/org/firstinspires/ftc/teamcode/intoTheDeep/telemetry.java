package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "telemetry", group = "Linear OpMode")

public class telemetry extends LinearOpMode {


    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        // Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        //  IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        Sensors sensors = new Sensors(this);

        //  movement.init();
        servos.init();
        motors.init();
        // intoTheDeep.init();
        sensors.init();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("on", "on");
            //  telemetry.addData("touchSensor", motors.slideTouch1.getState());
            telemetry.addData("gamepad.2,y", gamepad2.y);
            telemetry.addData("d-pad_up", gamepad2.dpad_up);
            telemetry.addData("d-pad_down", gamepad2.dpad_down);
            telemetry.addData("d-pad_left", gamepad2.dpad_left);
            telemetry.addData("d-pad_right", gamepad2.dpad_right);
            //   telemetry.addData("slide", motors.slideMotor1.getCurrentPosition());
            //  telemetry.addData("armMotor", motors.armMotor.getCurrentPosition());
            telemetry.addData("Distance", sensors.isObjectDetected());
            telemetry.addData("gamepad.2,x", gamepad2.x);
            telemetry.addData("gamepad.2,b", gamepad2.b);
            telemetry.addData("gamepad.2,a", gamepad2.a);
            //   telemetry.addData("gamepad.y Servo", servos.servo1.setPower(0));
            telemetry.addData("voltage of Ranger", sensors.distanceSensor.getVoltage());
            telemetry.update();
        }
    }
}










