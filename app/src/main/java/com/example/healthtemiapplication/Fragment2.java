package com.example.healthtemiapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    private View viewf2;
    private Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewf2 = inflater.inflate(R.layout.fragment_2, container, false);

        b = (Button) viewf2.findViewById(R.id.routinebuttonNext);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // set what exercise to do in this fragment
                User.rootExer = "pushup";
                Intent intent = new Intent(getActivity(), Routineset.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return viewf2;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }
}