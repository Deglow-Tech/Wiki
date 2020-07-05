package com.example.ejemplonavegacion.Entities;

public class Contact {

    private String name;
    private String phone;
    private String priority;

    public Contact(String name, String phone, String priority){
         this.name = name;
         this.phone = phone;
         this.priority = priority;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getPriority(){
        return priority;
    }

    @Override
    public String toString(){
        return this.name + " " + this.phone + " " + this.priority;
    }
}
