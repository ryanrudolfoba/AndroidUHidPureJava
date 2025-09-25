package com.android.commands.hid;

import android.os.SystemClock;

//usage: export CLASSPATH=/sdcard/classes.dex;app_process / com.android.commands.hid.Hid

public class Hid {

    private static final byte[] hidGamePadDescriptor = new byte[]{
            0x05, 0x01,  //Usage Page (Generic Desktop Ctrls)
            0x09, 0x05,  //Usage (Game Pad)
            (byte) 0xA1, 0x01,  //Collection (Application)
            0x05, 0x09,  //  Usage Page (Button)
            0x19, 0x01,  //  Usage Minimum (Button 1)
            0x29, 0x10,  //  Usage Maximum (Button 16)
            0x15, 0x00,  //  Logical Minimum (0)
            0x25, 0x01,  //  Logical Maximum (1)
            0x75, 0x01,  //  Report Size (1)
            (byte) 0x95, 0x10,  //  Report Count (16)
            (byte) 0x81, 0x02,  //  Input (Data,Var,Abs,No Wrap,Linear,Preferred State,No Null Position)
            0x05, 0x01,  //  Usage Page (Generic Desktop Ctrls)
            0x15, (byte) 0x81,  //  Logical Minimum (-127)
            0x25, 0x7F,  //  Logical Maximum (127)
            0x09, 0x30,  //  Usage (X)
            0x09, 0x31,  //  Usage (Y)
            0x09, 0x32,  //  Usage (Z)
            0x09, 0x35,  //  Usage (Rz)
            0x75, 0x08,  //  Report Size (8)
            (byte) 0x95, 0x04,  //  Report Count (4)
            (byte) 0x81, 0x02,  //  Input (Data,Var,Abs,No Wrap,Linear,Preferred State,No Null Position)
            (byte) 0xC0,        //End Col
    };

    // The gamePadCode array is 6 bytes long and represents the report data for a hid gamePad.
    // gamePadCode[0] and gamePadCode[1] represents the buttons' states of the gamePad. Assigned by using the formula: gamePadCode[0] |= 1; gamePadCode[0] &= ~1;
    // gamePadCode[2] represents the X axis value of the gamePad. Assigned normally.
    // gamePadCode[3] represents the Y axis value of the gamePad. Assigned normally.
    // gamePadCode[4] represents the Z axis value of the gamePad. Assigned normally.
    // gamePadCode[5] represents the RZ axis value of the gamePad. Assigned normally.
    public static byte[] gamePadCode = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public static void main(String[] args) {

        //Check permission.
        int uid = android.os.Process.myUid();
        if (uid != 0 && uid != 2000) {
            System.err.printf("Insufficient permission! Need to be launched by adb (uid 2000) or root (uid 0), but your uid is %d \n", uid);
            System.exit(255);
            return;
        }


        // We create the gamepad uHid device called Xbox Wireless Controller. This is for CoD Mobile to make non-supported controller work.
        // The id, name, vid, pid, and bus parameters do not matter. Only the descriptor parameter matters.

        Device gamePad = new Device(3, "Xbox Wireless Controller", "3",2345, 6789, 0x03, hidGamePadDescriptor, gamePadCode, null, null);

        System.out.println("Xbox Wireless controller uhid gamepad successfully created.");

    }
}
