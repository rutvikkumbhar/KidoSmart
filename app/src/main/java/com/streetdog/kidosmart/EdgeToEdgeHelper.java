package com.streetdog.kidosmart;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EdgeToEdgeHelper {

    public static void applyTopInset(View view) {

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {

            Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.statusBars()
            );

            v.setPadding(
                    0,
                    systemBars.top,
                    0,
                    0
            );

            return insets;
        });

        ViewCompat.requestApplyInsets(view);
    }
}