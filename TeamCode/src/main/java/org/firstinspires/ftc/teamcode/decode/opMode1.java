package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "forward", group = "Linear OpMode")

public class opMode1 extends LinearOpMode {

//    public enum RobotState {
//        COLLECTION_Y, ARM_UP, INTAKE_DUMP_LEFT, TURN, SLIDE_IN_A, SLIDE_OUT_Y2, INTAKE_OUT_RIGHT, INTAKE_RESET, SPIT_OUT_B, DISABLE, COLLECT_START
//    }

    // opMode1.RobotState robotState = RobotState.TURN;

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        Sensors sensors = new Sensors(this);
        movement.init();
        servos.init();
        motors.init();
        sensors.init();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("servo2", servos.servo1.getPosition());
            // telemetry.addData("state", robotState);
            movement.teleOpControls();

            if (gamepad1.a) {
                movement.turnRight(.5, 200);
            } else {
                movement.teleOpControls();
            }


        }
        telemetry.update();
    }
}



