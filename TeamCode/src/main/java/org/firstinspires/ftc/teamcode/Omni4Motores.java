/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Omni 4 llantas Chassis", group="Drive")
//@Disabled
public class Omni4Motores extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareChassisHerman   robot           = new HardwareChassisHerman();              // Use a K9'shardware

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        double servop = robot.eject.getPosition();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Fucking", "Nigger");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

           if(gamepad1.x){
               robot.eolico.setPower(1);
           } else if (gamepad1.y) {
               robot.eolico.setPower(-1);
           } else {
               robot.eolico.setPower(0);
           }

           if(gamepad1.a){
               robot.recogedor.setPower(1);
           } else if (gamepad1.b){
               robot.recogedor.setPower(-1);
           } else {
               robot.recogedor.setPower(0);
           }

           if(gamepad1.left_bumper){
               robot.LiftL1.setPower(-1);
               robot.LiftL2.setPower(-1);
               robot.LiftL3.setPower(-1);
               robot.LiftL4.setPower(1);
               robot.LiftL5.setPower(1);
               robot.LiftL6.setPower(1);
           } else if(gamepad1.right_bumper){
               robot.LiftL1.setPower(1);
               robot.LiftL2.setPower(1);
               robot.LiftL3.setPower(1);
               robot.LiftL4.setPower(-1);
               robot.LiftL5.setPower(-1);
               robot.LiftL6.setPower(-1);
           } else {
               robot.LiftL1.setPower(0);
               robot.LiftL2.setPower(0);
               robot.LiftL3.setPower(0);
               robot.LiftL4.setPower(0);
               robot.LiftL5.setPower(0);
               robot.LiftL6.setPower(0);
           }


            if (gamepad1.dpad_down){
               robot.Elevador.setPower(1);
            } else if (gamepad1.dpad_up){
               robot.Elevador.setPower(-1);
            } else{
               robot.Elevador.setPower(0);
            }

            if (gamepad1.dpad_right){
                robot.eject.setPosition(1);
            } else if (gamepad1.dpad_left){
                robot.eject.setPosition(0);
            }

            Mecanum(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }

            telemetry.addData("servo",  "pos = %.2f", servop);
            telemetry.update();

        }

    public void Mecanum (double x, double y, double rotation){
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = -x + y - rotation;
        wheelSpeeds[1] = x + y + rotation;
        wheelSpeeds[2] = x + y - rotation;
        wheelSpeeds[3] = -x + y + rotation;

        robot.normalize(wheelSpeeds);

        robot.frontLeftDrive.setPower(wheelSpeeds[0]);
        robot.frontRightDrive.setPower(wheelSpeeds[1]);
        robot.backLeftDrive.setPower(wheelSpeeds[2]);
        robot.backRightDrive.setPower(wheelSpeeds[3]);
    }
}
