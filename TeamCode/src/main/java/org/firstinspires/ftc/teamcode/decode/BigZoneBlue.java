package org.firstinspires.ftc.teamcode.decode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class BigZoneBlue extends LinearOpMode {
    public static double DISTANCE = 35;
    private ElapsedTime runtime = new ElapsedTime();
    decode decode = new decode(this);

    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);
        decode.init();
        waitForStart();
        follower.activateAllPIDFs();

        Path forwards = new Path(new BezierLine(new Pose(0, 0), new Pose(DISTANCE, 0)));
        forwards.setConstantHeadingInterpolation(0);

        Path strafeLeft = new Path(new BezierLine(new Pose(DISTANCE, 0), new Pose(DISTANCE, -20)));
        strafeLeft.setConstantHeadingInterpolation(0);


        follower.followPath(forwards);
        while (opModeIsActive() && follower.isBusy()) {
            follower.update();
        }


        runtime.reset();
        decode.autoShoot();
        sleep(1000);
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            decode.servo4.setPosition(.7);
            sleep(1000);
            decode.servo4.setPosition(0);
            sleep(1000);
        }
        decode.ShootStop();
        sleep(500);
        decode.collection();
        sleep(2000);
        decode.collectionRest();
        decode.autoShoot();
        sleep(1000);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            decode.servo4.setPosition(.7);
            sleep(1000);
            decode.servo4.setPosition(0);
            sleep(1000);
        }
        decode.collection();
        sleep(2000);
        decode.collectionRest();
        decode.autoShoot();
        sleep(1000);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            decode.servo4.setPosition(.7);
            sleep(1000);
            decode.servo4.setPosition(0);
            sleep(1000);
        }
        follower.followPath(strafeLeft);

        while (opModeIsActive() && follower.isBusy()) {
            follower.update();

        }

    }
}

