package org.firstinspires.ftc.teamcode.autos;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name="01 - Normal Shoot and Park", group="Autonomous", preselectTeleOp="MainOpMode")
public class DriveAutoOpMode extends LinearOpMode {
    final int SHOOTER_VELOCITY = 180;
    final double SHOOTER_POWER = 0.65;
    final double GATE = 0.30;
    final double kMaxSpeed = 0.2;
    DcMotor leftDriveFront;
    DcMotor rightDriveFront;
    DcMotor leftDriveBack;
    DcMotor rightDriveBack;
    DcMotor intake;
    DcMotorEx shooter;
    Servo gate;

    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveFront = hardwareMap.dcMotor.get("leftDriveFront");
        rightDriveFront = hardwareMap.dcMotor.get("rightDriveFront");
        leftDriveBack = hardwareMap.dcMotor.get("leftDriveBack");
        rightDriveBack = hardwareMap.dcMotor.get("rightDriveBack");
        intake  = hardwareMap.dcMotor.get("intake");
        shooter =(DcMotorEx) hardwareMap.dcMotor.get("shooter");
        gate = hardwareMap.servo.get("gate");

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

        ElapsedTime runtime = new ElapsedTime();
        waitForStart();

        // Spin up shooter
        shooter.setVelocity(SHOOTER_VELOCITY, AngleUnit.DEGREES);
        shooter.setPower(SHOOTER_POWER);
        runtime.reset();
        while (runtime.milliseconds() <= 1500) {
        }

        // Open Gate
        runtime.reset();
        gate.setPosition(GATE);
        while (runtime.milliseconds() <= 5000) {
        }
        shooter.setPower(0.0);
        gate.setPosition(0);


        // Back
        leftDriveFront.setPower(-kMaxSpeed);
        leftDriveBack.setPower(-kMaxSpeed);
        rightDriveFront.setPower(-kMaxSpeed);
        rightDriveBack.setPower(-kMaxSpeed);
        runtime.reset();
        while (runtime.milliseconds() <= 1000) {
        }

        // Right Strife
        leftDriveFront.setPower(kMaxSpeed);
        leftDriveBack.setPower(-kMaxSpeed);
        rightDriveFront.setPower(-kMaxSpeed);
        rightDriveBack.setPower(kMaxSpeed);
        runtime.reset();
        while (runtime.milliseconds() <= 3000) {
        }

        // Back
        leftDriveFront.setPower(-kMaxSpeed);
        leftDriveBack.setPower(-kMaxSpeed);
        rightDriveFront.setPower(-kMaxSpeed);
        rightDriveBack.setPower(-kMaxSpeed);
        runtime.reset();
        while (runtime.milliseconds() <= 6500) {
        }

        // Right Strife
        leftDriveFront.setPower(kMaxSpeed);
        leftDriveBack.setPower(-kMaxSpeed);
        rightDriveFront.setPower(-kMaxSpeed);
        rightDriveBack.setPower(kMaxSpeed);
        runtime.reset();
        while (runtime.milliseconds() <= 1200) {
        }


    }

}

