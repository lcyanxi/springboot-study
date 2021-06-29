package com.lcyanxi.basics.leetcode.binaryTree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichang
 * @date 2021/3/31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node {
    private Integer value;

    private Node left;

    private Node right;
}
