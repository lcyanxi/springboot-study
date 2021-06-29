package com.lcyanxi.basics.leetcode.binaryTree;

import lombok.Builder;
import lombok.Data;

/**
 * 给定一个二叉树的头节点，返回这颗二叉树中最大的二叉搜索子树的size
 * 思路： 二叉搜索树：左子树（max） < head   右子树的(min) < head
 * @author lichang
 * @date 2021/4/1
 */
public class CompleteBinaryTree {

    public static void main(String[] args) {
        Node node = new Node(10,new Node(6,new Node(9,null,null),null),
                new Node(15,new Node(13,null,null),new Node(16,null,null)));
        System.out.println(process(node));

    }

    private static InfoDTO process(Node head){
        if (head == null){
            return null;
        }

        Integer min = head.getValue();
        Integer max = head.getValue();

        InfoDTO leftDTO = process(head.getLeft());
        InfoDTO rightDTO = process(head.getRight());


        if (leftDTO != null ){
            min = Math.min(min,leftDTO.getMin());
            max = Math.max(max,leftDTO.getMax());
        }
        if (rightDTO != null){
            min = Math.min(min,rightDTO.getMin());
            max = Math.max(max,rightDTO.getMax());
        }

        boolean isAllBST = false;
        Integer maxSubBSTSize = 0;

        if (leftDTO != null){
            maxSubBSTSize = leftDTO.getMaxSubBSTSize();
        }
        if (rightDTO != null){
            maxSubBSTSize = Math.max(maxSubBSTSize,rightDTO.getMaxSubBSTSize());
        }

        // 判断是不是二叉树搜索树：
        /* 1 如果左右节点为null  是二叉搜索树
           2 左子树的max < head  右子树的最小min > head
         */
        if (
                (leftDTO == null ? true : leftDTO.isAllBST())
                && (rightDTO == null ? true : rightDTO.isAllBST())
                && (leftDTO == null ? true : leftDTO.getMax() < head.getValue())
                && (rightDTO == null ? true : rightDTO.getMin() > head.getValue())
        ){
            isAllBST = true;
            maxSubBSTSize = (leftDTO == null ? 0 : leftDTO.getMaxSubBSTSize()) +
                    (rightDTO == null ? 0 : rightDTO.getMaxSubBSTSize()) + 1;
        }
         return InfoDTO.builder().isAllBST(isAllBST).max(max).min(min).maxSubBSTSize(maxSubBSTSize).build();

    }
}

@Data
@Builder
class InfoDTO{
    // 是否全是二叉搜索树
    private boolean isAllBST;
    // 全是二叉搜索树的最大size
    private Integer maxSubBSTSize;
    // 最大值
    private Integer min;
    // 最小值
    private Integer max;
}