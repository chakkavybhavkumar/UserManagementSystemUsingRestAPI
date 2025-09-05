package com.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String useremailid;
    private long mobilenumber;
    private String password;
    private String gender;

    // 🔥 Private constructor to enforce Builder usage
    private UserDetails(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.useremailid = builder.useremailid;
        this.mobilenumber = builder.mobilenumber;
        this.password = builder.password;
        this.gender = builder.gender;
    }

    // 🔥 Static Inner Builder Class
    public static class Builder {
        private int id;
        private String username;
        private String useremailid;
        private long mobilenumber;
        private String password;
        private String gender;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder useremailid(String useremailid) {
            this.useremailid = useremailid;
            return this;
        }

        public Builder mobilenumber(long mobilenumber) {
            this.mobilenumber = mobilenumber;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserDetails build() {
            return new UserDetails(this);
        }
    }
}
