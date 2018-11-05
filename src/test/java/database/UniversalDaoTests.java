package database;

import org.junit.jupiter.api.Test;
import system.dao.Dao;
import system.model.users.Administrator;
import system.model.users.Teacher;
import system.model.users.UserType;

import java.util.Arrays;
import java.util.LinkedList;

public class UniversalDaoTests {

    @Test
    void testUniversalDaoWithTypesCreation() {
        LinkedList<Integer> classesKeys = new LinkedList<>(Arrays.asList(UserType.TEACHER.ordinal(), UserType.ADMINISTRATOR.ordinal()));
        LinkedList<Class> classes = new LinkedList<>(Arrays.asList(Teacher.class, Administrator.class));
        Dao dao = new Dao("Users", classesKeys, classes);

    }
}
