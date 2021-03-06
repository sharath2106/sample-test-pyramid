package com.testpyramid;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.google.gson.Gson;
import com.testpyramid.handlers.LoginHandler;
import com.testpyramid.handlers.PingHandler;
import com.testpyramid.persistence.UserRepository;

import static spark.Spark.*;

public class UserService {
    private static final Gson gson = new Gson();
    private static final ApplicationConfiguration config = Figaro.configure(null);

    public static void main(String[] args) {
        port(config.getValueAsInt("PORT"));

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/ping", new PingHandler());
        post("/login", new LoginHandler(new UserRepository()), gson::toJson);
    }
}