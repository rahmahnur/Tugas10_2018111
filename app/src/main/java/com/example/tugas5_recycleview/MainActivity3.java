package com.example.tugas5_recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainActivity3 {
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle
                                     savedInstanceState) {
        return
                inflater.inflate(R.layout.activity_main3,container,false);
    }
}
