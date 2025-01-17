package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Servos;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Movement;


@TeleOp(name = "DriveTeleOp", group = "Linear OpMode")

public class DriveTeleOp extends LinearOpMode {

    public enum RobotState {
        SAMPLE_TRANSFER,
        SAMPLE_COLLECTION,
        ARM_BACK,
        ARM_HANG,
        ARM_RESET,
        SLIDE_UP,
        SLIDE_DOWN,
        FIX_IT,
        HANG2

    }

    RobotState robotState = RobotState.ARM_BACK;

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
            if (gamepad2.b) {
                intoTheDeep.intakeClose();
                servos.servoBasketDrop();
                sleep(100);
            } else {
                servos.servoBasketNormal();
            }


            telemetry.addData("state", robotState);
            switch (robotState) {
                case FIX_IT:
                    motors.armMotor.setPower(0);
                    // basket.slideMotor.setPower(0);
                    servos.servo3.setPosition(0);
                    motors.servo2.setPosition(0);
                    motors.servo1.setPosition(0);
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }

                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = (RobotState.SLIDE_DOWN);
                    }
                    if (gamepad2.a) {
                        robotState = (RobotState.ARM_BACK);

                    }
                    if (gamepad2.back) {
                        robotState = (RobotState.ARM_HANG);
                    }


                    break;
                case SLIDE_DOWN:
                    intoTheDeep.intakeClose();
                    intoTheDeep.closeSlideTouch();
                    // basket.Slide();
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }

                    break;
                case SLIDE_UP:
                    intoTheDeep.intakeClose();

                    intoTheDeep.highBasketSlide();
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }

                    break;
                case ARM_HANG:
                    //  basket.servoBasketHang();
                    intoTheDeep.armHang();
                    servos.disableServo3();
                    movement.teleOpControls();
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.HANG2;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.ARM_RESET;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }

                    break;

                case ARM_BACK:
                    intoTheDeep.intakeClose();
                    intoTheDeep.armBack();
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }

                    break;
                case SAMPLE_TRANSFER:
                    intoTheDeep.closeServoTurn();
                    intoTheDeep.startSlide();
                    //   basket.transferSlide();
                    intoTheDeep.transfer();
                    movement.teleOpControls();

                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.ARM_RESET;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }

                    break;
                case SAMPLE_COLLECTION:
                    intoTheDeep.openServoTurn();
                    intoTheDeep.collection();
                    movement.teleOpControls();

                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.ARM_RESET;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }

                    break;
                case ARM_RESET:
                    intoTheDeep.armReset();
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }

                    break;
                case HANG2:
                    servos.disableServo3();
                    intoTheDeep.intakeClose();
                    sleep(10);
                    intoTheDeep.armBack();
                    movement.teleOpControls();

                    if (gamepad2.y) {
                        robotState = RobotState.SAMPLE_COLLECTION;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.SAMPLE_TRANSFER;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.ARM_BACK;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.SLIDE_UP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_DOWN;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.FIX_IT;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.ARM_HANG;
                    }


                    break;
//                case SERVO_BASKET_DROP:
//                    basket.servoBasketDrop();
//
//
//                    if (gamepad2.y) {
//                        robotState = RobotState.SAMPLE_COLLECTION;
//                    }
//                    if (gamepad2.x) {
//                        robotState = RobotState.SAMPLE_TRANSFER;
//                    }
//                    if (gamepad2.dpad_up) {
//                        robotState = RobotState.SLIDE_UP;
//                    }
//                    if (gamepad2.dpad_down) {
//                        robotState = RobotState.SLIDE_DOWN;
//                    }
//                    if (gamepad2.start) {
//                        robotState = RobotState.FIX_IT;
//                    }
//                    if (gamepad2.a) {
//                        robotState = RobotState.ARM_BACK;
//                    }
//                    if (gamepad2.back) {
//                        robotState = RobotState.ARM_HANG;
//                    }
//
//                    break;
            }


            if (gamepad2.right_bumper) {
                intoTheDeep.closeServoTurn();
            }
            if (gamepad2.left_bumper) {
                intoTheDeep.openServoTurn();
            }
            telemetry.update();
        }
    }
}










