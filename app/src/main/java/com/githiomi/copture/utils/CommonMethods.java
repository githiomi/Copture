package com.githiomi.copture.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CommonMethods {

    // To extract the text from a TextInput
    public static String extractText(TextInputLayout textInputLayout) {
        return Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();
    }

    public static void setIsRequiredField(TextInputLayout textInputLayout) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError("This is a required field!");
    }

    public static void setNotValidField(TextInputLayout textInputLayout) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError("Please enter a valid value!");
    }
}
