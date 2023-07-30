package com.idoisraeli.mobileminigames;

public class User {
    public String email;
    public String nickname;
    public String fullname;
    public int age;
    public String phone;

//    public Map<String, GameStats> gameStats = new HashMap<>();

    public User() {
        // Default constructor
        // Required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String nickname, int age, String phone, String firstname, String lastname) {
        this.email = email;
        this.nickname = nickname;
        this.fullname = firstname + " " + lastname;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }

    //    public class GameStats {
//        public int numberOfGamesPlayed = 0;
//        public int highScore = 0;
//    }
}
