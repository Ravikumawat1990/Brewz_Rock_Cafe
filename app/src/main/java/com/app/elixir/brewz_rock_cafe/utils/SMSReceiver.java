package com.app.elixir.brewz_rock_cafe.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by NetSupport on 06-09-2016.
 */
public class SMSReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages = null;
        String strMessage = "";

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                //   strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                //strMessage += " : ";
                strMessage = messages[i].getMessageBody();
                //strMessage += "\n";
            }

            if (strMessage.contains("Brewz Rock Cafe")) {
                if (strMessage.contains("(OTP)")) {
                    if (strMessage.contains("www.brewzrock.com")) {
                        String s = strMessage.substring(0, 6);
                        CM.setSp(context, "msg", s);
                    }
                }
            }
            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
