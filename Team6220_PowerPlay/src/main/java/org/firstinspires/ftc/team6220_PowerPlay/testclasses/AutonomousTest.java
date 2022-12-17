package org.firstinspires.ftc.team6220_PowerPlay.testclasses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.team6220_PowerPlay.AprilTagDetect;
import org.firstinspires.ftc.team6220_PowerPlay.Constants;

@Disabled
@Autonomous(name = "AutonomousTest", group = "Test")
public class AutonomousTest extends AprilTagDetect {

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        // grab cone
        servoGrabber.setPosition(Constants.GRABBER_CLOSE_POSITION);
        sleep(1500);

        // drive slides upwards a few inches
        driveSlidesAutonomous(Constants.SLIDE_LOW);
        sleep(1000);

        int signal = detectAprilTag();

        // drive to furthest tile in our quadrant
        driveInches(0, 54);
        sleep(500);

        // turn to -45 degrees
        turnToAngle(45);
        sleep(500);

        // drive slides to high junction
        driveSlidesAutonomous(Constants.SLIDE_HIGH);
        sleep(500);

        // drive forwards to high junction
        driveInches(0, 5 * Math.sqrt(2));
        sleep(500);

        // drive slides to high junction
        driveSlidesAutonomous(Constants.SLIDE_HIGH - 200);
        sleep(1000);

        // release cone
        servoGrabber.setPosition(Constants.GRABBER_OPEN_POSITION);
        sleep(1000);

        // drive backwards from high junction
        driveInches(180, 5 * Math.sqrt(2));
        sleep(500);

        // drive slides to high junction
        driveSlidesAutonomous(Constants.SLIDE_BOTTOM);
        sleep(500);

        switch (signal) {
            case 0:
                // turn to 90 degrees
                turnToAngle(90);
                sleep(500);

                // drive to signal zone 1
                driveInches(0, 24);
                sleep(500);

                // turn to 0 degrees
                turnToAngle(0);
                break;

            case 1:
                // turn to 0 degrees
                turnToAngle(0);
                sleep(500);

                // drive to signal zone 2
                driveInches(180, 2);
                sleep(500);
                break;

            case 2:
                // turn to 90 degrees
                turnToAngle(90);
                sleep(500);

                // drive to signal zone 3
                driveInches(180, 24);
                sleep(500);

                // turn to 0 degrees
                turnToAngle(0);
                break;
        }
    }
}
