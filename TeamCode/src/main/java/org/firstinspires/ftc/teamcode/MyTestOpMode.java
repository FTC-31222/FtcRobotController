package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Drive Test")
public class MyTestOpMode extends LinearOpMode {
    final double MAX_SPEED = 1.0;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftDrive = hardwareMap.dcMotor.get("leftDrive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("rightDrive");

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            double leftInput = gamepad1.left_stick_y * MAX_SPEED;
            double rightInput = -gamepad1.right_stick_y * MAX_SPEED;

            leftDrive.setDirection(FORWARD);
            leftDrive.setPower(leftInput);
            rightDrive.setPower(rightInput);

        }





    }
}
