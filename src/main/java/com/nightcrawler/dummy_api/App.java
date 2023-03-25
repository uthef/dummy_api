package com.nightcrawler.dummy_api;

import io.javalin.*;
import io.javalin.plugin.bundled.RedirectToLowercasePathPlugin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.register(new RedirectToLowercasePathPlugin());
        });

        app.get("/index", ctx -> ctx.result("Hello World!"));
        app.get("/", ctx -> ctx.result("Hello World!"));

        app.start(8080);
    }
}
