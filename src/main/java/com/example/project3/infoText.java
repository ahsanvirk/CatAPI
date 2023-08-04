package com.example.project3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project3.databinding.FragmentInfoTextBinding;

public class infoText extends Fragment {
    private FragmentInfoTextBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInfoTextBinding.inflate(inflater, container, false);
        binding.catText.setMovementMethod(new ScrollingMovementMethod());
        return binding.getRoot();
    }
}