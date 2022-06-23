package com.example.healthtemiapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
public class Fragment1 extends Fragment {

    private View viewf1;

    private ImageButton btnbp, btndl, btnsq, btnbbc;
    private String exerciseName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewf1 = inflater.inflate(R.layout.fragment_1, container, false);

        btnbp = (ImageButton) viewf1.findViewById(R.id.btnbp);
        btndl = (ImageButton) viewf1.findViewById(R.id.btndl);
        btnsq = (ImageButton) viewf1.findViewById(R.id.btncs);
        btnbbc = (ImageButton) viewf1.findViewById(R.id.btnbbc);
        btnbp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // set what exercise to do in this fragment
                exerciseName = "benchpress";
                Intent intent = new Intent(getActivity(), BenchpressLevel1.class);
                startActivity(intent);
            }
        });

        btndl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // set what exercise to do in this fragment
                exerciseName = "deadlift";
                Intent intent = new Intent(getActivity(), TeachingLevel1.class);
                startActivity(intent);
            }
        });

        btnsq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // set what exercise to do in this fragment
                exerciseName = "squat";
                Intent intent = new Intent(getActivity(), SquatLevel1.class);
                startActivity(intent);
            }
        });
        btnbbc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // set what exercise to do in this fragment
                exerciseName = "bicepscurl";
                Intent intent = new Intent(getActivity(), BicepscurlLevel1.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return viewf1;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }


}
