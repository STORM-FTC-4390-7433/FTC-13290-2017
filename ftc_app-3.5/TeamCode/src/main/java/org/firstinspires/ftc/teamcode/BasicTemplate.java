
package org.firstinspires.ftc.teamcode;
//imports this years qualcom ftc code
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by FTC13290-DEV on 11/7/2017.
 */
// Gives name to the teleop program
@TeleOp (name = "Leonardo", group = "TMNT")
// Declares the motors and servos
// this tells us that the program is an extension of another program.
public class BasicTemplate extends LinearOpMode{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorLift;

    private Servo armservoRight;
    private Servo armservoLeft;


    /*private static double armOpenPos = 0.5;
    private static double armClosePos = -0.8;*/
    @Override
    // Gives names to each of the motors in the phone

    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        armservoRight = hardwareMap.servo.get("armServoRight");
        armservoLeft = hardwareMap.servo.get("armServoLeft");
        // Sets motors to go forward
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        armservoRight.setPosition(0.3);
        armservoLeft.setPosition(0.3);

        double armAdj = 0.1;
        waitForStart();

        while(opModeIsActive()){
            // Declares the variables we can use to set as the amount of power.
            double LMPowr;
            double RMPowr;
            // Sets limit to how fast the motors can go.
            // If the power goes past .75 we have set it to go back to .75 so it will stay at that power.
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
        // when each button is pressed the servo will go the set position allowing it to open and close
        if (gamepad2.b) {
            armAdj = 0.0;
            armservoRight.setPosition(armAdj);
        }

        if (gamepad2.x){
            armAdj = 0.5;
            armservoRight.setPosition(armAdj);
        }

        if (gamepad2.b) {
            armAdj = 0.5;
            armservoLeft.setPosition(armAdj);
        }
        if (gamepad2.x) {
            armAdj = 0.0;
            armservoLeft.setPosition(armAdj);
        }


        if (gamepad2.right_bumper){
            armAdj = armservoRight.getPosition();
            armAdj = armAdj+0.1;
            if (armAdj > 1.0) {
                armAdj = 1.0;
            }
            armservoRight.setPosition(armAdj);
        }


        if (gamepad2.left_bumper){
            armAdj = armservoRight.getPosition();
            armAdj = armAdj-0.1;
            if (armAdj < -1.0) {
                armAdj = -1.0;
            }
            armservoRight.setPosition(armAdj);
        }


            telemetry.addData("armAdj", armAdj);
//            telemetry.update();
        // Sets the speed and direction of the lift when the corresponding buttons are pressed
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


