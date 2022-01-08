package com.cunyu.algorithm.demo;


public class No1 {

    int[] ints  = new int[2];


    public int[] twoSum(int[] nums, int target) {
        int[] array = new int[2];
        if(nums == null||nums.length==0){
            return array;
        }
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    array[0]=i;
                    array[1]=j;
                    return array;
                }
            }
        }
        return array;
    }


}
