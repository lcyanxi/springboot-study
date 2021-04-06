package com.lcyanxi.leetcode.Linked;

import lombok.Builder;
import lombok.Data;

/**
 * @author lichang
 * @date 2021/4/1
 */
@Data
@Builder
public class LinkedNode {

    private Integer value;

    private LinkedNode next;
}
