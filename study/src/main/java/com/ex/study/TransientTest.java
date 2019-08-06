package com.ex.study;

import java.io.*;

//https://www.cnblogs.com/lanxuezaipiao/p/3369962.html
public class TransientTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\user.txt"));
        UserInfo userInfo = new UserInfo();
        userInfo.setName("lxb");
        UserInfo.setPassword("123456");
        outputStream.writeObject(userInfo);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\user.txt"));
        UserInfo user =(UserInfo) inputStream.readObject();
        inputStream.close();
        System.out.println(user);
    }
}

class UserInfo implements Serializable{
    private transient String name;
    private static String password;

    public UserInfo(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserInfo.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
