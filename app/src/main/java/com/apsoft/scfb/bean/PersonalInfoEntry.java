package com.apsoft.scfb.bean;

/**
 * Created by admin on 2016/8/12.
 */
public class PersonalInfoEntry {
    Person data;

    public Person getData() {
        return data;
    }

    public void setData(Person data) {
        this.data = data;
    }

    public static class Person{
        private String name;
        private String idcard;
        private String address;
        private String phone;
        private Integer sex;
        private Integer height;
        private Integer weight;
        private Integer age;
        private String position;
        private String policyno;
        private String flag;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getPolicyno() {
            return policyno;
        }

        public void setPolicyno(String policyno) {
            this.policyno = policyno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Person(){

        }


        public Person(String name, String idcard, String address, String phone, Integer sex, Integer height, Integer weight, Integer age, String position) {
            this.name = name;
            this.idcard = idcard;
            this.address = address;
            this.phone = phone;
            this.sex = sex;
            this.height = height;
            this.weight = weight;
            this.age = age;
            this.position = position;
        }
    }
}
