package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Drive Test")
public class MyTestOpMode extends LinearOpMode {
    final double MAX_SPEED_41 = 1.0;
    final double FEEDER_41 = 0.67;
    final double SHOOTER_41 = 0.41;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftDrive_67 = hardwareMap.dcMotor.get("leftDrive");
        DcMotor rightDrive_67 = hardwareMap.dcMotor.get("rightDrive");

        CRServo feederRight_67 = hardwareMap.crservo.get("feederRight");
        CRServo feederLeft_67 =  hardwareMap.crservo.get("feederLeft");

        DcMotor shooter_67 = hardwareMap.dcMotor.get("shooter_67");


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            double leftInput = -gamepad1.left_stick_y * MAX_SPEED_41;
            double rightInput = -gamepad1.right_stick_y * MAX_SPEED_41;

            leftDrive_67.setDirection(REVERSE);
            leftDrive_67.setPower(leftInput);
            rightDrive_67.setPower(rightInput);

            if(gamepad2.y) {
                feederRight_67.setPower(FEEDER_41);
                feederLeft_67.setPower(-FEEDER_41);
            }
            else if (gamepad2.a) {
                feederRight_67.setPower(-FEEDER_41);
                feederLeft_67.setPower(FEEDER_41);

            }
            else{
                feederRight_67.setPower(0);
                feederLeft_67.setPower(0);
            }
            if(gamepad2.dpad_up){
                shooter_67.setPower(SHOOTER_41);

            }
            else if(gamepad2.dpad_down){
                shooter_67.setPower(-SHOOTER_41);
            }
            else{
                shooter_67.setPower(0);
            }

        }
    }
}
