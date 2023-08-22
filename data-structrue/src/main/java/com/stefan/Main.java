package com.stefan;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Integer> list = Arrays.asList(3, 4, 1, 5, 6, 2, 7);
        Collections.sort(list, new MyComparator());
        System.out.println(list);

        Student s1 = new Student(23, 98);
        Student s2 = new Student(23, 99);
        Student s3 = new Student(24, 98);
        Student s4 = new Student(25, 97);
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        Collections.sort(studentList);
        System.out.println(studentList);

        Student.B b = new Student.B();
        b.scan();
    }

}

class MyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        // 升序
//        return o1 - o2;
        // 降序
        return o2 - o1;
    }
}

class Student implements Comparable<Student> {

    private int age;

    private int score;

    public Student(int age, int score) {
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        if (this.score != o.score) {
            // 降序
            return o.score - this.score;
        } else {
            // 降序
            return this.age - o.age;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", score=" + score +
                '}';
    }

    static class A {
        public void scan() {
            doScan();
        }

        public void doScan() {
            System.out.println("A.doScan()");
        }
    }

    static class B extends A {
        @Override
        public void doScan() {
            System.out.println("B.doScan()");
        }
    }
}