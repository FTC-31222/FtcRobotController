package org.firstinspires.ftc.teamcode;
// Libraries
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name="MainOpMode")
public class MainOpMode extends LinearOpMode {
    // Motor speeds
    final int SHOOTER_VELOCITY = 180;
    final double SHOOTER_POWER = 0.65;
    final double INTAKE_POWER = 0.7;
    final double INTAKE_POWER_BOOST = 1.0;
    final double GATE = 0.30;
    final double ROTATE_RATIO = 0.5;
    final double DRIVE_RATIO = 0.7;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftDriveFront = hardwareMap.dcMotor.get("leftDriveFront");
        DcMotor rightDriveFront = hardwareMap.dcMotor.get("rightDriveFront");
        DcMotor leftDriveBack = hardwareMap.dcMotor.get("leftDriveBack");
        DcMotor rightDriveBack = hardwareMap.dcMotor.get("rightDriveBack");
        DcMotor intake  = hardwareMap.dcMotor.get("intake");
        DcMotorEx shooter =(DcMotorEx) hardwareMap.dcMotor.get("shooter");
        Servo gate = hardwareMap.servo.get("gate");
        ElapsedTime shakeTimer = new ElapsedTime();

        leftDriveFront.setDirection(REVERSE);
        leftDriveBack.setDirection(REVERSE);
        rightDriveFront.setDirection(FORWARD);
        rightDriveBack.setDirection(FORWARD);
        intake.setDirection(FORWARD);
        gate.setDirection(Servo.Direction.REVERSE);

        leftDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setVelocityPIDFCoefficients(160,0,0,0);
        shooter.setDirection(REVERSE);

        waitForStart();

        double y = 0.0;
        double x = 0.0;
        double rx = 0.0;
        boolean bIntake = false;

        while (opModeIsActive()) {
            y = -gamepad1.left_stick_y *DRIVE_RATIO; // Remember, Y stick value is reversed
            x = gamepad1.left_stick_x * 1.1 * DRIVE_RATIO; // Counteract imperfect strafing
            rx = gamepad1.right_stick_x * ROTATE_RATIO;

            // Minor rotate or minor forward/backwards
            if (gamepad2.dpad_left) {
                rx = -0.2;
            } else if (gamepad2.dpad_right) {
                rx = 0.2;
            } else if (gamepad2.dpad_up) {
                y = -0.2;
            } else if (gamepad2.dpad_down) {
                y = 0.2;
            }

            // Shake it
            if (gamepad2.right_trigger_pressed) {
                // square wave: sign flips every 80 ms
                int milli = ((int)(shakeTimer.milliseconds() / 80)) % 3;
                boolean right = milli == 0;
                boolean pause = milli == 1;
                boolean left = milli == 2;
                if (right) x = 0.6;
                if (left) x = -0.6;
                if (pause) x = 0;
                y = 0.0;
                rx = 0.00;
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            if (gamepad1.right_bumper) {
                bIntake = true;
            } else if (gamepad1.left_bumper) {
                bIntake = false;
            }

            // Intake (or reverse to outtake)
            if (gamepad1.right_trigger_pressed) {
                intake.setDirection(FORWARD);
                intake.setPower(INTAKE_POWER_BOOST);
            } else if (bIntake) {
                intake.setDirection(FORWARD);
                intake.setPower(INTAKE_POWER);
            } else if (gamepad1.left_bumper) {
                intake.setDirection(REVERSE);
                intake.setPower(0.3);
            } else {
                intake.setPower(0);
            }

            // Shooter
            if (gamepad2.a) {
                shooter.setVelocity(SHOOTER_VELOCITY, AngleUnit.DEGREES);
                shooter.setPower(SHOOTER_POWER);
            } else if (gamepad2.b) {
                shooter.setVelocity(0, AngleUnit.DEGREES);
                shooter.setPower(0);
            }

            // Gate
            if (gamepad2.y) {
                gate.setPosition(GATE);
            } else {
                gate.setPosition(0.0);
            }

            leftDriveFront.setPower(frontLeftPower);
            leftDriveBack.setPower(backLeftPower);
            rightDriveFront.setPower(frontRightPower);
            rightDriveBack.setPower(backRightPower);

            if(isStopRequested()) return;
        }
    }
}

