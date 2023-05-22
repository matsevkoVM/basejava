package com.urise.webapp;

public class xz {

    public static void main(String[] args) {
        int m = 10;
        System.out.println(quarterOf(m));
    }

    public static int quarterOf(int month) {
        if (month<4){
            return 1;
        } else if (month < 7) {
            return 2;
        } else if (month < 10){
            return 3;
        } else {
            return 4;
        }
    }
}
