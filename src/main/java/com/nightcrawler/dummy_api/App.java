package com.nightcrawler.dummy_api;

import java.util.concurrent.*;

import io.javalin.*;
import io.javalin.plugin.bundled.RedirectToLowercasePathPlugin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.plugins.register(new RedirectToLowercasePathPlugin());
        });

        long period = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
        KeepAliveService service = new KeepAliveService(" https://javalin.onrender.com", period);

        app.get("/index", ctx -> ctx.result("Hello World!"));
        app.get("/", ctx -> ctx.result("Hello World!"));
        app.get("/count", ctx -> ctx.result(String.valueOf(service.getRequestCount())));

        app.start(8080);
    }
}
