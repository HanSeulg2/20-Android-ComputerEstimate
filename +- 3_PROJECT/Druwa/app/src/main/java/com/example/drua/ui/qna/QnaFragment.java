package com.example.drua.ui.qna;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drua.R;

public class QnaFragment extends Fragment {

    private QnaViewModel qnaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qnaViewModel =
                new ViewModelProvider(this).get(QnaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qna, container, false);
        final TextView textView = root.findViewById(R.id.qna);

        qnaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}