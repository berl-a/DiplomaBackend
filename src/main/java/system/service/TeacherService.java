package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.TeacherDao;
import system.model.Teacher;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    private boolean getCachedTeachers = false;
    private LinkedList<Teacher> cachedTeachers = new LinkedList<>();

    public LinkedList<Teacher> getTeachers() {
        if(!getCachedTeachers)
            cachedTeachers = teacherDao.getTeachers();

        return cachedTeachers;
    }

    public LinkedList<Teacher>  getUniqueTeachers() {
        if(!getCachedTeachers)
            cachedTeachers = teacherDao.getTeachers();

        return new LinkedList<>(
                cachedTeachers
                        .stream()
                        .sorted(Comparator.comparing(t -> (t.getTytul() + t.getName() + t.getSurname())))
                        .collect(Collectors.toList())
        );
    }

    public Teacher getTeacherWithTytulAndNameAndSurname(String combination) {
        boolean prevCachedOption = getCachedTeachers;
        getCachedTeachers = false;
        getTeachers();
        getCachedTeachers = prevCachedOption;

        System.out.println("Comb is " + combination);
        return cachedTeachers
                .stream()
                .filter(t -> {
//                    System.out.print(combination + (combination.equalsIgnoreCase(t.getTytul() + " " + t.getName() + " " + t.getSurname()) ? " is " : " is not ") + "equal to " + (t.getTytul() + " " + t.getName() + " " + t.getSurname()));
                    return combination.equals((t.getTytul() + " " + t.getName() + " " + t.getSurname()));
                })
                .findAny()
                .get();
    }

    public boolean isGetCachedTeachers() {
        return getCachedTeachers;
    }

    public void setGetCachedTeachers(boolean getCachedTeachers) {
        this.getCachedTeachers = getCachedTeachers;
    }

}
