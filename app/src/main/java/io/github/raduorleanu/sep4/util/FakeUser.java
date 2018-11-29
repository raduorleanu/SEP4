package io.github.raduorleanu.sep4.util;



import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class FakeUser {


    private Context context;
    private ArrayList<String> boyNames;
    private ArrayList<String> girlNames;
    private ArrayList<String> boyPics;
    private ArrayList<String> girlPics;
    private ArrayList<String> familyNames;
    private ArrayList<String> adjectives;
    private ArrayList<String> streetNames;
    private ArrayList<String> comments;
    private Random random;

    public FakeUser(Context context) {
        this.context = context;
        boyNames = readFile("files/boyNames.txt");
        girlNames = readFile("files/girlNames.txt");
        boyPics = readFile("files/maleProfilePics.txt");
        girlPics = readFile("files/femaleProfilePics.txt");
        familyNames = readFile("files/familyNames.txt");
        adjectives = readFile("files/adj.txt");
        streetNames = readFile("files/streetNames.txt");
        comments = readFile("files/comments.txt");
        random = new Random();
    }


    private ArrayList<String> readFile(String fileName) {
        ArrayList<String> res = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open(fileName);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                res.add(line);
            }

            inputStream.close();
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return res;
    }

    private User createFullDetailsUser() {
        boolean isGirl = tossCoin();
        String username = (isGirl ? randomGirlUsername() : randomBoyUsername()) + " " + randomFamilyName();


        return new User(username,
                randomStreetName(),
                username + "@gmail.com",
                randomAdjective(),
                isGirl ? randomGirlPic() : randomBoyPic());
    }

    private String randomNumber(int numberLength) {
        long l = random.nextLong();
        String s = String.valueOf(l);
        return s.substring(1, numberLength + 1);
    }

    private String randomGirlName() {
        return girlNames.get(random.nextInt(girlNames.size()));
    }

    private String randomBoyName() {
        return boyNames.get(random.nextInt(boyNames.size()));
    }

    private String randomFamilyName() {
        return familyNames.get(random.nextInt(familyNames.size()));
    }

    private String randomGirlPic() {
        return girlPics.get(random.nextInt(girlPics.size()));
    }

    private String randomBoyPic() {
        return boyPics.get(random.nextInt(boyPics.size()));
    }

    private String randomStreetName() {
        return streetNames.get(random.nextInt(streetNames.size()));
    }

    private boolean tossCoin() {
        return random.nextBoolean();
    }

    private String randomAdjective() {
        return adjectives.get(random.nextInt(adjectives.size()));
    }

    private String randomGirlUsername() {
        String s = "";
        s += randomGirlName();
        return s;
    }

    private String randomBoyUsername() {
        String s = "";
        s += randomBoyName();
        return s;
    }
    private String randomDayInTheFuture() {
        int s = random.nextInt(25);
        return "2019-01-" + (s < 10 ? "0" + s : s);
    }

    public List<User> getUsers(int numberOfUsers) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            users.add(createFullDetailsUser());
        }
        return users;
    }

    public List<Event> getEvents(int numberOfEvents) {
        List<Event> events = new ArrayList<>();
        for(int i = 0; i < numberOfEvents; i++) {
            User u = createFullDetailsUser();

            events.add(new Event(
                    u,
                    new ArrayList<User>(),
                    Integer.parseInt(randomNumber(2)) / 10,
                    randomDayInTheFuture(),
                    randomAdjective() + " " + randomAdjective(),
                    u.getAddress(),
                    new ArrayList<String>()));
        }
        return events;
    }

    public void addUsersToEvent(Event event, int numberOfUsers) {
        for(int i = 0; i < numberOfUsers; i++) {
            User u = createFullDetailsUser();
            event.getParticipants().add(u);
        }
    }

    public List<String> getSomeComments(int numberOfComments) {
        List<String> co = new ArrayList<>();
        for(int i = 0; i < numberOfComments; i++) {
            co.add(comments.get(random.nextInt(comments.size() - 2)));
        }
        return co;
    }
}
