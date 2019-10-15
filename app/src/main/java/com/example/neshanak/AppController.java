package com.example.neshanak;

import android.app.Application;

import ir.map.sdk_map.Mapir;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // add Token to MAPir...
        Mapir.getInstance(this, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY2NGQ5ZGVlYmY2ZDk0MjNhOTFkMDFjOWNjNjY5MmU1YWRlNTVhNjNlNzAyZTJjYjhkZGUwNjc2ZmE5YzhiMjhhZmQxNzJjNzg3MDUwZGQ4In0.eyJhdWQiOiI2NDMxIiwianRpIjoiNjY0ZDlkZWViZjZkOTQyM2E5MWQwMWM5Y2M2NjkyZTVhZGU1NWE2M2U3MDJlMmNiOGRkZTA2NzZmYTljOGIyOGFmZDE3MmM3ODcwNTBkZDgiLCJpYXQiOjE1NzExMzgxNDIsIm5iZiI6MTU3MTEzODE0MiwiZXhwIjoxNTczNjQzNzQyLCJzdWIiOiIiLCJzY29wZXMiOlsiYmFzaWMiXX0.eDf-O-3BJl-vVrCBhMKjwumUFpvI7QN5LX1Ner8yLKrHjzbTrqLqfXpDzccFREc7eB0KapyEriGQR_pZfeEw9kq4mj7ghvJ9oyPiWpGm2DgVAm9SyYiNTIAsjoF4WHzbQdWlabIfm75RhDwREAA9i5mN9rQlzmG4cMEbYOwrKhtbNE0Q4rCQ7iIgZWJK8pyhwy9Xur7zDp1Nqv-p_QQxm2JTYERaVbqcL7-YG2s27e1J7Y9io8Reul_l4TIITVYC9tIh2H6Icj7nRPovGhteM0GE64sSXmV3aShUm1tvlSycCkhFWQYjnmg9iDtpC-nIUrZOIiIM0kvwx7Sg4WX8ig");


    }

}
