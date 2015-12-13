package com.serviceTest;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Queue;

public class ContentDownloader implements Runnable{

    private String myURL;
    private Queue<String> responses;

    public ContentDownloader(String url, Queue<String> responses) {

        this.myURL = url;
        this.responses = responses;
    }

    public void callURL() {
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = (HttpURLConnection) url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                int responseCode = urlConn.getResponseCode();
//                System.out.println(responseCode);
//                BufferedReader bufferedReader = new BufferedReader(in);
                sb.append(responseCode);
//                if (bufferedReader != null) {
//                    int cp;
//                    while ((cp = bufferedReader.read()) != -1) {
//                        sb.append((char) cp);
//                    }
//                    bufferedReader.close();
//                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }

        responses.add(sb.toString());
    }

    @Override
    public void run() {
       callURL() ;
    }
}
