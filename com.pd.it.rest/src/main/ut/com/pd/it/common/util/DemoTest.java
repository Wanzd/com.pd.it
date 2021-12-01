package com.pd.it.common.util;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

class DemoTest {
    private static void calShortestStr(String str){
        char[] chars = str.toCharArray();
        char[] dictChars = new char[chars.length];
        System.arraycopy(chars, 0, dictChars, 0, chars.length);
        Arrays.sort(dictChars);
        int idx1=-1;
        int idx2=-1;
        char smallChar=dictChars[0];
        char bigChar=dictChars[0];
        for(int i=0;i<chars.length;i++) {
            if(chars[i]!=dictChars[i]) {
                idx1=i;
                smallChar=dictChars[i];
                break;
            }
        }
        if(idx1!=-1) {
            for(int i=0;i<chars.length;i++) {
                if(chars[i]==smallChar) {
                    idx2=i;
                    bigChar=chars[idx1];
                    break;
                }
            }
            chars[idx1]=smallChar;
            chars[idx2]=bigChar;
        }
        String rs=new String(chars);
        System.out.println(rs);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chars=str.toCharArray();
        calShortestStr(str);
    }

}
