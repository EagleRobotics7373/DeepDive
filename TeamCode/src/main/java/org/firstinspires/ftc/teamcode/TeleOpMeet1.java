package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class TeleOpMeet1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor rightRearMotor = hardwareMap.get(DcMotor.class, "rightRear");
        DcMotor leftRearMotor = hardwareMap.get(DcMotor.class, "leftRear");
        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");
        DcMotor claw = hardwareMap.get(DcMotor.class, "Claw");
        Servo grab1 = hardwareMap.get(Servo.class, "RightGrab");
        Servo grab2 = hardwareMap.get(Servo.class, "LeftGrab");
        Servo pincher = hardwareMap.get(Servo.class, "Pincher");
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        int position = 0;
        float positionS = 0;
        waitForStart();

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        claw.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        claw.setPower(.25);
        claw.setTargetPosition(-740);
        claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive()) {
//            if (gamepad2.dpad_up) {
//                position += 250;
//                sleep(100);
//            }
//            if (gamepad2.dpad_down) {
//                position -= 250;
//                sleep(100);
//            }
//            if (gamepad1.dpad_up) {
//                positionS += .1;
//                sleep(100);
//            }
//            if (gamepad1.dpad_down) {
//                positionS -= .1;
//                sleep(100);
//            }
            if(gamepad2.y) {
                arm.setTargetPosition(12250);
                arm.setPower(.5);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad2.a) {
                arm.setTargetPosition(0);
                arm.setPower(.5);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad2.dpad_up) {
                claw.setTargetPosition(-750);
                claw.setPower(.25);
                claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad2.dpad_down) {
                claw.setTargetPosition(-1800);
                claw.setPower(.25);
                claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad2.dpad_left) {
                claw.setTargetPosition(-1250);
                claw.setPower(.25);
                claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(gamepad2.right_bumper) {
                grab2.setPosition(.5);
                grab1.setPosition(.3);
            }
            if(gamepad2.left_bumper) {
                grab2.setPosition(.7);
                grab1.setPosition(.1);
            }
            if (gamepad2.right_trigger > .1) {
                pincher.setPosition(.8);
            }
            if (gamepad2.left_trigger > .1) {
                pincher.setPosition(.6);
            }
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = -gamepad1.right_stick_x;
            y *= .65;
            x *= .65;
            rx *= .65;

            if(gamepad1.left_trigger > 0) {
                y *= .5;
                x *= .5;
                rx *= .5;
            }

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftFrontMotor.setPower(frontLeftPower);
            leftRearMotor.setPower(backLeftPower);
            rightFrontMotor.setPower(frontRightPower);
            rightRearMotor.setPower(backRightPower);

            telemetry.addData("Position Servo",positionS);
            telemetry.addData("Position",claw.getCurrentPosition());
            telemetry.update();
        }
        rightFrontMotor.setPower(0.0);
        leftFrontMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
    }


}