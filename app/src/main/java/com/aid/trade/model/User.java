package com.aid.trade.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * The User entity
 */
@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    /**
     * Gets the user id.
     *
     * @return The user id
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email
     *
     * @param email The user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Helper method to create a user.
     *
     * @param email    The email
     * @param password The password
     * @return The user
     */
    public static User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
