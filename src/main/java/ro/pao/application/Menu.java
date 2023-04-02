package ro.pao.application;

import ro.pao.model.*;
import ro.pao.model.abstracts.User;
import ro.pao.model.enums.CourseName;
import ro.pao.service.*;
import ro.pao.service.impl.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

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
    private final TeacherService teacherService = new TeacherServiceImpl();
    private final QuizService quizService = new QuizServiceImpl();
    private final AnsweredQuizService answeredQuizService = new AnsweredQuizServiceImpl();

    public static Menu getInstance() {
        return (INSTANCE == null ? new Menu() : INSTANCE);
    }

    public void intro() {
        String intro = """
                Welcome to the platform
                """;

        System.out.println(intro);

        ReportCard card1 = ReportCard.builder().grades(new HashMap<>()).build();
        reportCardService.addOnlyOne(card1);

        Teacher teacher1 = Teacher.builder().id(UUID.randomUUID()).name(new Name("Popescu", "Dan")).passwdHash(User.hashPasswd("1234")).build();
        teacherService.addOnlyOne(teacher1);

        Course poo = Course.builder().id(UUID.randomUUID()).students(new HashSet<>()).quizes(new ArrayList<>()).year(Year.now()).name(CourseName.OOP).build();

        courseService.addOnlyOne(poo);

        reportCardService.addGrade(card1.getId(), 4.3, poo);


        Student student1 = Student.builder().id(UUID.randomUUID()).name(new Name("Teodorescu", "George")).userName("george")
                .passwdHash(User.hashPasswd("12345")).reportCard(card1).build();
        studentService.addOnlyOne(student1);

        Student student2 = Student.builder().id(UUID.randomUUID()).name(new Name("Ionescu", "Andrei")).userName("andrei")
                .passwdHash(User.hashPasswd("12345")).reportCard(card1).build();
        studentService.addOnlyOne(student2);

        System.out.println(reportCardService.getById(card1.getId()).get().getGrades().get(poo.getId()));

        Question q1 = Question.builder().id(UUID.randomUUID()).text("q1").options(new ArrayList<>()).answer(1).points(5.0).build();

        questionService.addOnlyOne(q1);
        questionService.addOption(q1.getId(),"dasfds");
        questionService.addOption(q1.getId(),"asdfds");
        questionService.addOption(q1.getId(), "asd");

        Question q2 = Question.builder().id(UUID.randomUUID()).text("q2").options(new ArrayList<>()).points(5.0).answer(2).build();

        questionService.addOnlyOne(q2);
        questionService.addOption(q2.getId(),"dasfds");
        questionService.addOption(q2.getId(),"asdfds");
        questionService.addOption(q2.getId(), "asd");




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

    public void getAllStudents() {
        String intro = """
                A list of students
                """;

        System.out.println(intro);

        studentService.getAllFromMap().stream().sorted(Comparator.comparing(Student::getName)).forEach(student ->
                System.out.println(student));
    }

    public void getAllCourses(){
        String intro = """
                 A list of courses
                 """;

        System.out.println(intro);

        courseService.getAllFromMap().stream().forEach(c -> System.out.println(c));

    }

    public void addQuiz(){
        String intro = """
                Added a quiz
                """;

        Quiz quiz1 = Quiz.builder().id(UUID.randomUUID()).course(courseService.getAllFromMap().get(0)).questionList(new ArrayList<>()).build();
        quizService.addOnlyOne(quiz1);

        quizService.addQuestion(quiz1.getId(), questionService.getAllFromMap().get(0));
        quizService.addQuestion(quiz1.getId(), questionService.getAllFromMap().get(1));

        System.out.println(quizService.getAllFromMap().get(0));
    }

    public void gradeQuiz(){
        List<Integer> answers = new ArrayList<>();
        answers.add(1);
        answers.add(2);
        AnsweredQuiz answeredQuiz = AnsweredQuiz.builder().id(UUID.randomUUID()).quiz(quizService.getAllFromMap().get(0)).answers(answers)
                .student(studentService.getAllFromMap().get(0)).build();

        answeredQuizService.addOnlyOne(answeredQuiz);

        answeredQuizService.grade(answeredQuiz.getId());
        System.out.println(answeredQuiz);
        System.out.println(reportCardService.getAllFromMap().get(0));
    }


}
