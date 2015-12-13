package com.serviceTest;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Crunchify.com
 *
 */

public class CrunchifyCallUrlAndGetResponse {

    public static void main(String[] args) {
        final String url = "http://cdn3.crunchify.com/wp-content/uploads/code/json.sample.txt";
        Queue<String> responses = new PriorityQueue<String>();
        Runnable req = new ContentDownloader(url,responses);
        for (int i = 0 ; i <= 5; i++){
            Thread t = new Thread(req);
            t.start();
        }
        int resultcount = -1;
        while (resultcount < 5) {
            String value = responses.poll();
            if (value == null) continue;
            System.out.println(value);
            resultcount++;
        }
    }
}