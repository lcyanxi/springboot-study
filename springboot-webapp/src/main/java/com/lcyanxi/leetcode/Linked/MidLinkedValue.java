package com.lcyanxi.leetcode.Linked;


/**
 * 输入链表头节点、奇数长度返回中点、偶数长度返回上中点
 * @author lichang
 * @date 2021/4/1
 */
public class MidLinkedValue {
    public static void main(String[] args) {

        LinkedNode node = new LinkedNode(1,new LinkedNode(2,new LinkedNode(3,
                new LinkedNode(4,new LinkedNode(5,new LinkedNode(6,
                        new LinkedNode(7,new LinkedNode(8,null))))))));

        System.out.println(process(node).getValue());
        System.out.println(process2(node).getValue());

    }

    /**
     * 输入链表头节点、奇数长度返回中点、偶数长度返回上中点
     * @param head 头节点
     * @return
     */
    private static LinkedNode process(LinkedNode head){
        if (head == null || head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }

        LinkedNode slow  = head.getNext();
        LinkedNode first = head.getNext().getNext();
        while (first.getNext() != null && first.getNext().getNext() != null){
            slow = slow.getNext();
            first = first.getNext().getNext();
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * @param head 头节点
     * @return
     */
    private static LinkedNode process2(LinkedNode head){
        if (head == null || head.getNext() == null){
            return  head;
        }
        if (head.getNext().getNext() == null){
            return head.getNext();
        }
        LinkedNode slow = head;
        LinkedNode first = head.getNext();
        while (first.getNext() != null && first.getNext().getNext() != null){
            slow = slow.getNext();
            first = first.getNext().getNext();
        }
        return slow.getNext();
    }


    /**
     * 输入链表头节点，奇数长度返回中点前一个节点，偶数长度返回上中点的前一个节点
     * @param head 头节点
     * @return
     */
    private static LinkedNode process3(LinkedNode head){
        if (head == null || head.getNext() == null || head.getNext().getNext() == null){
            return null;
        }
        LinkedNode temp = head;
        LinkedNode slow = temp.getNext();
        LinkedNode first = temp.getNext().getNext();
        while (first.getNext() != null && first.getNext().getNext() != null){
            temp = slow;
            slow = slow.getNext();
            first = first.getNext().getNext();
        }
        return temp;
    }
}
