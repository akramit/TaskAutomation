package com.androidautosolns.taskautomation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Call# newInstance} factory method to
 * create an instance of this fragment.
 */
public class Call extends Fragment {
    private Button goBackToMenu;
    private Button ok;

    public Call() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        goBackToMenu = view.findViewById(R.id.call_backButton);
        goBackToMenu.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_call_to_menu));
    }
}