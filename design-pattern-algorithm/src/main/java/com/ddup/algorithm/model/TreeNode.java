package com.ddup.algorithm.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.sun.management.HotSpotDiagnosticMXBean.ThreadDumpFormat.JSON;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<V> {
    private V data;
    private TreeNode<V> left;
    private TreeNode<V> right;

    public TreeNode(V data) {
        this.data = data;
    }

    /**
     * 左旋转
     */
    public void leftRotate() {
        // 以当前节点的值创建一个新节点
        TreeNode newNode = new TreeNode(data);
        // 把新节点的左子树设置为当前节点的左子树
        newNode.left = left;
        // 新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        // 当前节点的值替换为右节点的值
        data = right.data;
        // 当前节点的右子树设置为右子树的右子树
        right = right.right;
        // 当前节点的左子树设置为新节点
        left = newNode;
    }

    public void rightRotate() {
        TreeNode newNode = new TreeNode(data);
        newNode.right = right;
        newNode.left = left.right;
        data = left.data;
        left = left.left;
        right = newNode;
    }

    /**
     * 获取当前节点的高度
     *
     * @return
     */
    public int getHeight() {
        return Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight()) + 1;
    }

    /**
     * 获取左子树的高度
     *
     * @return
     */
    public int getLeftHeight() {
        if (left == null) {
            return 0;
        }
        return left.getHeight();
    }

    /**
     * 获取右子树的高度
     *
     * @return
     */
    public int getRightHeight() {
        if (right == null) {
            return 0;
        }
        return right.getHeight();
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }


}