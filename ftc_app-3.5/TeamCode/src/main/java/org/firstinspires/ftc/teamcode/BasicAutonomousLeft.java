
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 7433 on 11/18/2017.
 */
/*
Copyright (c) 2016 Robert Atkinson
All rights reserved.
Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.
NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENC
E OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
// gives the program a name
@Autonomous(name="BasicAutonomousLeft", group="Autonomous")  // @Autonomous(...) is the other common choice

public class BasicAutonomousLeft extends BasicTemplate {


   // Declares the motors and servos

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorLift = null;
    private Servo armservoRight = null;
    private Servo armservoLeft = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

       /* eg: Initialize the hardware variables. Note that the strings used here as parameters
        * to 'get' must correspond to the names assigned during the robot configuration
        * step (using the FTC Robot Controller app on the phone).
        */
        motorLeft  = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        armservoRight = hardwareMap.servo.get("armServoRight");
        armservoLeft = hardwareMap.servo.get("armServoLeft");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        // color_sensor = hardwareMap.colorSensor.get("color");
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //leftMotor = hardwareMap.dcMotor.get("leftMotor");
        //rightMotor = hardwareMap.dcMotor.get("rightMotor");
        //jewelServo = hardwareMap.servo.get("jewelServo");
        //color_sensor = hardwareMap.colorSensor.get("color");

        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            // telemetry.update();
           /*shooterLeft.setPower(0.37);
           shooterRight.setPower(0.37);*/

            motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //color_sensor.red();
            //color_sensor.blue();
            armservoLeft.setPosition(0.5);
            armservoRight.setPosition(0.0);
            sleep(1000);

            motorLift.setPower(.8);
            sleep(1000);
            motorLift.setPower(0);
            motorLeft.setTargetPosition(1000);
            motorRight.setTargetPosition(1000);

            int leftPosition = motorLeft.getCurrentPosition();
            telemetry.addData("Encoder Position", leftPosition);
            int rightPosition = motorRight.getCurrentPosition();
            telemetry.addData("Encoder Position", rightPosition);
            telemetry.update();
            sleep(2000);
            motorLeft.setPower(.3);
            motorRight.setPower(.275);


            while(motorLeft.isBusy() && motorRight.isBusy() && opModeIsActive()) {
                leftPosition = motorLeft.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = motorRight.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
            }
            motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorLeft.setTargetPosition(1700);
            motorRight.setTargetPosition(-1700);

            motorLeft.setPower(.3);
            motorRight.setPower(.275);
            while(motorLeft.isBusy() && motorRight.isBusy() && opModeIsActive()) {
                leftPosition = motorLeft.getCurrentPosition();
                telemetry.addData("Left Encoder Position", leftPosition);
                rightPosition = motorRight.getCurrentPosition();
                telemetry.addData("Right Encoder Position", rightPosition);
                telemetry.update();
            }
            motorLeft.setPower(0);
            motorRight.setPower(0);
            telemetry.addData("Encoder Position", leftPosition);
            telemetry.addData("Encoder Position", rightPosition);
            telemetry.update();
            sleep(1000);
            armservoLeft.setPosition(0.0);
            armservoRight.setPosition(0.5);



            // sleep(5000);
         /*   rightArmServo.setPosition(.7);
            leftArmServo.setPosition(.7);
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            Thread.sleep(1200);
            idle();
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
            Thread.sleep(1700);
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            Thread.sleep(1800);
*/
            // leftMotor.getCurrentPosition();
            //  leftMotor.setTargetPosition(100);
            // leftMotor.isBusy();
   /*
    Thread.sleep(1000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   //conveyor.setPower(1);
   try {
    Thread.sleep(1000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
           /*conveyor.setPower(0);
           shooterLeft.setPower(0);
           shooterRight.setPower(0);*/
   /*try {
    Thread.sleep(2000);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   leftMotor.setPower(1);
   rightMotor.setPower(1);
   try {
    Thread.sleep(625);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
   leftMotor.setPower(0);
   rightMotor.setPower(0);
   programState = 0;
/*
   requestOpModeStop();
   idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
  }
 }
 public void encoderDrive(double speed,
                          double leftInches, double rightInches,
                          double timeoutS) throws InterruptedException {
  int newLeftTarget;
  int newRightTarget;
  // Ensure that the opmode is still active
  //  if (opModeIsActive()) {
  // Determine new target position, and pass to motor controller
  // newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
  //  newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
  //  leftMotor.setTargetPosition(newLeftTarget);
  // rightMotor.setTargetPosition(newRightTarget);
  // Turn On RUN_TO_POSITION
  //leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  //rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  // reset the timeout time and start motion.
  //runtime.reset();
  //leftMotor.setPower(Math.abs(speed));
  //rightMotor.setPower(Math.abs(speed));
  // keep looping while we are still active, and there is time left, and both motors are running.
            /*while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {
                // Display it for the driver.
                //telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
               // telemetry.addData("Path2",  "Running at %7d :%7d",
                     //   leftMotor.getCurrentPosition(),
                      //  rightMotor.getCurrentPosition());
               // telemetry.update();
                // Allow time for other processes to run.
                idle();
            }
            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //  sleep(250);   // optional pause after each move */
        }
    }
}