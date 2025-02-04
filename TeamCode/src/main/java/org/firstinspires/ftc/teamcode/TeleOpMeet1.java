package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
        DcMotor lift = hardwareMap.get(DcMotor.class, "Lift");
        DcMotor extend = hardwareMap.get(DcMotor.class, "Extend");
        Servo grab = hardwareMap.get(Servo.class,"Grab");
        Servo rotate = hardwareMap.get(Servo.class,"Rotate");
        Servo bucket = hardwareMap.get(Servo.class,"Bucket");
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        int tick = 0;

        int position = 0;
        double positionS = 0.0;

        waitForStart();

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {
//            if (gamepad2.dpad_up) {
//                position += 10;
//                sleep(100);
//            }
//            if (gamepad2.dpad_down) {
//                position -= 10;
//                sleep(100);
//            }
            if (gamepad1.dpad_up) {
                positionS += .05;
                sleep(100);
            }
            if (gamepad1.dpad_down) {
                positionS -= .05;
                sleep(100);
            }

            if(gamepad1.x) {
                bucket.setPosition(positionS);
            }
            if(gamepad1.b) {
                grab.setPosition(positionS);
            }


            //rotate down is .9
            //rotate up is .2


            if(gamepad2.y) {
                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rotate.setPosition(.6);
                sleep(300);
                lift.setTargetPosition(3100);
                lift.setPower(1);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                bucket.setPosition(.2);
            }
            if(gamepad2.a) {
                rightFrontMotor.setPower(1);
                leftFrontMotor.setPower(1);
                rightRearMotor.setPower(1);
                leftRearMotor.setPower(1);
                rotate.setPosition(.6);
                bucket.setPosition(.55);
                lift.setTargetPosition(0);
                lift.setPower(1);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
            if(gamepad2.x) {
                bucket.setPosition(0);
            }
            if(gamepad2.left_trigger > 0) {
                tick=0;
                extend.setTargetPosition(0);
                extend.setPower(1);
                extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rotate.setPosition(0);
            }
            if(tick==60) {
                grab.setPosition(.25);
            }
            if(gamepad2.right_trigger > 0) {
                extend.setTargetPosition(120);
                extend.setPower(1);
                extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rotate.setPosition(0.75);
                grab.setPosition(.25);
            }
            if(gamepad2.dpad_right) {
                grab.setPosition(.25);
            } else if (gamepad2.dpad_left) {
                grab.setPosition(.45);
            }

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
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

            tick+=1;
            telemetry.addData("Servo",positionS);
            telemetry.addData("Lift",lift.getCurrentPosition());
            telemetry.addData("Extend",extend.getCurrentPosition());
            telemetry.addData("rotatesaldihowu",rotate.getPosition());
            telemetry.addData("tick",tick);
            telemetry.update();
        }
        rightFrontMotor.setPower(0.0);
        leftFrontMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
    }




}

