//package org.firstinspires.ftc.teamcode.autos;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//@Autonomous(name="Drive Auto", group="Autonomous", preselectTeleOp="MainOpMode")
//public class DriveAutoOpMode extends LinearOpMode {
//    final double kWheelDiameter =  3.77953; // inches of wheel diameterRadius
//    final double kEncoderTicksPerRev = 537.7;
//
//    DcMotorEx leftDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("leftDrive");
//    DcMotorEx rightDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("rightDrive");
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//    }
//
//    public double getRightPositionInchea]s() {
//        return rightDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
//    }
//    public double getLeftPositionInches() {
//        return leftDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
//    }
//}
