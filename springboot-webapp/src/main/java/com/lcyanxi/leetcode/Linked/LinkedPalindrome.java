package com.lcyanxi.leetcode.Linked;

import java.util.Stack;

/**
 * 给定一个链表头节点，判断链表是否是回文链表
 * @author lichang
 * @date 2021/4/2
 */
public class LinkedPalindrome {

    public static void main(String[] args) {
        LinkedNode node = new LinkedNode(1,new LinkedNode(2,new LinkedNode(3,
                new LinkedNode(2,new LinkedNode(1,null)))));
        System.out.println(process(node));
    }

    private static boolean process(LinkedNode head){
        if (head == null){
            return true;
        }
        LinkedNode temp = head;
        Stack stack = new Stack();
        while (temp != null){
            stack.add(temp.getValue());
            temp = temp.getNext();
        }

        while (head != null){
            Object value =  stack.pop();
            if (head.getValue().equals(value)){
                return false;
            }
            head = head.getNext();
        }
        return true;
    }
}
