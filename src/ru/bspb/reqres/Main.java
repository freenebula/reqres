package ru.bspb.reqres;

import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException, InterruptedException {
        String name = "George";

        System.out.println(ReqResUtils.getAllUsers());
        ReqResUtils.usersToDelete(name);
        ReqResUtils.usersToCreate(name);

    }



}
