package com.hannea.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class
 *
 * @author wgm
 * @date 2017/11/03
 */
public class Md5Util {
    public static String md5(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b=md.digest();

            int temp;
            StringBuffer sb=new StringBuffer("");
            for ( int offset = 0; offset <b.length ; offset++ ) {
                temp=b[offset];
                if(temp<0) temp+=256;
                if(temp<16) sb.append("0");
                sb.append(Integer.toHexString(temp));
            }
            str=sb.toString();

        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        }
        return str;
    }
}
