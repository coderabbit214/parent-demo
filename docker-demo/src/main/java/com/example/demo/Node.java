package com.example.demo;

/**
 * @Author: Mr_J
 * @Date: 2022/1/22 9:46 下午
 */
public class Node {
    private String data;
    private Node nextNode;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", nextNode=" + nextNode +
                '}';
    }
}
