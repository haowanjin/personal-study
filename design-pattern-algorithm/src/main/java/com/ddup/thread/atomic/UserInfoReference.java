package com.ddup.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class UserInfoReference {
    public static void main(String[] args) {
        UserInfo user = new UserInfo("Jack", 20);
        AtomicReference<UserInfo> ac = new AtomicReference<>(user);

        UserInfo user1 = ac.get();
        System.out.println(user1);

        user.setAge(18);
        user.setName("Rose");
        System.out.println(ac.get());

        ac.compareAndSet(user1, user);
        System.out.println(ac.get());
        System.out.println(user1);
    }

    public static class UserInfo {
        private String name;
        private Integer age;

        public UserInfo(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
