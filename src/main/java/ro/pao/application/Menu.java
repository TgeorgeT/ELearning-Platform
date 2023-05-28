package ro.pao.application;

import ro.pao.application.csv.CsvWriter;
import ro.pao.gateways.Requests;
import ro.pao.model.*;
import ro.pao.model.abstracts.User;
import ro.pao.model.enums.CourseName;
import ro.pao.repository.StudentRepository;
import ro.pao.repository.impl.StudentRepositoryImpl;
import ro.pao.repository.impl.TeacherRepositoryImpl;
import ro.pao.service.*;
import ro.pao.service.impl.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static ro.pao.application.utils.Constants.CSV_PATH_WRITE;

/**
 * In Meniu se fac operatiile care pot lua informatii din toate dintre servicile definite.
 * De exemplu, avem definit mai jos `private final ExampleService exampleService = new ExampleServiceImpl();`
 * <p>
 * In cazul in care aveam definit mai multe servicii, la fel, faceam o initializare a serviciile si astfel apelam metode din serviciu.
 */
public class Menu {

    private static Menu INSTANCE;

    private static final Logger logger = Logger.getGlobal();

    public static Menu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Menu();
        }
        return INSTANCE;
    }

    public void intro() {
        String intro = """
                Welcome to part 2
                """;

        System.out.println(intro);

    }

    public void makeRequest(){
        Requests request = new Requests();
        request.saveRequestInfo();
    }

    public void writeTeachersToCSV() throws Exception {
        TeacherService teacherService = new TeacherServiceImpl(new TeacherRepositoryImpl());
        List<Teacher> teachers = teacherService.getAll();
        CsvWriter csvWriter = CsvWriter.getInstance();
        Iterator<Teacher> it = teachers.iterator();
        List<String[]> teacherStrings = new ArrayList<>();
        Path path = Paths.get(CSV_PATH_WRITE);
        String[] headers = {"user_id", "last_name", "first_name"};
        teacherStrings.add(headers);
        while(it.hasNext()){
            teacherStrings.add(it.next().toStringArray());
        }
        try {
            csvWriter.writeLineByLine(teacherStrings, path);
        }
        catch(IOException e) {
            logger.log(Level.SEVERE, "csv output error");
        }
    }

    public void writeParallel(){
        StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
        List<Student> students = studentService.getAll();
        System.out.println("This is a list of all the students:");
        students.parallelStream().forEach(student -> {
            System.out.println(student.getName().toString());
        });

    }

    //   public void intro() {
//        String intro = """
//                Welcome to the platform
//                """;
//
//        System.out.println(intro);
//
//        ReportCard card1 = ReportCard.builder().grades(new HashMap<>()).build();
//        reportCardService.addOnlyOne(card1);
//
//        Teacher teacher1 = Teacher.builder().id(UUID.randomUUID()).name(new Name("Popescu", "Dan")).passwdHash(User.hashPasswd("1234")).build();
//        teacherService.addOnlyOne(teacher1);
//
//        Course poo = Course.builder().id(UUID.randomUUID()).students(new HashSet<>()).quizes(new ArrayList<>()).year(Year.now()).name(CourseName.OOP).build();
//
//        courseService.addOnlyOne(poo);
//
//        reportCardService.addGrade(card1.getId(), 4.3, poo);
//
//
//        Student student1 = Student.builder().id(UUID.randomUUID()).name(new Name("Teodorescu", "George")).userName("george")
//                .passwdHash(User.hashPasswd("12345")).reportCard(card1).build();
//        studentService.addOnlyOne(student1);
//
//        Student student2 = Student.builder().id(UUID.randomUUID()).name(new Name("Ionescu", "Andrei")).userName("andrei")
//                .passwdHash(User.hashPasswd("12345")).reportCard(card1).build();
//        studentService.addOnlyOne(student2);
//
//        System.out.println(reportCardService.getById(card1.getId()).get().getGrades().get(poo.getId()));
//
//        Question q1 = Question.builder().id(UUID.randomUUID()).text("q1").options(new ArrayList<>()).answer(1).points(5.0).build();
//
//        questionService.addOnlyOne(q1);
//        questionService.addOption(q1.getId(),"dasfds");
//        questionService.addOption(q1.getId(),"asdfds");
//        questionService.addOption(q1.getId(), "asd");
//
//        Question q2 = Question.builder().id(UUID.randomUUID()).text("q2").options(new ArrayList<>()).points(5.0).answer(2).build();
//
//        questionService.addOnlyOne(q2);
//        questionService.addOption(q2.getId(),"dasfds");
//        questionService.addOption(q2.getId(),"asdfds");
//        questionService.addOption(q2.getId(), "asd");


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


//    public void getAllStudents() {
//        String intro = """
//                A list of students
//                """;
//
//        System.out.println(intro);
//
//        studentService.getAllFromMap().stream().sorted(Comparator.comparing(Student::getName)).forEach(student ->
//                System.out.println(student));
//    }
//
//    public void getAllCourses(){
//        String intro = """
//                 A list of courses
//                 """;
//
//        System.out.println(intro);
//
//        courseService.getAllFromMap().stream().forEach(c -> System.out.println(c));
//
//    }
//
//    public void addQuiz(){
//        String intro = """
//                Added a quiz
//                """;
//
//        System.out.println(intro);
//
//        Quiz quiz1 = Quiz.builder().id(UUID.randomUUID()).course(courseService.getAllFromMap().get(0)).questionList(new ArrayList<>()).build();
//        quizService.addOnlyOne(quiz1);
//
//        Quiz quiz2 = Quiz.builder().id(UUID.randomUUID()).course(courseService.getAllFromMap().get(0)).questionList(new ArrayList<>()).build();
//        quizService.addOnlyOne(quiz2);
//
//        Quiz quiz3 = Quiz.builder().id(UUID.randomUUID()).course(courseService.getAllFromMap().get(0)).questionList(new ArrayList<>()).build();
//        quizService.addOnlyOne(quiz3);
//
//        quizService.addQuestion(quiz1.getId(), questionService.getAllFromMap().get(0));
//        quizService.addQuestion(quiz1.getId(), questionService.getAllFromMap().get(1));
//
//        System.out.println(quizService.getAllFromMap().get(0));
//    }
//
//    public void gradeQuiz(){
//
//        String intro = """
//                Grading a quiz
//                """;
//        System.out.println(intro);
//
//        List<Integer> answers = new ArrayList<>();
//        answers.add(1);
//        answers.add(2);
//        AnsweredQuiz answeredQuiz = AnsweredQuiz.builder().id(UUID.randomUUID()).quiz(quizService.getAllFromMap().get(0)).answers(answers)
//                .student(studentService.getAllFromMap().get(0)).build();
//
//        answeredQuizService.addOnlyOne(answeredQuiz);
//
//        answeredQuizService.grade(answeredQuiz.getId());
//        System.out.println(answeredQuiz);
//        System.out.println(reportCardService.getAllFromMap().get(0));
//    }
//
//
//    public void getAllTeachers(){
//        String intro = """
//                Printing all teachers
//                """;
//        System.out.println(intro);
//        teacherService.getAllFromMap().stream().forEach(c -> System.out.println(c));
//    }
//
//    public void getAllQuizzes(){
//        String intro = """
//                Printing all quizzes
//                """;
//        System.out.println(intro);
//        quizService.getAllFromMap().stream().forEach(c -> System.out.println(c));
//    }
//
//    public void removeQuiz(){
//        String intro = """
//                Removing a quiz
//                """;
//        System.out.println(intro);
//        quizService.removeElementById(quizService.getAllFromMap().get(0).getId());
//        quizService.getAllFromMap().stream().forEach(c -> System.out.println(c));
//    }
//
//    public void orderedQuizzes(){
//        String intro = """
//                Ordering quizzes
//                """;
//        System.out.println(intro);
//        quizService.getAllFromMap().stream().sorted(Comparator.comparing(quiz -> quiz.getQuestionList().size())).forEach(quiz ->
//                System.out.println(quiz));
//
//    }


}
