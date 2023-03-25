package com.nightcrawler.dummy_api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class KeepAliveService {
    private final Timer timer;
    private long count = 0;

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

                    if (connection.getResponseCode() == 200) count++;
                    System.out.println(String.format("GET \"%s\" - %d", url, connection.getResponseCode()));
                    connection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
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
