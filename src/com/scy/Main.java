package com.scy;

import com.scy.tree.BST;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BST<Integer> bst = new BST<>();
        int[] nums = {13, 6, 12, 5, 4, 21, 66};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        bst.preOrder();
    }
}
