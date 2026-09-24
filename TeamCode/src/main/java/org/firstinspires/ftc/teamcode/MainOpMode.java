package org.firstinspires.ftc.teamcode;
// Libraries
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="MainOpMode")
public class MainOpMode extends LinearOpMode {
    // Motor speeds
    
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftDriveFront = hardwareMap.dcMotor.get("leftDriveFront");
        DcMotor rightDriveFront = hardwareMap.dcMotor.get("rightDriveFront");
        DcMotor leftDriveBack = hardwareMap.dcMotor.get("leftDriveBack");
        DcMotor rightDriveBack = hardwareMap.dcMotor.get("rightDriveBack");
        DcMotor intake  = hardwareMap.dcMotor.get("intake");

        leftDriveFront.setDirection(REVERSE);
        leftDriveBack.setDirection(REVERSE);
        rightDriveFront.setDirection(FORWARD);
        rightDriveBack.setDirection(FORWARD);
        intake.setDirection(FORWARD);

        waitForStart();

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

            // Intake (or reverse to outtake)
            if (gamepad1.right_bumper) {
                intake.setDirection(FORWARD);
                intake.setPower(1.0);
            } else if (gamepad1.left_bumper) {
                intake.setDirection(REVERSE);
                intake.setPower(1.0);
            } else {
                intake.setPower(0);
            }

            leftDriveFront.setPower(frontLeftPower);
            leftDriveBack.setPower(backLeftPower);
            rightDriveFront.setPower(frontRightPower);
            rightDriveBack.setPower(backRightPower);

            if(isStopRequested()) return;
        }
    }
}

