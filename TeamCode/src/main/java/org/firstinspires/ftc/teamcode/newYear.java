package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "newYear ", group = "Linear OpMode")

public class newYear extends LinearOpMode {
    // this is how you start and initialize a state machine
    public enum RobotState {
        COLLECTION_A, SHOOT, SHOOT_REST, SPIT_OUT_B, DISABLE, LINE_UP
    }

    // this is where you tell it the first state class you want it to start at.
// In this case (ha ha see what I did there) you will want to change the word DISABLE
    RobotState robotState = RobotState.DISABLE;


    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        // all of these are for the movement of your robot they are private
        // libraries that you have built to make your robot work.
        // All of the names for each motor, servo, and sensor are in these files.
        // Please be careful about what you clean up and change as it's useful to see the solutions to previous problems.
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        movement.init();
        servos.init();
        motors.init();


        //the next two lines are essential
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("state", robotState);
            // these are the OG (original) controls for your robot for the chassis but hopefully you already knew that. ;)
            movement.teleOpControls();


            switch (robotState) {

                case SHOOT:

                    //Fill this

                    if (gamepad1.a) {

                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.b) {

                        robotState = RobotState.DISABLE;
                    }
                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }


                    break;


                case COLLECTION_A:

                    // and this

                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.b) {

                        robotState = RobotState.DISABLE;
                    }


                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }


                    break;

                case SHOOT_REST:
                    // this too
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }
                    break;
                case SPIT_OUT_B:
                    // maybe this
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }


                    if (gamepad1.b) {
                        robotState = RobotState.DISABLE;
                    }
                    //make sure every case has a break or it will "slip"
                    // between the last command and the new case unable to preform any tasks
                    // and is hard to tell if missing so always add it.
                    // Trust me it will help with trouble shooting
                    break;
                case DISABLE:
                    // this may need to be deleted


                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    if (gamepad1.start) {
                        robotState = RobotState.LINE_UP;
                    }
                    break;
                case LINE_UP:
                    // (explanation of what to put inside these spaces)in this space you write whatever you want to happen. Be cautious about how you use loops because the case is a loop in itself.
                    if (gamepad1.a) {
                        // this tells the code where to go if you press gamepad.a 1
                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }

                    if (gamepad1.b) {
                        robotState = RobotState.DISABLE;
                    }


                    break;
            }

        }
        telemetry.update();
    }
}



