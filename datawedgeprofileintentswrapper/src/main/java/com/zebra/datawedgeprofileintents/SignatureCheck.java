package com.zebra.datawedgeprofileintents;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureCheck {

    public static String getSignature(Context context)
    {
        String sha1Signature = "";
        try {
            Signature[] signatures = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            for (Signature sig : signatures) {
                sha1Signature = getSHA1(sig.toByteArray());
            }
        }

        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return sha1Signature;
    }

    private static String getSHA1(byte[] sig)
    {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA1");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(sig);
        byte[] hashtext = digest.digest();
        return bytesToHex(hashtext);
    }

    private static String bytesToHex(byte[] bytes)
    {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}