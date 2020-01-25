/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.vision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class ll {

    public static void output(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    
    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    System.out.println("LimelightX" + x);
    }

    public static void ledBLINK(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(2);
    }

    public static void ledON(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
    
    public static void ledOFF(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }
    
    public static void gettv(){
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        SmartDashboard.putNumber("Limelight tv", tv);
        System.out.println("tv "+tv);
        System.out.println(" ");
    }

    public static void gettx(){
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        SmartDashboard.putNumber("Limelight tx", tx);
        System.out.println("tx "+ tx);
        System.out.println(" ");      

    }


    public static double returntx(){
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        return tx;
    }
    
    public static void getts(){
        double ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
        SmartDashboard.putNumber("Limelight ts", ts);
        System.out.println("ts "+ ts);
        System.out.println(" ");
    }

    public static void getty(){
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        SmartDashboard.putNumber("Limelight ty", ty);
        System.out.println("ty "+ty);
        System.out.println(" ");
    }

}