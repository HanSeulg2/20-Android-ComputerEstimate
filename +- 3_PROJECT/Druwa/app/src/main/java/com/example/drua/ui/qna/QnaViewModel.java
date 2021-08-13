package com.example.drua.ui.qna;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QnaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QnaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is qna fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}