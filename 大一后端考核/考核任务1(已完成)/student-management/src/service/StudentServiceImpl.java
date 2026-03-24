package service;


import entity.Student;
import mapper.StudentMapper;

import java.util.ArrayList;

/**
 * 业务逻辑层实现类：实现具体业务逻辑，依赖Mapper层进行数据操作
 * 职责：处理数据校验、业务规则，调用Mapper层方法完成数据操作
 */
public class StudentServiceImpl implements StudentService {

    // 依赖Mapper层（通过构造方法注入，解耦且便于测试）
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public String addStudent(Student student) {
        // 1. 校验必填字段
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return "新增失败：学号不能为空！";
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "新增失败：姓名不能为空！";
        }

        // 2. 校验性别合法性
        if (!"男".equals(student.getGender()) && !"女".equals(student.getGender())) {
            return "新增失败：性别必须为'男'或'女'！";
        }

        // 3. 校验年龄范围
        if (student.getAge() < 18 || student.getAge() > 25) {
            return "新增失败：年龄必须在18-25岁之间！";
        }

        // 4. 调用Mapper层新增，返回结果
        boolean isSuccess = studentMapper.add(student);
        return isSuccess ? "新增成功！" : "新增失败：学号已存在！";
    }

    @Override
    public String getStudentByStudentId(String studentId) {
        //TODO 请在此处补全业务逻辑
        if (studentId == null) {
            return "查询失败！查询学号不能为空！";
        }
        Student student = studentMapper.findStudentByStudentId(studentId);
        if (student == null) {
            return "学号为：" + studentId + "的学生不存在！";
        }
        return StudentInfo(student);
    }

    @Override
    public String getStudentsByClassName(String className) {
        //TODO 请在此处补全业务逻辑
        if (className == null) {
            return "查询失败！查询班级不能为空！";
        }
        ArrayList<Student> students = studentMapper.findStudentByClassName(className);
        if (students.isEmpty()) {
            return "该班级：" + className + "暂无学生！";
        }
        return StudentsInfo(students);
    }

    @Override
    public String getAllStudents() {
        //TODO 请在此处补全业务逻辑
        ArrayList<Student> students = studentMapper.allStudents();
        if (students.isEmpty()) {
            return "当前无学生信息，请添加后查询";
        }

        return StudentsInfo(students);
    }

    @Override
    public String updateStudent(Student student) {
        //TODO 请在此处补全业务逻辑
        if (student.getStudentId() == null) {
            return "修改失败！学号不能为空";
        }

        Student s = studentMapper.findStudentByStudentId(student.getStudentId());
        if (s == null){
            return "修改失败！该学生不存在！";
        }

        if (student.getGender() != null && !student.getGender().isEmpty()) {
            if (!"男".equals(student.getGender()) && !"女".equals(student.getGender())) {
                return "修改失败！性别不存在！";
            }
        }

        if (student.getAge() > 0) {
            if (student.getAge() < 18 || student.getAge() > 25) {
                return "修改失败！年龄必须在18-25岁之间！";
            }
        }

        boolean flag = studentMapper.updateStudent(student);

        return flag ? "修改成功！" : "修改失败！";
    }

    @Override
    public String deleteStudent(String studentId) {
        //TODO 请在此处补全业务逻辑
        if (studentId == null) {
            return "删除失败！学号不能为空！";
        }
        Student student = studentMapper.findStudentByStudentId(studentId);
        if (student == null) {
            return "学生不存在！";
        }
        boolean flag = studentMapper.deleteStudent(studentId);

        return flag ? "删除成功！" : " 删除失败！";
    }

    public String StudentInfo(Student student) {
        return "学号：" + student.getStudentId() +
                "，姓名：" + student.getName() +
                "，性别：" + student.getGender() +
                "，年龄：" + student.getAge() +
                "，班级：" + student.getClassName() +
                "，专业：" + student.getMajor();
    }

    public String StudentsInfo(ArrayList<Student> students) {
        StringBuilder info = new StringBuilder();
        info.append("共找到" + students.size() + "名学生：\n");
        for (Student student : students) {
            info.append(StudentInfo(student));
            info.append("\n");
        }
        return info.toString();
    }

}