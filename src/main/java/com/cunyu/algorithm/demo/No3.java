package com.cunyu.algorithm.demo;

import org.springframework.boot.SpringApplication;

import java.util.HashSet;
import java.util.Set;

public class No3 {


    public static void main(String[] args) {

         String s = "abcdefga";
         int  al = lengthOfLongestSubstring(s);
        System.out.println(al);
    }

    public static int lengthOfLongestSubstring(String s) {
        int n  = s.length();
        if(n<=1) return n;
        int max = 1;
        for(int i =0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(allUnique(s,i,j)){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }
    private static Boolean allUnique(String s, int start, int end){
        Set<Character> set = new HashSet<>();
        for(int i =start;i<=end;i++){
            if(set.contains(s.charAt(i))){
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }
}
