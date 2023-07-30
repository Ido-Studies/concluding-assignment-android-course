package com.idoisraeli.mobileminigames;

public class User {
    public String username;
    public String nickname;
    public String fullname;
    public int age;
    public String phone;

//    public Map<String, GameStats> gameStats = new HashMap<>();

    public User() {
        // Default constructor
        // Required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String nickname, int age, String phone, String firstname, String lastname) {
        this.username = username;
        this.nickname = nickname;
        this.fullname = firstname + " " + lastname;
        this.age = age;
        this.phone = phone;
    }

//    public class GameStats {
//        public int numberOfGamesPlayed = 0;
//        public int highScore = 0;
//    }
}
