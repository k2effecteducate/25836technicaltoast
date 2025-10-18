package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous
public class TestAuto extends LinearOpMode {

    Movement movement = new Movement(this);

    @Override
    public void runOpMode() {
        movement.init();
        waitForStart();
        movement.forwardDistance(0.5, 18);

    }
}
