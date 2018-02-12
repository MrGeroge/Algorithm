package leetcode;

/**
 * Created by George on 2017/12/11.
 */
public class ConvertSortedListToBinarySearchTree {//构造一个高度平衡(每个节点的左右子树深度差不超过1)的二叉查找树(每个节点的val比其所有左子树上的节点val都大，比其所有右子树上的节点val都小)
    public TreeNode sortedListToBST(ListNode head) {
        return toBST(head, null);
    }

    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        // 申请两个指针，fast移动速度是low的两倍
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);

        return root;
    }
}
