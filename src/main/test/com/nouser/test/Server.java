package com.nouser.test;

/**
 * 负载均衡算法使用
 * @author zhoukl
 */
public class Server {
    private String ip;
    private int weight;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Server() {
    }

    @Override
    public String toString() {
        return "Server{" +
                "ip='" + ip + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public Server(String ip) {

        this.ip = ip;
    }
}
