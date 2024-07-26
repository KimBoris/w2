package org.example.w2.common;

public class StringUtil {

    //만약 없으면 default값을 반환하기로 한다.
    public static int getInt(String str, int defaultValue) {

        if(str == null || str.length() == 0) {
            return defaultValue;
        }

        try{
            return Integer.parseInt(str);
        }
        catch(NumberFormatException e){
            return defaultValue;
        }
    }
}
