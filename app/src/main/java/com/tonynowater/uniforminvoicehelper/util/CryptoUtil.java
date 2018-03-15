package com.tonynowater.uniforminvoicehelper.util;

/**
 * Created by tonyliao on 2018/3/15.
 */

public class CryptoUtil {
    static String hash_hmac(String type, String value, String key)
    {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance(type);
            javax.crypto.spec.SecretKeySpec secret = new javax.crypto.spec.SecretKeySpec(key.getBytes(), type);
            mac.init(secret);
            byte[] digest = mac.doFinal(value.getBytes());
            StringBuilder sb = new StringBuilder(digest.length*2);
            String s;
            for (byte b : digest){
                s = Integer.toHexString(Integer.valueOf(b));
                if(s.length() == 1) sb.append('0');
                sb.append(s);
            }
            return sb.toString();
        } catch (Exception e) {
            android.util.Log.v("TAG","Exception ["+e.getMessage()+"]", e);
        }
        return "";
    }
}
