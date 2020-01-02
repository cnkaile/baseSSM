package com.nouser.test;

import java.util.ArrayList;
import java.util.List;

public class F1 {
    public List<Server> servers;

    private int currendIndex;
    private int totalServer;

    public F1() {
        servers = new ArrayList<>();
        servers.add(new Server("192.168.1.1"));
        servers.add(new Server("192.168.1.2"));
        servers.add(new Server("192.168.1.3"));
        servers.add(new Server("192.168.1.4"));
        servers.add(new Server("192.168.1.5"));
        servers.add(new Server("192.168.1.6"));
        this.totalServer = servers.size();
        this.currendIndex = totalServer - 1;
    }

    public synchronized Server around(){
        currendIndex = (currendIndex + 1) % totalServer;
        return servers.get(currendIndex);
    }

    public static void main(String[] args) {
        F1 f1 = new F1();
        for (int i = 0; i < 50; i ++){
            System.out.println(f1.around());
        }
    }
}
