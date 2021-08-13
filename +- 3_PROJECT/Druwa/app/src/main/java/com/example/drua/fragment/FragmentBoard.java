package com.example.drua.fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drua.R;

public class FragmentBoard extends Fragment {
    Button mButton1;

    public interface CustomOnClickListener {
        public void onClicked(View v);
    }

    private CustomOnClickListener customOnClickListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        mButton1 = (Button) view.findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pcpartpicker.com/list/"));
                startActivity(intent);
            }
        });
        return view;
    }
    public void buttonClicked( View v ) {
        customOnClickListener.onClicked(v);
    }

}
