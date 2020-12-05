package com.skypan.myapplication.driver_model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.skypan.myapplication.passenger_model.PassengerMainActivity;
import com.skypan.myapplication.R;
import com.skypan.myapplication.driver_model.ui.Setting;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DriverMainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private EditText et_startTime;
    private EditText et_endTime;
    private Date startTime = new Date();
    private Date endTime = new Date();
    private TimePickerView pvTime;
    private String[] day = new String[10];
    private Setting temp =new Setting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverMainActivity.this.setButtonCustomDialog();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_House,R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_test)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setButtonCustomDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DriverMainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.set_custom_dialog_layout_with_button,null);
        alertDialog.setView(v);
        Button btOK = v.findViewById(R.id.button_ok);
        Button btC  = v.findViewById(R.id.buttonCancel);
        final Button start  = v.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeChoose(start);
            }
        });
        final Button start2  = v.findViewById(R.id.start2);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeChoose(start2);
            }
        });
        final Button start3  = v.findViewById(R.id.start3);
        start3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeChoose(start3);
            }
        });

        final EditText editText_name = v.findViewById(R.id.name);
        final EditText editText_end = v.findViewById(R.id.end);
        final EditText editText_startTime = v.findViewById(R.id.et_startTime);
        final EditText editText_endTime = v.findViewById(R.id.et_endTime);
        final AlertDialog dialog = alertDialog.create();
        dialog.show();

        final RadioButton mRg1 = v.findViewById(R.id.rb_gender_1);
        final RadioButton mRg2 = v.findViewById(R.id.rb_gender_2);
        final String[] gender = new String[1];
        final RadioButton mRg3 = v.findViewById(R.id.rb_helmet_1);
        final RadioButton mRg4 = v.findViewById(R.id.rb_helmet_2);
        final String[] helmet = new String[1];
        final RadioButton mRg5 = v.findViewById(R.id.rb_fee_1);
        final RadioButton mRg6 = v.findViewById(R.id.rb_fee_2);
        final String[] fee = new String[1];
        int Gender,Halmet,Fee;
        //radiobutton
        {mRg1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                gender[0] = String.valueOf(mRg1.getText());
            }
        });
            mRg2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    gender[0] = String.valueOf(mRg2.getText());
                }
            });
            mRg3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    helmet[0] = String.valueOf(mRg3.getText());
                }
            });
            mRg4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    helmet[0] = String.valueOf(mRg4.getText());
                }
            });

            mRg5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    fee[0] = String.valueOf(mRg5.getText());
                }
            });
            mRg6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    fee[0] = String.valueOf(mRg6.getText());
                }
            });}
        //checkbox
        {final CheckBox mCb1 = v.findViewById(R.id.cb_1);
            final CheckBox mCb2 = v.findViewById(R.id.cb_2);
            final CheckBox mCb3 = v.findViewById(R.id.cb_3);
            final CheckBox mCb4 = v.findViewById(R.id.cb_4);
            final CheckBox mCb5 = v.findViewById(R.id.cb_5);
            final CheckBox mCb6 = v.findViewById(R.id.cb_6);
            final CheckBox mCb7 = v.findViewById(R.id.cb_7);

            mCb1.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb2.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb3.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb4.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb5.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb6.setOnCheckedChangeListener(checkBoxOnCheckedChange);
            mCb7.setOnCheckedChangeListener(checkBoxOnCheckedChange);}

        btOK.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                final Editable Temp;
                temp.setName(editText_name.getText());
                //start
                temp.setEnd(editText_end.getText());
                temp.setStartTime(editText_startTime.getText());
                temp.setEndTime(editText_endTime.getText());
                temp.setGender(gender[0]);
                temp.setHalmet(helmet[0]);
                temp.setFee(fee[0]);



                AlertDialog.Builder twoDialog = new AlertDialog.Builder(DriverMainActivity.this);
                twoDialog.setTitle("這是疊上去的AlertDialog");
                twoDialog.setMessage(temp.getName() + "\n" //start
                        + "\n" + temp.getEnd() + "\n"
                        + temp.getStarttime() + "\n"+ temp.getEndtime() + "\n" + temp.getGneder() + "\n" + temp.getHelmet() + "\n"  + temp.getFee()
                        + "\n" +day[0]+day[1]+day[2]+day[3]+day[4]+day[5]+day[6] );
                twoDialog.setPositiveButton("瞭解", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        dialog.dismiss();
                    }
                }));
                twoDialog.show();
                addSetting.addSetting(temp);
                refresh();
            }

        }));
        btC.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                dialog.dismiss();
            }
        }));

        //time
        et_startTime = v.findViewById(R.id.et_startTime);
        et_endTime = v.findViewById(R.id.et_endTime);
        et_startTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (pvTime != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startTime);
                    pvTime.setDate(calendar);
                    pvTime.show(v1);
                }
            }
        }));
        et_endTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (pvTime != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(endTime);
                    pvTime.setDate(calendar);
                    pvTime.show(v1);
                }
            }
        }));

        initTimePicker();
    }

    private String placeChoose(final Button start) {

        AlertDialog.Builder placealertDialog = new AlertDialog.Builder(DriverMainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.choose_place,null);
        placealertDialog.setView(v);

        final AlertDialog dialog = placealertDialog.create();
        dialog.show();


        final RadioButton mp1 = v.findViewById(R.id.rb_place_1);
        final RadioButton mp2 = v.findViewById(R.id.rb_place_2);
        final RadioButton mp3 = v.findViewById(R.id.rb_place_3);
        final String[] place = new String[1];
        mp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                place[0] = String.valueOf(mp1.getText());
                temp.setStart(place[0],0);
                start.setText(temp.getStart1());
                dialog.dismiss();
            }
        });
        mp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                place[0] = String.valueOf(mp2.getText());
                temp.setStart(place[0],1);
                start.setText(temp.getStart2());
                dialog.dismiss();
            }
        });
        mp3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                place[0] = String.valueOf(mp3.getText());
                temp.setStart(place[0],2);
                start.setText(temp.getStart3());
                dialog.dismiss();
            }
        });
        return place[0];
    }

    private void refresh() {
        Intent intent = new Intent(DriverMainActivity.this, DriverMainActivity.class);
        startActivity(intent);

    }


    private void initTimePicker() {

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //如果是開始時間的EditText
                if(v.getId() == R.id.et_startTime){
                    startTime = date;
                }else {
                    endTime = date;
                }
                EditText editText = (EditText)v;
                editText.setText(getTime(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
                })
                .setType(new boolean[]{true, true, true, true, true, false})
                .isDialog(true)
                .build();


        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改動畫樣式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部顯示
            }
        }
    }

    private String getTime(Date date) {//可根據需要自行擷取資料顯示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(date);
    }

    private final CompoundButton.OnCheckedChangeListener checkBoxOnCheckedChange =
            new CompoundButton.OnCheckedChangeListener() {
                int i = 0;

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //buttonView 為目前觸發此事件的 CheckBox, isChecked 為此 CheckBox 目前的選取狀態
                    if (isChecked)//等於 buttonView.isChecked()
                    {
                        Toast.makeText(getApplicationContext(), buttonView.getText() + " 被選取", Toast.LENGTH_LONG).show();
                        //if((String) buttonView.getId()=="cb_1")i=1;
                        i =(buttonView.getId())- 2131230817;
                        day[i] = String.valueOf(buttonView.getText());
                    } else {
                        Toast.makeText(getApplicationContext(), buttonView.getText() + " 被取消", Toast.LENGTH_LONG).show();
                        i =(buttonView.getId())- 2131230817;
                        day[i] = null;
                    }
                }
            };
}