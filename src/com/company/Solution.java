package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<Integer> hashs = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            hashs.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++){
            int sum = nums[i];
            for (int j = 0; j < nums.length; j++){
                if (hashs.contains(sum + nums[j])) result.add(Arrays.asList(sum, nums[j], sum - nums[j]));
            }
        }
        return result;
    }
}
