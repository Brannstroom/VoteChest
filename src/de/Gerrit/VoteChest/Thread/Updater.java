package de.Gerrit.VoteChest.Thread;


import org.apache.http.client.methods.HttpPost;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.omg.DynamicAny.NameValuePair;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Updater extends Thread{

    private int timer = 10;

    public void run() {

        while (true) {

            try {
                sendPOST();
            } catch (Exception e) {

            }


            try {
                Thread.sleep(300000); //300000
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            }
    }
    private static void sendPOST() throws Exception {

        InetAddress IP = InetAddress.getLocalHost();

        String ipAddress =  Bukkit.getServer().getIp() +  Bukkit.getServer().getPort();

                String body = "ip1=" + URLEncoder.encode(  ipAddress, "UTF-8" ) + "&" +
                "ip2=" + URLEncoder.encode( IP.toString(), "UTF-8" ) + "&" +
                        "onlineplayers=" + URLEncoder.encode( String.valueOf(Bukkit.getServer().getOnlinePlayers().size())
                        , "UTF-8" );

        URL url = new URL( "http://92.60.36.169:8080/" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod( "POST" );
        connection.setDoInput( true );
        connection.setDoOutput( true );
        connection.setUseCaches( false );
        connection.setRequestProperty( "Content-Type",
                "application/x-www-form-urlencoded" );
        connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );

        OutputStreamWriter writer = new OutputStreamWriter( connection.getOutputStream() );
        writer.write( body );
        writer.flush();


        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()) );

        for ( String line; (line = reader.readLine()) != null; )
        {
            System.out.println( line );
        }

        writer.close();
        reader.close();


    }

}
