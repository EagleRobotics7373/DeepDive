package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(120), Math.toRadians(120), 16)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(14, -56, Math.toRadians(90)))
                        .forward(20)
                        .turn(Math.toRadians(90))
                        .forward(53)
                        .turn(Math.toRadians(45))
                        .forward(25)
                        .waitSeconds(3)
                        .back(13)
                        .turn(Math.toRadians(45))
                        .back(5)
                        .waitSeconds(5)
                        .forward(5)
                        .turn(Math.toRadians(-45))
                        .forward(13)
                        .waitSeconds(5)
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}