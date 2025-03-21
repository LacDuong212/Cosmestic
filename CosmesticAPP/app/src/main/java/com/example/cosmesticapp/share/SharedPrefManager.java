// 22110410 - Huỳnh Thị Mỹ Tâm
package com.example.cosmesticapp.share;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cosmesticapp.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "cosmetic_store_user_pref";
    private static final String KEY_USER_ID = "key_user_id";
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_GENDER = "key_gender";
    private static final String KEY_IMAGE = "key_image";
    private static final String KEY_ACTIVE = "key_active";
    private static final String KEY_IS_LOGGED_IN = "key_is_logged_in";

    private static SharedPrefManager instance;
    private SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    /**
     * Store user data in SharedPreferences
     */
    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_IMAGE, user.getImage());
        editor.putBoolean(KEY_ACTIVE, user.isActive());
        editor.putBoolean(KEY_IS_LOGGED_IN, true);

        editor.apply();
    }

    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /**
     * Get the stored user data as a User object
     */
    public User getUser() {
        User user = new User();

        user.setUserId(sharedPreferences.getLong(KEY_USER_ID, -1));
        user.setUsername(sharedPreferences.getString(KEY_USERNAME, null));
        user.setEmail(sharedPreferences.getString(KEY_EMAIL, null));
        user.setGender(sharedPreferences.getString(KEY_GENDER, null));
        user.setImage(sharedPreferences.getString(KEY_IMAGE, null));
        user.setActive(sharedPreferences.getBoolean(KEY_ACTIVE, false));

        return user;
    }

    /**
     * Clear all user data from SharedPreferences (logout)
     */
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
