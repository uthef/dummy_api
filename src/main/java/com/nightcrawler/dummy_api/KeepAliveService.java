package com.nightcrawler.dummy_api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class KeepAliveService {
    private final Timer timer;
    private long count = 0;
    private Logger logger = LoggerFactory.getLogger(KeepAliveService.class);

    public KeepAliveService(String url, long period) {
        timer = new Timer();
        final String localUrl = url;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    URL url = new URL(localUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int code = connection.getResponseCode();

                    if (code == 200) count++;
                    connection.disconnect();

                    logger.info("GET \"{}\" (code: {})", url, code);

                } catch (Exception e) {
                    logger.error("An error occurred", e);
                }
            }

        }, 0, period);
    }

    public void interrupt() {
        timer.cancel();
    }

    public long getRequestCount() {
        return count;
    }
}
