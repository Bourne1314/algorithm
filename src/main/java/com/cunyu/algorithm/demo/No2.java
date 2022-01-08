package com.cunyu.algorithm.demo;


import org.springframework.boot.SpringApplication;

public class No2 {

    public static void main(String[] args) {
    ListNode listNode = new ListNode(0);
    ListNode listNode1 = new ListNode(3);
    ListNode listNode2 = new ListNode(3);
    }


      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode l3 = new ListNode(0);
        ListNode p = l3;
        while(l1!=null||l2!=null||temp!=0){
            int l1Val=l1!=null?l1.val:0;
            int l2Val=l2!=null?l2.val:0;
            int sumval = l1Val+l2Val+temp;
            temp = sumval/10;
            p.next = new ListNode(sumval%10);
            p=p.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        return l3.next;
    }
}
