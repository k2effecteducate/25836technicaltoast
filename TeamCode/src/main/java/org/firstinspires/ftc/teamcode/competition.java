package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "competition ", group = "Linear OpMode")

public class competition extends LinearOpMode {

    public enum RobotState {
        SLIDE_DOWN, COLLECTION, ARM_UP, ARM_DOWN, SLIDE_UP, INTAKE_DUMP, INTAKE_IN, SLIDE_IN, SLIDE_OUT
    }

    competition.RobotState robotState = RobotState.INTAKE_IN;

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);

        movement.init();
        servos.init();
        motors.init();
        intoTheDeep.init();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("servo2", servos.servo1.getPosition());
            telemetry.addData("state", robotState);
            movement.teleOpControls();
            switch (robotState) {
                case INTAKE_IN:
                    intoTheDeep.intakeClose();
                    intoTheDeep.straitArm();

                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.ARM_DOWN;
                    }

                    break;

                case SLIDE_IN:
                    intoTheDeep.slideInTouch();
                    intoTheDeep.straitArm();
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.left_bumper) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.COLLECTION;
                    }

                    break;

                case SLIDE_OUT:
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.straitArm();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.left_bumper) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.ARM_DOWN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.COLLECTION;
                    }
                    break;
                case COLLECTION:
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.sensorCollect();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.left_bumper) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.ARM_DOWN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }

                    break;
                case ARM_UP:
                    intoTheDeep.armUp();

                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.left_bumper) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.ARM_DOWN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.COLLECTION;
                    }
                    break;
                case SLIDE_UP:
                    intoTheDeep.armUp();
                    intoTheDeep.slidePIDOut();

                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.left_bumper) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.ARM_DOWN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.COLLECTION;
                    }

                    break;

            }
        }
        telemetry.update();
    }
}


