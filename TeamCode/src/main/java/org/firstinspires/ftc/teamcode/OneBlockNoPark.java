package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="OneBlockNoPark", preselectTeleOp= "TeleOpMeet1")
public class OneBlockNoPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        DcMotor arm = hardwareMap.get(DcMotor.class, "Lift");
        Servo bucket = hardwareMap.get(Servo.class, "Bucket");
        Servo rotate = hardwareMap.get(Servo.class, "Rotate");
        Servo grab = hardwareMap.get(Servo.class, "Grab");
        DcMotor extend = hardwareMap.get(DcMotor.class, "Extend");

        Pose2d startPose = new Pose2d(14, -56, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        waitForStart();

        TrajectorySequence trajSeqRIGHT = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(0, ()-> {
                    arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rotate.setPosition(.5);
                })
                .forward(5)
                .turn(Math.toRadians(-105))
//                .forward(51) original forward, was working
                .forward(5)
//                .turn(Math.toRadians(-57)) original rotation
                .turn(Math.toRadians(-51))
                .waitSeconds(3)
                .addTemporalMarker(7, ()-> {
                    rotate.setPosition(.5);
                    arm.setTargetPosition(3100);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setPower(1);
                })
                .addTemporalMarker(9.5, ()-> {
                    bucket.setPosition(0);
                })
                .back(16)
                .addTemporalMarker(11, ()-> {
                    bucket.setPosition(.55                              );
                    arm.setPower(1);
                    arm.setTargetPosition(0);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
//                .turn(Math.toRadians(-53))
//                .back(10)
//                .waitSeconds(3)
//                .addTemporalMarker(15, ()-> {
////                    extend.setTargetPosition(120);
////                    extend.setPower(1);
////                    extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    grab.setPosition(.25);
//                    rotate.setPosition(.75);
//                })
//                .addTemporalMarker(16, ()-> {
//                    grab.setPosition(.45);
//                })
//                .addTemporalMarker(18, ()-> {
////                    extend.setTargetPosition(0);
////                    extend.setPower(1);
////                    extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    rotate.setPosition(.2);
//                })
//                .addTemporalMarker(18.6, ()-> {
//                    grab.setPosition(.25);
//                })
//                .forward(10)
//                .turn(Math.toRadians(60))
//                .forward(15)
//                .addTemporalMarker(21, ()-> {
//                    rotate.setPosition(.65);
//                    arm.setPower(1);
//                    arm.setTargetPosition(3100);
//                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                })
//                .addTemporalMarker(23, ()-> {
//                    bucket.setPosition(0);
//                })
//                .addTemporalMarker(25, ()-> {
//                    bucket.setPosition(.55);
//                    arm.setPower(1);
//                    arm.setTargetPosition(-200);
//                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                })
                .waitSeconds(5)
                .build();
        drive.followTrajectorySequence(trajSeqRIGHT);
    }
}