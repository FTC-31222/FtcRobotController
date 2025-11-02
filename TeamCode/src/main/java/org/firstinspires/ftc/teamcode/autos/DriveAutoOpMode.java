package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Drive Auto", group="Autonomous", preselectTeleOp="MainOpMode")
public class DriveAutoOpMode extends LinearOpMode {
    final double kWheelDiameter =  3.77953; // inches of wheel diameterRadius
    final double kEncoderTicksPerRev = 537.7;
    
    final double kMaxSpeed = 0.25;


    DcMotorEx leftDrive_67;
    DcMotorEx rightDrive_67;

    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("leftDrive");
        rightDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("rightDrive");

        rightDrive_67.setDirection(DcMotor.Direction.REVERSE);
        leftDrive_67.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        driveForwardTime(2000);
    }

    public double getRightPositionInches() {
        return rightDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
    }
    public double getLeftPositionInches() {
        return leftDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
    }
    public void driveRight(double inches){
        int x = (int)(inches * kEncoderTicksPerRev / (Math.PI * kWheelDiameter));
        rightDrive_67.setTargetPosition(x);
        rightDrive_67.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive_67.setPower(kMaxSpeed);
    }
    public void driveLeft(double inches){
        int x = (int)(inches * kEncoderTicksPerRev / (Math.PI * kWheelDiameter));
        leftDrive_67.setTargetPosition(x);
        leftDrive_67.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive_67.setPower(kMaxSpeed);
    }
    public void driveForwardTime(long ms){
        leftDrive_67.setPower(kMaxSpeed);
        rightDrive_67.setPower(kMaxSpeed);
        sleep(ms);

    }
    
}

