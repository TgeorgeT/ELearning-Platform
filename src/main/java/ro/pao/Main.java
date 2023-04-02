package ro.pao;

import ro.pao.application.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Menu menu = Menu.getInstance();

            menu.intro();
            menu.getAllStudents();
            menu.getAllCourses();
            menu.addQuiz();
            menu.gradeQuiz();
            menu.getAllTeachers();
            menu.getAllQuizzes();
            menu.removeQuiz();
            menu.orderedQuizzes();
            if ("exit".equals(scanner.next())) {
                break;
            }
        }
    }
}