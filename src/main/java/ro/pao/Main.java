package ro.pao;

import ro.pao.application.Menu;
import ro.pao.gateways.Requests;
import ro.pao.model.Name;
import ro.pao.model.Teacher;
import ro.pao.model.abstracts.User;
import ro.pao.repository.TeacherRepository;
import ro.pao.repository.impl.TeacherRepositoryImpl;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            Menu menu = Menu.getInstance();

            menu.intro();
            //menu.makeRequest();

            menu.writeTeachersToCSV();
            if ("exit".equals(scanner.next())) {
                break;
            }
        }
    }
}