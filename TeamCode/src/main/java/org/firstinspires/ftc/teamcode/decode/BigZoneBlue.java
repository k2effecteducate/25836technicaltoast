package org.firstinspires.ftc.teamcode.decode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous
public class BigZoneBlue extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    decode decode = new decode(this);
    Movement movement = new Movement(this);
    Motors motors = new Motors(this);


    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);


        decode.init();
        movement.init();
        motors.init();
        runtime.reset();
        waitForStart();
        follower.activateAllPIDFs();
        decode.resetOdometry();
        int firstForward = -12;
        int secondForward = -20;

        decode.collection();
        sleep(100);
        decode.resetOdometry();
        movement.odemetryForward(0, 0, firstForward, 0, 0);
        decode.autoShooting();
        decode.autoShooting();
        motors.motor2.setPower(-.7);
        sleep(100);
        decode.autoShooting();
        decode.autoShooting();
        movement.odemetryForward(firstForward, 0, secondForward, 0, 0);
        sleep(100);
        movement.turnLeft(-.4, 300);
        sleep(100);
        movement.forward(.4, 800);
//        decode.collection();
//        sleep(100);
//        decode.collection();
//        sleep(2000);
//        movement.odemetryForward(-40, 0, -20, 20, 45);
//        sleep(100);
//        decode.collection();
//        sleep(2000);


    }
}


