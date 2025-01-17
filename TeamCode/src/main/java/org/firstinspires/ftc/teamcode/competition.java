package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;


@TeleOp(name = "competition ", group = "Linear OpMode")

public class competition extends LinearOpMode {

    public enum RobotState {
        SLIDE_DOWN,
        COLLECTION,
        ARM_UP,
        ARM_DOWN,
        SLIDE_UP,
        INTAKE_DUMP,
        INTAKE_IN
    }

    competition.RobotState robotState = RobotState.INTAKE_IN;

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Basket basket = new Basket(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);

        movement.init();
        basket.init();
        motors.init();
        intoTheDeep.init();


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


            movement.teleOpControls();
            intoTheDeep.teleOpIntakeControls();


            telemetry.addData("state", robotState);
            switch (robotState) {
                case INTAKE_IN:
                    break;
                case SLIDE_UP:
                    intoTheDeep.highBasketSlide();
                    break;


            }
        }
    }
}


