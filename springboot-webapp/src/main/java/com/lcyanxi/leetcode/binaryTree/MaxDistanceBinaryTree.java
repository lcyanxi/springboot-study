package com.lcyanxi.leetcode.binaryTree;

import javax.validation.constraints.Max;
import lombok.Builder;
import lombok.Data;

/**
 * 给定一个课二叉数的头节点head，任何两个节点之间都存在距离，返回整课二叉树的最大距离
 * 思路：
 * 1.包含头节点：左树的高度 + 又树的高度 + 1
 * 2.不包含头节点 ： max(左树的高度，右树的高度) + 1
 * @author lichang
 * @date 2021/3/31
 */
public class MaxDistanceBinaryTree {

    public static void main(String[] args) {

        Node node = new Node(10,new Node(11,null,new Node(12,new Node(15,null,null),null)),
                new Node(13,null,null));

        System.out.println(process(node).getMaxDistance());

    }

    private static  InfoData process(Node head){
        if (head == null){
            return InfoData.builder().height(0).maxDistance(0).build();
        }
        InfoData leftNode = process(head.getLeft());
        InfoData rightNode = process(head.getRight());
        // 高度
        int height = Math.max(leftNode.getHeight() ,rightNode.getHeight()) + 1;
        // 左边最大的距离 && 右边最大的距离 && 和高度取max
        int maxDistance = Math.max(Math.max(leftNode.getMaxDistance(),rightNode.getMaxDistance()),
                leftNode.getHeight() + rightNode.getHeight() + 1);

        return InfoData.builder().maxDistance(maxDistance).height(height).build();
    }


}
@Data
@Builder
class InfoData{
    /**
     * 最大距离
     */
    private Integer maxDistance;

    /**
     * 高度
     */
    private Integer height;
}
