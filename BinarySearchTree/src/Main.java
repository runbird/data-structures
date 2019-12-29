public class Main {

    public static void main(String[] args) {
	// write your code here
        BST<Integer> bst = new BST<>();
        int[] nums = {4,2,1,3,6,5};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
        Integer integer = bst.removeMax();
        System.out.println(integer);
    }
}
