package com.itb.itbbar.Fragments.BottomNav_Frags;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.itb.itbbar.Adapters.Trending_list_Adapter;
import com.itb.itbbar.Adapters.ViewPager_Adapter_1;
import com.itb.itbbar.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Reservations_Fragment extends Fragment implements View.OnClickListener {
    private TextView table_today_button, table_tomorrow_button, table_pick_a_date_TV, table_date_day_TextView, table_time_TV;
    private ImageView calendar_icon;
    private Button btn_today, btn_tomorrow, btn_pickDate, datePickerButton;
    private LinearLayout table_pick_a_date_button, table_next_button;
    private ViewPager viewPager_1;
    private WormDotsIndicator indicator;
    private TypedArray list_1;
    private ViewPager_Adapter_1 adapter_1;
    private Trending_list_Adapter trendingListAdapter;
    private List<String> table_list = new ArrayList<>();
    int mInteger = 0;
    RecyclerView rv_trending;
    Button btn_plus, btn_minus;
    TextView tv_value;
    Context context;
    TypedArray typedArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);
        context = view.getContext();
        initialisation(view);
        onClickActions();
        SetAdapter();
        setFirstViewPager();
        return view;
    }

    private void SetAdapter() {
        typedArray = getResources().obtainTypedArray(R.array.trending_image);
        table_list = Arrays.asList(this.getResources().getStringArray(R.array.trending_list));
        trendingListAdapter = new Trending_list_Adapter(getContext(), table_list, typedArray);
        rv_trending.setAdapter(trendingListAdapter);
        trendingListAdapter.setClickOnInterface(new Trending_list_Adapter.itemCLickListener() {
            @Override
            public void listeners(int position) {
                if (position == 0) {


                } else if (position == 1) {
                    Navigation.findNavController(getView()).navigate(R.id.navigate_from_main_fragment_to_offers);

                } else {
                    showTableBottomSheet_1();
                }


            }
        });
    }

    private void onClickActions() {
        btn_today.setOnClickListener(this);
        btn_tomorrow.setOnClickListener(this);
        btn_pickDate.setOnClickListener(this);
    }

    private void showTableBottomSheet_1() {
        View rootView = getLayoutInflater().inflate(R.layout.table_bottomsheet_1, null);
        table_today_button = rootView.findViewById(R.id.table_fragment_today_button);
        table_tomorrow_button = rootView.findViewById(R.id.table_fragment_tomorrow_button);
        table_pick_a_date_TV = rootView.findViewById(R.id.pick_date_TV);
        table_date_day_TextView = rootView.findViewById(R.id.date_day_textView);
        table_time_TV = rootView.findViewById(R.id.time_TV);
        datePickerButton = rootView.findViewById(R.id.datePickerButton);
        calendar_icon = rootView.findViewById(R.id.calendar_icon);
        table_pick_a_date_button = rootView.findViewById(R.id.table_fragment_pick_date_button);
        table_next_button = rootView.findViewById(R.id.table_next_button);


        final BottomSheetDialog table_bottomSheetDialog_1 = new BottomSheetDialog(getActivity(), R.style.BottomSheetStyle);
        table_bottomSheetDialog_1.setContentView(rootView);
        table_bottomSheetDialog_1.show();

        //default today selected
        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, EEEE");
        String date = dateFormat.format(calendar.getTime());
        table_date_day_TextView.setText(date);
        table_today_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
        table_today_button.setTextColor(getResources().getColor(R.color.white));

        table_today_button.setOnClickListener(this);
        table_tomorrow_button.setOnClickListener(this);
        table_pick_a_date_button.setOnClickListener(this);
        table_next_button.setOnClickListener(this);
        datePickerButton.setOnClickListener(this);


        table_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTableBottomSheet_2(table_bottomSheetDialog_1);
                table_bottomSheetDialog_1.dismiss();
            }
        });

    }

    private void showTableBottomSheet_2(BottomSheetDialog table_bottomSheetDialog_1) {
        View rootView = getLayoutInflater().inflate(R.layout.table_bottomsheet_2, null);
        BottomSheetDialog table_bottomSheetDialog_2 = new BottomSheetDialog(getActivity());
        LinearLayout table_back_button = rootView.findViewById(R.id.table_back_button);
        btn_plus = rootView.findViewById(R.id.btn_plus);
        tv_value = rootView.findViewById(R.id.tv_value);
        btn_minus = rootView.findViewById(R.id.btn_minus);

        table_bottomSheetDialog_2.setContentView(rootView);
        table_bottomSheetDialog_2.show();


        table_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_bottomSheetDialog_1.show();
                table_bottomSheetDialog_2.dismiss();
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteger = mInteger + 1;
                display(mInteger);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteger = mInteger - 1;
                display2(mInteger);

            }
        });
    }

    private void display2(int mInteger) {
        tv_value.setText("" + mInteger);
    }

    private void display(int mInteger) {
        tv_value.setText("" + mInteger);
    }


    private void setFirstViewPager() {
        addDataToList_1();

        adapter_1 = new ViewPager_Adapter_1(getContext(), list_1);
        viewPager_1.setAdapter(adapter_1);
        indicator.setViewPager(viewPager_1);
        viewPager_1.setCurrentItem(0);
        adapter_1.setTimer(viewPager_1, 4);

    }


    private void addDataToList_1() {


        list_1 = getResources().obtainTypedArray(R.array.View_pager_Image);

    }

    private void initialisation(View view) {
        indicator = view.findViewById(R.id.dots_indicator);
        viewPager_1 = view.findViewById(R.id.viewPager_1);
        rv_trending = view.findViewById(R.id.rv_trending);
        btn_today = view.findViewById(R.id.btn_today);
        btn_tomorrow = view.findViewById(R.id.btn_tomorrow);
        btn_pickDate = view.findViewById(R.id.btn_pickDate);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table_fragment_today_button: {
                table_today_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_today_button.setTextColor(getResources().getColor(R.color.white));
                table_tomorrow_button.setBackground(getResources().getDrawable(R.color.white));
                table_tomorrow_button.setTextColor(getResources().getColor(R.color.darkGrey));
                table_pick_a_date_button.setBackground(getResources().getDrawable(R.color.white));
                table_pick_a_date_TV.setTextColor(getResources().getColor(R.color.darkGrey));
                table_pick_a_date_TV.setText(R.string.pick_a_date);
                calendar_icon.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.app_color)));

                final Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, EEEE");
                String date = dateFormat.format(calendar.getTime());

                table_date_day_TextView.setText(date);
                break;

            }

            case R.id.table_fragment_tomorrow_button: {
                table_tomorrow_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_tomorrow_button.setTextColor(getResources().getColor(R.color.white));
                table_today_button.setBackground(getResources().getDrawable(R.color.white));
                table_today_button.setTextColor(getResources().getColor(R.color.darkGrey));
                table_pick_a_date_button.setBackground(getResources().getDrawable(R.color.white));
                table_pick_a_date_TV.setTextColor(getResources().getColor(R.color.darkGrey));
                table_pick_a_date_TV.setText(R.string.pick_a_date);
                calendar_icon.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.app_color)));

                getTomorrowsDate();
                break;
            }

            case R.id.table_fragment_pick_date_button: {
                openCalender();
                break;
            }

            case R.id.btn_today: {
                showBottomSheetOnButtonCLick();
                getTodayDate();

                table_tomorrow_button.setClickable(false);
                table_pick_a_date_button.setClickable(false);

                table_today_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_today_button.setTextColor(getResources().getColor(R.color.white));
                break;
            }

            case R.id.btn_tomorrow: {
                showBottomSheetOnButtonCLick();

                table_today_button.setClickable(false);
                table_pick_a_date_button.setClickable(false);

                getTomorrowsDate();

                table_tomorrow_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_tomorrow_button.setTextColor(getResources().getColor(R.color.white));
                break;
            }

            case R.id.btn_pickDate: {
                openCalenderOnButtonClick();
                break;
            }

            case R.id.datePickerButton: {
                pick_a_time();
                break;
            }

        }
    }

    private void pick_a_time() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int mHour, int mMin) {
                String AM_PM;
                if (mHour < 12) {
                    AM_PM = "AM";
                } else if (mHour == 12) {
                    AM_PM = "NOON";
                } else {
                    mHour = mHour - 12;
                    AM_PM = "PM";
                }

                if (mMin < 10) {
//                    datePickerButton.setText(mHour + ":0" + mMin + " " + AM_PM);
                    table_time_TV.setText(mHour + ":0" + mMin + " " + AM_PM);
                } else {
//                    datePickerButton.setText(mHour + ":" + mMin + " " + AM_PM);
                    table_time_TV.setText(mHour + ":" + mMin + " " + AM_PM);
                }
            }
        }, hour, min, false);

        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(hour, min);
        timePickerDialog.show();
    }

    private void openCalender() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {


                table_pick_a_date_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_pick_a_date_TV.setTextColor(getResources().getColor(R.color.white));
                calendar_icon.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                table_today_button.setBackground(getResources().getDrawable(R.color.white));
                table_today_button.setTextColor(getResources().getColor(R.color.darkGrey));

                table_tomorrow_button.setBackground(getResources().getDrawable(R.color.white));
                table_tomorrow_button.setTextColor(getResources().getColor(R.color.darkGrey));

                switch (monthOfYear) {
                    case 0:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jan" + " " + year);
                        break;
                    case 1:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Feb" + " " + year);
                        break;
                    case 2:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Mar" + " " + year);
                        break;
                    case 3:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Apr" + " " + year);
                        break;
                    case 4:
                        table_date_day_TextView.setText(dayOfMonth + " " + "May" + " " + year);
                        break;
                    case 5:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jun" + " " + year);
                        break;
                    case 6:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jul" + " " + year);
                        break;
                    case 7:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Aug" + " " + year);
                        break;
                    case 8:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Sep" + " " + year);
                        break;
                    case 9:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Oct" + " " + year);
                        break;
                    case 10:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Nov" + " " + year);
                        break;
                    case 11:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Dec" + " " + year);
                        break;
                }
            }
        }, year, month, day);

        datePickerDialog.updateDate(year, month, day);
        datePickerDialog.show();
    }

    private void openCalenderOnButtonClick() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                showTableBottomSheet_1();

                table_pick_a_date_button.setBackground(getResources().getDrawable(R.drawable.button_layout));
                table_pick_a_date_TV.setTextColor(getResources().getColor(R.color.white));
                calendar_icon.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                table_today_button.setBackground(getResources().getDrawable(R.color.white));
                table_today_button.setTextColor(getResources().getColor(R.color.darkGrey));
                table_tomorrow_button.setBackground(getResources().getDrawable(R.color.white));
                table_tomorrow_button.setTextColor(getResources().getColor(R.color.darkGrey));

                switch (monthOfYear) {
                    case 0:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jan" + " " + year);
                        break;
                    case 1:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Feb" + " " + year);
                        break;
                    case 2:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Mar" + " " + year);
                        break;
                    case 3:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Apr" + " " + year);
                        break;
                    case 4:
                        table_date_day_TextView.setText(dayOfMonth + " " + "May" + " " + year);
                        break;
                    case 5:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jun" + " " + year);
                        break;
                    case 6:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Jul" + " " + year);
                        break;
                    case 7:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Aug" + " " + year);
                        break;
                    case 8:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Sep" + " " + year);
                        break;
                    case 9:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Oct" + " " + year);
                        break;
                    case 10:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Nov" + " " + year);
                        break;
                    case 11:
                        table_date_day_TextView.setText(dayOfMonth + " " + "Dec" + " " + year);
                        break;
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void getTomorrowsDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, EEEE");
        String date = dateFormat.format(calendar.getTime());

        table_date_day_TextView.setText(date);
    }

    private void getTodayDate() {
        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, EEEE");
        String date = dateFormat.format(calendar.getTime());
        table_date_day_TextView.setText(date);
    }

    private void showBottomSheetOnButtonCLick() {
        View rootView = getLayoutInflater().inflate(R.layout.table_bottomsheet_1, null);
        table_today_button = rootView.findViewById(R.id.table_fragment_today_button);
        table_tomorrow_button = rootView.findViewById(R.id.table_fragment_tomorrow_button);
        table_pick_a_date_TV = rootView.findViewById(R.id.pick_date_TV);
        table_date_day_TextView = rootView.findViewById(R.id.date_day_textView);
        table_time_TV = rootView.findViewById(R.id.time_TV);
        datePickerButton = rootView.findViewById(R.id.datePickerButton);

        calendar_icon = rootView.findViewById(R.id.calendar_icon);
        table_pick_a_date_button = rootView.findViewById(R.id.table_fragment_pick_date_button);
        table_next_button = rootView.findViewById(R.id.table_next_button);


        final BottomSheetDialog table_bottomSheetDialog_1 = new BottomSheetDialog(getActivity(), R.style.BottomSheetStyle);
        table_bottomSheetDialog_1.setContentView(rootView);
        table_bottomSheetDialog_1.show();

        table_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTableBottomSheet_2(table_bottomSheetDialog_1);
                table_bottomSheetDialog_1.dismiss();
            }
        });

    }

}