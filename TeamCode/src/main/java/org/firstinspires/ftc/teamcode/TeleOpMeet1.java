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
        DcMotor spin = hardwareMap.get(DcMotor.class, "Spin");
        Servo bucket = hardwareMap.get(Servo.class, "Bucket");
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        int position = 0;
        float positionS = 0;
        waitForStart();

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {
            if(arm.getCurrentPosition()>1000) {
                bucket.setPosition(.8);
            } else {
                bucket.setPosition(.7);
            }
            if (gamepad1.dpad_up) {
                position += 250;
                sleep(200);
            }
            if (gamepad1.dpad_down) {
                position -= 250;
                sleep(200);
            }
            if (gamepad2.dpad_up) {
                positionS += .1;
                sleep(100);
            }
            if (gamepad2.dpad_down) {
                positionS -= .1;
                sleep(100);
            }
            if (gamepad2.y) {
                arm.setTargetPosition(11750);
                arm.setPower(1);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad2.a) {
                arm.setTargetPosition(0);
                arm.setPower(1);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad2.left_trigger > .1) {
                spin.setPower(.65);
            }
            if (gamepad2.right_trigger > .1) {
                spin.setPower(0);
            }
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.1;
            double rx = -gamepad1.right_stick_x;
            y *= .65;
            x *= .65;
            rx *= .65;

            if (gamepad1.left_trigger > 0) {
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

            telemetry.addData("Position Servo", positionS);
            telemetry.addData("Position", position);
            telemetry.addData("Position Arm", arm.getCurrentPosition());
            telemetry.update();
        }
        rightFrontMotor.setPower(0.0);
        leftFrontMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
    }
    }