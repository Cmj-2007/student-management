package mapper;

import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据访问层接口：定义学生数据的CRUD操作规范
 * 职责：仅声明数据操作方法，不涉及业务逻辑和实现
 */
public interface StudentMapper {

    /**
     * 新增学生
     * @param student 学生对象（包含学号等信息）
     * @return 新增成功返回true，失败返回false（如学号已存在时返回false）
     */
    boolean add(Student student);

    //TODO 请在此处补全操作HashMap所需的方法
    //查询所有学生
    ArrayList<Student> allStudents();
    //根据学号查询
    Student findStudentByStudentId(String studentId);
    //根据班级查询
    ArrayList<Student> findStudentByClassName(String className);
    //删除学生
    boolean deleteStudent(String studentId);
    //修改学生
    boolean updateStudent(Student student);
}