/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.util.NemesisDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/**
 * No encoders
 * 
 */
public class drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX leftDriveMaster;
  private final TalonSRX rightDriveMaster;

  private final VictorSPX leftDriveSlave;
  private final VictorSPX rightDriveSlave;

  private NemesisDrive drivesystem;

  private double straightPower = 0;
  private double turnPower = 0;

  // singleton
  private static Drivetrain driveInstance = null;

   // enum for the switch statements, dictates the drivetrain control method
   private States driveState = States.STOPPED;

   public static Drivetrain getDrivetrainInstance() {
    if (driveInstance == null) {
      driveInstance = new Drivetrain();
    }
    return driveInstance;
  }
  
  private enum States {
    STOPPED, TELEOP_DRIVE, DRIVE_STRAIGHT
  }

  public Drivetrain() {
    leftDriveMaster = new TalonSRX(2);
		leftDriveSlave = new VictorSPX(3);

		rightDriveMaster = new TalonSRX(0);
    rightDriveSlave = new VictorSPX(1);

    leftDriveMaster.setInverted(true);
    leftDriveSlave.setInverted(true);

    leftDriveSlave.follow(leftDriveMaster);
    rightDriveSlave.follow(rightDriveMaster);
  }

  public void update() { // Code cannot run w/o this method
    switch (driveState) {

      case DRIVE_STRAIGHT: 
        break;
  
      case STOPPED:
      setSpeeds(0, 0);
        break;

      case TELEOP_DRIVE:
      final double out[] = drivesystem.calculate(straightPower, turnPower);
      setSpeeds(out[0], out[1]);

      }
    }

    public void setSpeeds(final double leftSpeed, final double rightSpeed) { // Methods can't be inside of each other...
      leftDriveMaster.set(ControlMode.PercentOutput, leftSpeed);
      rightDriveMaster.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void turnRight(final double leftSpeed, final double rightSpeed) {
      leftDriveMaster.set(ControlMode.PercentOutput, leftSpeed);  
      rightDriveMaster.set(ControlMode.PercentOutput, -rightSpeed);
    }

    public void turnLeft(final double leftSpeed, final double rightSpeed) {
      leftDriveMaster.set(ControlMode.PercentOutput, -leftSpeed);  
      rightDriveMaster.set(ControlMode.PercentOutput, rightSpeed);
    }
    
      public void stopDrivetrain() {
        driveState = States.STOPPED;
      }

      public void TeleOpDrive(final double straight, final double turn) {
        straightPower = straight;
        turnPower = turn;
        driveState = States.TELEOP_DRIVE;
      }
      

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  
  }
}

