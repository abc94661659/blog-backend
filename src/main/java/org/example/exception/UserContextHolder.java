package org.example.exception;

public class UserContextHolder {
    private static final ThreadLocal<Integer> userIdHolder = new ThreadLocal<>();

    public static Integer getUserId() {
        return userIdHolder.get();
    }

    public static void setUserId(Integer id) {
        userIdHolder.set(id);
    }

    public static void clear() {
        userIdHolder.remove();
    }
}
