package com.itb.itbbar.Fragments.Nav_Drawer_Frags;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itb.itbbar.R;
import com.itb.itbbar.Utils.AppUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment implements View.OnClickListener {

    private Spinner gender_spinner;
    private TextView profile_editProfile_TV;
    private EditText userName_ET, email_ET, phone_ET;
    private Button DOB_button, profile_done;

    private FloatingActionButton upload_image_FAB;
    private Uri image_uri;
    private CircleImageView profileImage;

    private String[] gender = {"Male", "Female"};
    private DatePickerDialog datePickerDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initialisation(view);
        onCLickActions();

        return view;
    }

    private void settingUpSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_menu_item, gender);
        adapter.setDropDownViewResource(R.layout.drop_down_menu_item);
        gender_spinner.setAdapter(adapter);
    }

    private void onCLickActions() {
        upload_image_FAB.setOnClickListener(this);
        DOB_button.setOnClickListener(this);
        profile_editProfile_TV.setOnClickListener(this);
        profile_done.setOnClickListener(this);
    }

    private void initialisation(View view) {
        gender_spinner = view.findViewById(R.id.gender_spinner);
        DOB_button = view.findViewById(R.id.DOB_button);
        profile_done = view.findViewById(R.id.profile_done);

        upload_image_FAB = view.findViewById(R.id.upload_image_FAB);
        profileImage = view.findViewById(R.id.profileImage);
        profile_editProfile_TV = view.findViewById(R.id.profile_editProfile_TV);

        userName_ET = view.findViewById(R.id.userName_ET);
        email_ET = view.findViewById(R.id.email_ET);
        phone_ET = view.findViewById(R.id.phone_ET);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_image_FAB:
                imagePicker();
                break;

            case R.id.profile_editProfile_TV:

                profile_editProfile_TV.setVisibility(View.GONE);
                upload_image_FAB.setVisibility(View.VISIBLE);
                profile_done.setVisibility(View.VISIBLE);
                userName_ET.setEnabled(true);
                phone_ET.setEnabled(true);
                email_ET.setEnabled(true);
                gender_spinner.setEnabled(true);
                DOB_button.setEnabled(true);

                break;

            case R.id.profile_done:
                profile_editProfile_TV.setVisibility(View.VISIBLE);
                upload_image_FAB.setVisibility(View.GONE);
                profile_done.setVisibility(View.GONE);
                userName_ET.setEnabled(false);
                phone_ET.setEnabled(false);
                email_ET.setEnabled(false);
                gender_spinner.setEnabled(false);
                DOB_button.setEnabled(false);

                break;

            case R.id.DOB_button:
                initDatePicker();
                break;

        }

    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                DOB_button.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }

    private void imagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // Get the url of the image from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // update the preview image in the layout
                image_uri = selectedImageUri;
                Toast.makeText(getContext(), "Image Selected", Toast.LENGTH_SHORT).show();
                Glide.with(getContext()).load(image_uri).into(profileImage);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_menu_item, gender);
        adapter.setDropDownViewResource(R.layout.drop_down_menu_item);
        gender_spinner.setAdapter(adapter);
    }

}