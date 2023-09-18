package com.example.lab2_20192858;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("results")
    private Result[] results;

    public UserResponse(Result[] results) {
        this.results = results;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public static class Result {
        @SerializedName("gender")
        private String gender;

        @SerializedName("name")
        private Name name;

        @SerializedName("email")
        private String email;

        @SerializedName("login")
        private Login login;

        @SerializedName("picture")
        private Picture picture;

        public Result(String gender, Name name, String email, Login login, Picture picture) {
            this.gender = gender;
            this.name = name;
            this.email = email;
            this.login = login;
            this.picture = picture;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public Login getLogin() {
            return login;
        }
        public void setLogin(Login login) {
            this.login = login;
        }
        public Picture getPicture() {
            return picture;
        }
        public void setPicture(Picture picture) {
            this.picture = picture;
        }
        public static class Login {
            @SerializedName("password")
            private String password;
            public String getPassword() {
                return password;
            }
            public void setPassword(String password) {
                this.password = password;
            }
        }
        public static class Picture {
            @SerializedName("large")
            private String large;
            public String getLarge() {
                return large;
            }
            public void setLarge(String large) {
                this.large = large;
            }
        }
        public static class Name {
            @SerializedName("title")
            private String title;
            @SerializedName("first")
            private String first;
            @SerializedName("last")
            private String last;
            public Name(String title, String first, String last) {
                this.title = title;
                this.first = first;
                this.last = last;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getFirst() {
                return first;
            }
            public void setFirst(String first) {
                this.first = first;
            }
            public String getLast() {
                return last;
            }
            public void setLast(String last) {
                this.last = last;
            }
        }
    }
}
