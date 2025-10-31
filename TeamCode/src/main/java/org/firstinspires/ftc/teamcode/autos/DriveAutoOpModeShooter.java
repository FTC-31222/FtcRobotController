package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name="Drive Auto Shooter", group="Autonomous", preselectTeleOp="MainOpMode")
public class DriveAutoOpModeShooter extends LinearOpMode {
    final double kWheelDiameter =  3.77953; // inches of wheel diameterRadius
    final double kEncoderTicksPerRev = 537.7;

    final double kMaxSpeed = 0.25;

    DcMotorEx leftDrive_67;
    DcMotorEx rightDrive_67;


    final double FEEDER_41 = 1;
    final double SHOOTER_41 = 0.52;

    @Override
    public void runOpMode() throws InterruptedException {
       // leftDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("leftDrive");;
       // rightDrive_67 = (DcMotorEx) hardwareMap.dcMotor.get("rightDrive");


        CRServo feederRight_67 = hardwareMap.crservo.get("feederRight");
        CRServo feederLeft_67 =  hardwareMap.crservo.get("feederLeft");

        DcMotorEx shooter_67 = (DcMotorEx) hardwareMap.dcMotor.get("shooter_67");
        shooter_67.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter_67.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter_67.setVelocityPIDFCoefficients(139,0,0,0); //33 before



        waitForStart();
      //  rightDrive_67.setDirection(DcMotorSimple.Direction.FORWARD);
      //  leftDrive_67.setDirection(DcMotorSimple.Direction.REVERSE);


        shooter_67.setVelocity(150, AngleUnit.DEGREES);
        shooter_67.setPower(SHOOTER_41);
        sleep(1000);
//1
        feederRight_67.setPower(-FEEDER_41);
        feederLeft_67.setPower(FEEDER_41);
        sleep(500);
        feederRight_67.setPower(FEEDER_41);
        feederLeft_67.setPower(-FEEDER_41);
        sleep(1000);

        //2
        feederRight_67.setPower(-FEEDER_41);
        feederLeft_67.setPower(FEEDER_41);
        sleep(500);
        feederRight_67.setPower(FEEDER_41);
        feederLeft_67.setPower(-FEEDER_41);
        sleep(1000);

        //3
        feederRight_67.setPower(-FEEDER_41);
        feederLeft_67.setPower(FEEDER_41);
        sleep(500);
        feederRight_67.setPower(FEEDER_41);
        feederLeft_67.setPower(-FEEDER_41);

    //    telemetry.addData("Right Drive Pos: ", rightDrive_67.getCurrentPosition());
      //  telemetry.addData("Left Drive Pos: ", leftDrive_67.getCurrentPosition());
      //  telemetry.addData("Shooter Power", shooter_67.getVelocity(AngleUnit.DEGREES));


       // telemetry.update();
       // driveForwardTime(2000);
    }

    public double getRightPositionInches() {
        return rightDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
    }
    public double getLeftPositionInches() {
        return leftDrive_67.getCurrentPosition() * Math.PI * kWheelDiameter / kEncoderTicksPerRev;
    }
    public void driveRight(double in) {
        int x = (int)( in * kEncoderTicksPerRev/(Math.PI* kWheelDiameter));
        rightDrive_67.setTargetPosition(x);
        rightDrive_67.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive_67.setPower(kMaxSpeed);
    }

    public void driveLeft(double in) {
        int x = (int)( in * kEncoderTicksPerRev/(Math.PI* kWheelDiameter));
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
