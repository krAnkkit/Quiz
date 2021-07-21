package sample.quiz.model.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.quiz.model.Persistence;
import sample.quiz.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class DiskPersistence implements Persistence {

    private Gson gson;
    private Path usersDir;

    public DiskPersistence() {
        try {
            usersDir = Paths.get(System.getProperty("user.dir"), "users");
            gson = new GsonBuilder().create();
            if(!usersDir.toFile().exists()) {
                Files.createDirectories(usersDir);
            }
        } catch(IOException iox) {
            throw new RuntimeException("Error persisting queue data", iox);
        }
    }

    @Override
    public void writeUser(User user) {
        Path path = Paths.get(usersDir.toFile().getAbsolutePath(), user.getId());
        String userStr = gson.toJson(user);
        try {
            if(!path.toFile().exists()) {
                Files.createFile(path);
            }
            Files.writeString(path, userStr, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User readUser(String username) {
        Path path = Paths.get(usersDir.toFile().getAbsolutePath(), username);
        try {
            if(!path.toFile().exists()) {
                User u = new User(username);
                writeUser(u);
                return u;
            } else {
                String str = Files.readString(path);
                return gson.fromJson(str, User.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
