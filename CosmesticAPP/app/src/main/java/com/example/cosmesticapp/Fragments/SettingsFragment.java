// 22110459 - Trần Triệu Vĩ
package com.example.cosmesticapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.cosmesticapp.R;
import com.example.cosmesticapp.activity.LoginActivity;
import com.example.cosmesticapp.share.SharedPrefManager;

public class SettingsFragment extends Fragment {
    Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btn_logout = view.findViewById(R.id.btn_logout);
        view.setOnClickListener(this::logout);

        return view;
    }

    private void logout(View view) {
        // Clear user data from SharedPreferences
        SharedPrefManager.getInstance(requireContext()).clear();

        // Redirect to login screen
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

}