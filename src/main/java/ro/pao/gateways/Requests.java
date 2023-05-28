package ro.pao.gateways;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.pao.mapper.TeacherMapper;
import ro.pao.model.Name;
import ro.pao.model.Teacher;
import ro.pao.model.abstracts.User;
import ro.pao.repository.TeacherRepository;
import ro.pao.repository.impl.TeacherRepositoryImpl;
import ro.pao.service.TeacherService;
import ro.pao.service.impl.TeacherServiceImpl;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Requests {

    private static HttpClient httpClient = HttpClient.newHttpClient();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static TeacherService teacherService = new TeacherServiceImpl(new TeacherRepositoryImpl());


    public void saveRequestInfo() {

        try {

            HttpRequest httpRequestPerson = HttpRequest.newBuilder()
                    .uri(new URI("https://names.drycodes.com/4?nameOptions=boy_names"))
                    .GET()
                    .build();

            var httpResponse = httpClient.send(httpRequestPerson, HttpResponse.BodyHandlers.ofString());

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); var body = httpResponse.body();

            JsonNode person = objectMapper.readTree(body);

            person.forEach(personObject -> {
                String name = personObject.toString();
                String[] parts = name.strip().split("_");
                String first_name = parts[0].substring(1);
                String last_name = parts[1].substring(0, parts[1].length()-1);
                Teacher teacher = Teacher.builder().id(UUID.randomUUID()).name(new Name(last_name, first_name)).userName(name).passwdHash(User.hashPasswd(name)).build();
                teacherService.addOnlyOne(teacher);
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}