package com.driver;

import org.apache.tomcat.Jar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String , Student> studentDB = new HashMap<>();
    HashMap<String , Teacher> teacherDB = new HashMap<>();
    HashMap<String , List<String>> studentTeacherDB = new HashMap<>();

    public void addStudent(Student student){
        String studentName = student.getName();
        studentDB.put(studentName, student);
    }

    public void addTeacher(Teacher teacher){
        String teacherName = teacher.getName();
        teacherDB.put(teacherName, teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){

        if(studentTeacherDB.containsKey(teacher)){
            List<String> teacherList = studentTeacherDB.get(teacher); // save student name and make pair
            teacherList.add(student);
            studentTeacherDB.put(teacher, teacherList);
        }
        else {
            List<String> teacherList = new ArrayList<>();
            teacherList.add(student);
            studentTeacherDB.put(teacher, teacherList);
        }
    }

    public Student getStudentByName(String studentName){
        return studentDB.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName){
        return teacherDB.get(teacherName);
    }

    public List<String> getStudentByTeacherName(String teacher) {
        return studentTeacherDB.get(teacher);
    }

    public List<String> getAllStudent(){
        List<String> studentList = new ArrayList<>();
        for(String sName : studentTeacherDB.keySet()){
            studentList.add(sName);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher){
        List<String> studentList = studentTeacherDB.get(teacher);
        for(String name : studentList){
            studentList.remove(name);
        }
        teacherDB.remove(teacher);
        studentTeacherDB.remove(teacher);
    }

    public void deleteAllTeachers(){
        for(List<String> studentList : studentTeacherDB.values()){

            for(String s : studentList){
                studentList.remove(s);
            }

            teacherDB.clear();
            studentTeacherDB.clear();
        }
    }
}
