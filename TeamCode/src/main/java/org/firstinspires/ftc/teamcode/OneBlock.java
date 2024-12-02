package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
@Autonomous(name="OneBlock", preselectTeleOp= "TeleOpMeet1")
public class OneBlock extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//        Servo bucket = hardwareMap.get(Servo.class, "Bucket");
//        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");

        Pose2d startPose = new Pose2d(14, -56, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        waitForStart();

        TrajectorySequence trajSeqRIGHT = drive.trajectorySequenceBuilder(startPose)
                .forward(22)
                .strafeLeft(70)
                .turn(Math.toRadians(135))
                .lineToConstantHeading(new Vector2d(-53, -51))
//                .addTemporalMarker(() -> arm.setMode(DcMotor.RunMode.RUN_TO_POSITION))
//                .addTemporalMarker(() -> arm.setTargetPosition(12250))
//                .addTemporalMarker(() -> arm.setPower(1))
                .build();
        drive.followTrajectorySequence(trajSeqRIGHT);
    }
}