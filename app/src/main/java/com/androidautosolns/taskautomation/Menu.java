package com.androidautosolns.taskautomation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Menu extends Fragment {

    private Button textButton;
    private Button callButton;
    public Menu() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textButton = view.findViewById(R.id.txtButton);
        //final NavController navController = NavHostFragment.findNavController(this);
        //textButton.setOnClickListener(() -> navController.navigate(R.id.action_menu_to_textFragment));
        textButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menu_to_textFragment));

        // Call Button Functionality
        callButton=view.findViewById(R.id.callButton);
        callButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menu_to_call));
    }

}