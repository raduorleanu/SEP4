package io.github.raduorleanu.sep4.databaseHandlers;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;

public class UserDbHandler {

    private AddUsersToEventUserSwapRepository repository;

    public UserDbHandler(AddUsersToEventUserSwapRepository repository) {
        this.repository = repository;
        addData();
    }

    private void addData() {
        List<User> l1 = new ArrayList<>();
        List<User> l2 = new ArrayList<>();

        l1.add(new User("Momo", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyuL3-fEOFYHRS-CFfzK4kQe-tp9HZtZ-EVL7wjk9rFvXNqnzt"));
        l1.add(new User("Mina", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYHVyDCUF6wUBYD_nQZUVJb_4kyoicqd22BJO7_-tlkp1kQRLZ"));
        l1.add(new User("Sana", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSR4-0VFcAYf1cGu697c0vbI5uc-cg6uQTPU2Uf9JZJ2R1DI0ws"));
        l1.add(new User("JiHyo", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cFoUL_agNdrAtaQCWo_0D8qdQqby32ayHdhbT754V59QEKSnZg"));

        repository.updateNotGoing(l1);
        repository.updateGoing(l2);
    }

}
