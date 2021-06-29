package com.lcyanxi.basics.leetcode.binaryTree;

import lombok.Builder;
import lombok.Data;

/**
 * 给你一个颗二叉树的头节点（X），判断这个二叉树是否是平衡二叉树
 * 思路：索取的信息：
 * 1.判断头节点的左节点是否是平衡二叉树，头节点的右节点是否是平衡二叉树
 * 2.最大的高度
 *
 * @author lichang
 * @date 2021/3/31
 */
public class IsBalanceBinaryTree {
    public static void main(String[] args) {

        Node node = new Node(1,null,null);
        node.setLeft(new Node(2,new Node(3,null,null),null));

        System.out.println(process(node));


    }
    private static Info process(Node node){
        if (node == null){
            return Info.builder().height(0).isBalance(true).build();
        }
        Info left = process(node.getLeft());
        Info right = process(node.getRight());

        // 封装我要返回的基础数据
        boolean isBalance = false;

        int height = Math.max(left.getHeight(),right.getHeight()) + 1;

        if (left.isBalance() && right.isBalance() && Math.abs(left.getHeight() - right.getHeight()) < 2){
            isBalance = true;
        }
        return Info.builder().isBalance(isBalance).height(height).build();
    }
}

@Data
@Builder
class Info{
    /**
     * 标记是否平衡
     */
    private boolean isBalance;

    /**
     * 高度
     */
    private int height;
}
