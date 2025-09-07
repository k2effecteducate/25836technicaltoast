package org.firstinspires.ftc.teamcode.decode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class Line extends LinearOpMode {
    public static double DISTANCE = 40;


    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);
        waitForStart();
        follower.activateAllPIDFs();

        Path forwards = new Path(new BezierLine(new Pose(0, 0), new Pose(DISTANCE, 0)));
        forwards.setConstantHeadingInterpolation(0);

        Path backwards = new Path(new BezierLine(new Pose(DISTANCE, 0), new Pose(0, 0)));
        backwards.setConstantHeadingInterpolation(0);


        follower.followPath(forwards);
        while (opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
        follower.followPath(backwards);

        while (opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
    }

}
