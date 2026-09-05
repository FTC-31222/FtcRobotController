package org.firstinspires.ftc.teamcode;
// Libraries
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name="MainOpMode")
public class MainOpMode extends LinearOpMode {
    // Motor speeds
    final double MAX_SPEED_41 = 0.95;
    final double FEEDER_41 = 0.33;
    final double SHOOTER_41 = 0.52;
    boolean isShooterOn = false;
    boolean lastShooterToggle = false;
    final double FASTER_SHOOTER_41 = 0.67;
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftDriveFront = hardwareMap.dcMotor.get("leftDriveFront");
        DcMotor rightDriveFront = hardwareMap.dcMotor.get("rightDriveFront");
        DcMotor leftDriveBack = hardwareMap.dcMotor.get("leftDriveBack");
        DcMotor rightDriveBack = hardwareMap.dcMotor.get("rightDriveBack");

        // double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
       // double x = gamepad1.left_stick_x;
        // double rx = gamepad1.right_stick_x;

        waitForStart();

        //leftDriveFront.setPower(y + x + rx);
        //leftDriveBack.setPower(y - x + rx);
       // rightDriveFront.setPower(y - x - rx);
       // rightDriveBack.setPower(y + x - rx);

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftDriveFront.setDirection(REVERSE);
            leftDriveBack.setDirection(REVERSE);
            rightDriveFront.setDirection(FORWARD);
            rightDriveBack.setDirection(FORWARD);

            leftDriveFront.setPower(frontLeftPower);
            leftDriveBack.setPower(backLeftPower);
            rightDriveFront.setPower(frontRightPower);
            rightDriveBack.setPower(backRightPower);

            if(isStopRequested()) return;

            leftDrive_67.setPower(leftInput);
            rightDrive_67.setPower(rightInput);

            if(gamepad2.y) {
                feederRight_67.setPower(-FEEDER_41);
                feederLeft_67.setPower(FEEDER_41);
            } else{
                feederRight_67.setPower(FEEDER_41);
                feederLeft_67.setPower(-FEEDER_41);
            }

            // Handle shooter wheel speed
            // If user press dpad_up, we spin at "normal" speed
            // If left_trigger is pressed, we increase the speed.
            if (gamepad2.dpad_down) {
                shooter_67.setPower(-SHOOTER_41);
            } else if (isShooterOn ) {
                shooter_67.setVelocity(160, AngleUnit.DEGREES);
                shooter_67.setPower(1);
            } else {
                shooter_67.setPower(0);
            }

            if(lastShooterToggle != gamepad2.dpad_up && gamepad2.dpad_up) {
                isShooterOn = !isShooterOn;
            }
            lastShooterToggle = gamepad2.dpad_up;

            telemetry.addData("Right Drive Pos: ", rightDrive_67.getCurrentPosition());
            telemetry.addData("Left Drive Pos: ", leftDrive_67.getCurrentPosition());
            telemetry.addData("Shooter Power", shooter_67.getVelocity(AngleUnit.DEGREES));

            telemetry.update();
        }
//
//        CRServo feederRight_67 = hardwareMap.crservo.get("feederRight");
//        CRServo feederLeft_67 =  hardwareMap.crservo.get("feederLeft");
//
//        DcMotorEx shooter_67 = (DcMotorEx) hardwareMap.dcMotor.get("shooter_67");
//        shooter_67.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        shooter_67.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        shooter_67.setVelocityPIDFCoefficients(139,0,0,0); //33 before
//
//
//        waitForStart();
//    // init the motors first so it doesn't run during boot up
//
//        //leftDrive_67.setPower(0);
//        //rightDrive_67.setPower(0);
//
//        if(isStopRequested()) return;
//
//        while(opModeIsActive()) {
//            //double leftInput = (-gamepad1.left_stick_y * MAX_SPEED_41) + (gamepad1.right_stick_x * MAX_SPEED_41);
//           // double rightInput = (-gamepad1.left_stick_y * MAX_SPEED_41) + (-gamepad1.right_stick_x * MAX_SPEED_41);
//            double drive = -gamepad1.right_stick_y; // Forward/Backward
//            double turn  =  gamepad1.right_stick_x; // Turning
//
//            double leftInput  = (drive + turn) * MAX_SPEED_41;
//            double rightInput = (drive - turn) * MAX_SPEED_41;
//
//            rightDrive_67.setDirection(FORWARD);
//            leftDrive_67.setDirection(REVERSE);
//
//            leftDrive_67.setPower(leftInput);
//            rightDrive_67.setPower(rightInput);
//
//            if(gamepad2.y) {
//                feederRight_67.setPower(-FEEDER_41);
//                feederLeft_67.setPower(FEEDER_41);
//            } else{
//                feederRight_67.setPower(FEEDER_41);
//                feederLeft_67.setPower(-FEEDER_41);
//            }
//
//            // Handle shooter wheel speed
//            // If user press dpad_up, we spin at "normal" speed
//            // If left_trigger is pressed, we increase the speed.
//            if (gamepad2.dpad_up) {
//                shooter_67.setVelocity(150, AngleUnit.DEGREES);
//                shooter_67.setPower(1);
//            } else if (gamepad2.left_trigger > 0.0) {
//                shooter_67.setPower(FASTER_SHOOTER_41);;
//            } else if (gamepad2.dpad_down) {
//                shooter_67.setPower(-SHOOTER_41);
//            } else {
//                shooter_67.setPower(0);
//            }
//
//            telemetry.addData("Right Drive Pos: ", rightDrive_67.getCurrentPosition());
//            telemetry.addData("Left Drive Pos: ", leftDrive_67.getCurrentPosition());
//            telemetry.addData("Shooter Power", shooter_67.getVelocity(AngleUnit.DEGREES));
//
//
//            telemetry.update();
//        }
    }
}

