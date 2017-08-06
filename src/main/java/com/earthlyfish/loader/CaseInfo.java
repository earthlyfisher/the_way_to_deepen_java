package com.earthlyfish.loader;

/**
 * Created by earthlyfisher on 2017/3/8.
 */
public class CaseInfo {
    private String id;

    private String name;

    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setAllproperty(String id, String name, String address) {
        this.setId(id);
        this.setName(name);
        this.setAddress("China");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("回收我了");
    }
}
