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
        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");
        Servo bucket = hardwareMap.get(Servo.class, "Bucket");

        Pose2d startPose = new Pose2d(14, -56, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        waitForStart();

        TrajectorySequence trajSeqRIGHT = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(1, ()-> {
                    arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bucket.setPosition(0);
                })
                .forward(22)
                .turn(Math.toRadians(90))
                .forward(52)
                .turn(Math.toRadians(45))
                .forward(29)
                .turn(Math.toRadians(10))
                .addTemporalMarker(8, ()-> {
                    arm.setPower(1);
                    arm.setTargetPosition(12500);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .forward(2)
                .addTemporalMarker(14, ()-> {
                    bucket.setPosition(.3);
                })
                .addTemporalMarker(16, ()-> {
                    arm.setPower(1);
                    arm.setTargetPosition(0);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(5)
                .back(29)
                .turn(Math.toRadians(-55))
                .back(100)
                .strafeLeft(50)
                .build();
        drive.followTrajectorySequence(trajSeqRIGHT);
    }
}