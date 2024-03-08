package com.nhnacademy.shoppingmall.thread;

public class UserIdStore {
    private UserIdStore(){
    }
    private static final ThreadLocal<Long> userIdLocal = new ThreadLocal<>();

    public static void setUserId(String userId){
        userIdLocal.set(Long.valueOf(userId));
    }

    public static Long getUserId(){
        return userIdLocal.get();
    }

    public static void remove(){
        userIdLocal.remove();
    }
}
