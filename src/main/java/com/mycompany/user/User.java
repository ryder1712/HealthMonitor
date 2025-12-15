package com.mycompany.user;

public class User {
    private String name;
    private int age;
    private float weight;
    private int height;
    
    public User (String name, int age, float weight, int height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public float getWeight() {
        return weight;
    }
    
    public int getHeight() {
        return height;
    }
}
