package ru.bspb.reqres;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class ReqResUtils {

    static HttpClient httpClient = HttpClient.newHttpClient();
    static String url = "https://reqres.in/api/users";

    public static ArrayList<DataItem> getAllUsers () throws IOException, InterruptedException {
        ArrayList<DataItem> allUsers = new ArrayList<>();
        for (int i = 1; i <= pages(); i++) {
            String url2 = url + "?page=" + i;
            allUsers.addAll(getUsers(url2).getData());
        }
        return allUsers;
    }

    private static Integer pages () throws IOException, InterruptedException {
        return getUsers(url).getTotalPages();
    }

    private static GetUsersRs getUsers (String url) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        GetUsersRs resp = mapper.readValue(response.body(), GetUsersRs.class);

        return resp;
    }

    public static void usersToDelete (String name) throws IOException, InterruptedException {
        System.out.println("-----------------------------------Удаление пользователей-----------------------------------");
        for (DataItem dataitem : getAllUsers()) {
            if (dataitem.getFirstName().equals(name)) {
                System.out.println(dataitem);
                deleteUser(dataitem.getId());
            }
        }
    }

    private static void deleteUser (int id) throws IOException, InterruptedException{
        String url2 = url + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url2))
                .build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println("Status Code - " + response.statusCode());
    }

    public static void usersToCreate (String name) throws IOException, InterruptedException {
        System.out.println("-----------------------------------Создание пользователей-----------------------------------");
        ObjectMapper mapper = new ObjectMapper();
        for (DataItem dataitem : getAllUsers()) {
            if (dataitem.getFirstName().equals(name)) {
                User user = new User();
                user.setName(dataitem.getFirstName() + " " + dataitem.getLastName());
                user.setJob("New employee");
                String body = mapper.writeValueAsString(user);
                createUser(body);

            }
        }
    }

    private static void createUser (String body) throws IOException, InterruptedException{
         HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type", "application/json")
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println("Status Code - " + response.statusCode());
        System.out.println("Response - " + response.body());
    }
}

