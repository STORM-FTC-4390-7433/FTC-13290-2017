
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by FTC13290-DEV on 11/7/2017.
 */
@TeleOp (name = "Leonardo", group = "TMNT")
public class BasicTemplate extends LinearOpMode{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorLift;

    private Servo armservoRight;


    /*private static double armOpenPos = 0.5;
    private static double armClosePos = -0.8;*/

    @Override
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        armservoRight = hardwareMap.servo.get("armServoRight");
        double armAdj = 0.1;
        waitForStart();

        while(opModeIsActive()){
            double LMPowr;
            double RMPowr;
            LMPowr = -gamepad1.left_stick_y;
            RMPowr = -gamepad1.right_stick_y;
            if (LMPowr < -0.75) {
                LMPowr = -0.75;
            }

            if (RMPowr < -0.75) {
                RMPowr = -0.75;
            }

            if (LMPowr > 0.75) {
                LMPowr = 0.75;
            }

            if (RMPowr > 0.75) {
                RMPowr = 0.75;
            }
        motorLeft.setPower(LMPowr);
        motorRight.setPower(RMPowr);

        if (gamepad2.b) {
            armAdj = 0.5;
            armservoRight.setPosition(armAdj);
        }

        if (gamepad2.x){
            armAdj = -0.8;
            armservoRight.setPosition(armAdj);
        }


        if (gamepad2.right_bumper = true){
            //armAdj = armservoRight.getPosition();
            armAdj = armAdj+0.1;
            if (armAdj > 0.5) {
                armAdj = 0.5;
            }
            armservoRight.setPosition(armAdj);
        }


        if (gamepad2.left_bumper = true){
            //armAdj = armservoRight.getPosition();
            armAdj = armAdj-0.1;
            if (armAdj < -0.8) {
                armAdj = -0.8;
            }
            armservoRight.setPosition(armAdj);
        }


        double liftSpeed = 0.0;
            if (gamepad2.y) {
                liftSpeed = 0.5;
            }

            if (gamepad2.a){
                liftSpeed = -0.5;
            }
            if (gamepad2.right_trigger > 0) {
                liftSpeed = gamepad2.right_trigger;
            }
            if (gamepad2.left_trigger > 0) {
                liftSpeed = -gamepad2.left_trigger;
            }
            motorLift.setPower(liftSpeed);
        idle();
        }
    }
}


