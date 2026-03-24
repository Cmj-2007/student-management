package mapper;

import entity.Student;

import java.util.*;


/**
 * 数据访问层实现类：用HashMap模拟数据库，实现StudentMapper接口的方法
 * 职责：仅处理数据存储和读取，不做任何业务校验（校验交给Service层）
 */
public class StudentMapperImpl implements StudentMapper {

    // 用HashMap存储学生数据：key=学号（唯一），value=学生对象
    private final Map<String, Student> studentMap = new HashMap<>();

    @Override
    public boolean add(Student student) {
        // 若学号已存在，新增失败（返回false）；否则存入map（返回true）
        String studentId = student.getStudentId();
        if (studentMap.containsKey(studentId)) {
            return false;
        }
        studentMap.put(studentId, student);
        return true;
    }

    //TODO 请在此处补全操作HashMap数据库所需的方法的实现
    @Override
    public ArrayList<Student> allStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public Student findStudentByStudentId(String studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public ArrayList<Student> findStudentByClassName(String className) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (className.equals(student.getClassName())) {
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        if (!studentMap.containsKey(studentId)) {
            return false;
        }
        studentMap.remove(studentId);
        return true;
    }

    @Override
    public boolean updateStudent(Student student) {
        String studentId = student.getStudentId();
        if (!studentMap.containsKey(studentId)) {
            return false;
        }

        Student s = studentMap.get(studentId);
        if (student.getName() != null) {
            s.setName(student.getName());
        }
        if (student.getGender() != null) {
            s.setGender(student.getGender());
        }
        if (student.getAge() > 0) {
            s.setAge(student.getAge());
        }
        if (student.getClassName() != null && !student.getClassName().isEmpty()) {
            s.setClassName(student.getClassName());
        }
        if (student.getMajor() != null && !student.getMajor().isEmpty()) {
            s.setMajor(student.getMajor());
        }

        studentMap.put(studentId,s);
        return true;
    }
}