public class ReverseLinkedList {
  static class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
  }

  public static ListNode reverse(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
      ListNode nxt = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nxt;
    }
    return prev;
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    a.next = new ListNode(2);
    a.next.next = new ListNode(3);
    ListNode result = reverse(a);
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }
}
