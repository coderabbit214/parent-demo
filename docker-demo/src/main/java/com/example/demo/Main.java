package com.example.demo;

/**
 * @Author: Mr_J
 * @Date: 2022/1/22 9:47 下午
 */
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setData("第一个节点");

        Node node2 = new Node();
        node2.setData("第二个节点");
        node1.setNextNode(node2);
        System.out.println(node1);
//        System.out.println(node2);
    }
}

