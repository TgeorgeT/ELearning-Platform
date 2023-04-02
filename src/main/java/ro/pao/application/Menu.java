package ro.pao.application;

import ro.pao.model.*;
import ro.pao.model.abstracts.User;
import ro.pao.model.enums.CourseName;
import ro.pao.service.*;
import ro.pao.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * In Meniu se fac operatiile care pot lua informatii din toate dintre servicile definite.
 * De exemplu, avem definit mai jos `private final ExampleService exampleService = new ExampleServiceImpl();`
 * <p>
 * In cazul in care aveam definit mai multe servicii, la fel, faceam o initializare a serviciile si astfel apelam metode din serviciu.
 */
public class Menu {

    private static Menu INSTANCE;

    private final ExampleService exampleService = new ExampleServiceImpl();
    private final ReportCardService reportCardService = new ReportCardServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();



    public static Menu getInstance() {
        return (INSTANCE == null ? new Menu() : INSTANCE);
    }

    public void intro() {
        String intro = """
                Intro example
                """;

        System.out.println(intro);

        ReportCard card1 = ReportCard.builder().grades(new HashMap<>()).build();
        reportCardService.addOnlyOne(card1);

        Course poo = Course.builder().id(UUID.randomUUID()).name(CourseName.OOP).build();

        courseService.addOnlyOne(poo);

        reportCardService.addGrade(card1.getId(), 4.3, poo);


        Student student1 = Student.builder().id(UUID.randomUUID()).name("George").userName("george")
                .passwdHash(User.hashPasswd("12345")).reportCard(card1).build();
        studentService.addOnlyOne(student1);

        System.out.println(reportCardService.getById(card1.getId()).get().getGrades().get(poo.getId()));

        Question q1 = Question.builder().id(UUID.randomUUID()).text("q1").answer(1).build();

        questionService.addOnlyOne(q1);
        questionService.addOption(q1.getId(),"dasfds");
        questionService.addOption(q1.getId(),"asdfds");
        questionService.addOption(q1.getId(), "asd");

        Question q2 = Question.builder().id(UUID.randomUUID()).text("q2").answer(0).build();

        questionService.addOnlyOne(q1);
        questionService.addOption(q1.getId(),"dasfds");
        questionService.addOption(q1.getId(),"asdfds");
        questionService.addOption(q1.getId(), "asd");





//        ExampleClass exampleClass = ExampleClass.builder()
//                .id(UUID.randomUUID())
//                .creationDate(LocalDate.now()) // data de azi
//                .updateDate(LocalDate.now())
//                .deleteDate(LocalDate.now())
//                .build();
//
//        exampleService.addOnlyOne(exampleClass);
//
//        List<ExampleClass> exampleServiceList = List.of(
//                ExampleClass.builder()
//                        .id(UUID.randomUUID())
//                        .creationDate(LocalDate.of(2023, 03, 22))
//                        .updateDate(LocalDate.now())
//                        .build(),
//                ExampleClass.builder()
//                        .id(UUID.randomUUID())
//                        .creationDate(LocalDate.of(2023, 03, 22))
//                        .updateDate(LocalDate.now())
//                        .build()
//        );
//
//        exampleService.addAllFromGivenList(exampleServiceList);
//
//        System.out.println("Inainte de stergere: ");
//        exampleService.getAllFromList()
//                .forEach(elementFromList -> System.out.println(elementFromList));
//
//
//        System.out.println("Dupa modificare: ");
//        exampleClass.setUpdateDate(LocalDate.of(2, 2, 2));
//        exampleService.modificaElementById(exampleClass.getId(), exampleClass);
//        exampleService.getAllFromList()
//                .forEach(elementFromList -> System.out.println(elementFromList));
//
//        System.out.println("Dupa stergere: ");
//        exampleService.removeElementById(exampleClass.getId());
//        exampleService.getAllFromList()
//                .forEach(elementFromList -> System.out.println(elementFromList));
    }
}
