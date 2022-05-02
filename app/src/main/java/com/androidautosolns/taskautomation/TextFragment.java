package com.androidautosolns.taskautomation;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class messageScheduleTask extends TimerTask{
    public void run (){

    }
}


public class TextFragment extends Fragment {
    private Button backToMenu;
    private EditText phoneId;
    private EditText messageId;
    private EditText timeId;
    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        backToMenu = view.findViewById(R.id.text_backButton);
        backToMenu.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_textFragment_to_menu));

        phoneId = (EditText) view.findViewById(R.id.phone_id);
        messageId= (EditText) view.findViewById(R.id.message_id);
        timeId = (EditText) view.findViewById(R.id.time_id);
        String test= messageId.getText().toString();

        //Send the message to a number
        final Button schedule = view.findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String message = messageId.getText().toString();
                String phoneNumber= phoneId.getText().toString();
                String time = timeId.getText().toString();  // 24 hr format HH:MM
                String [] timeParts = time.split(":");
                Date currentDate = new Date();

                int hrs= Integer.parseInt(timeParts[0]);
                int min= Integer.parseInt(timeParts[1]);

                System.out.println(hrs+" "+min);

                int currentDateHour = currentDate.getHours();
                int currentDateMin= currentDate.getMinutes();
                //print(Integer.toString(currentDateMin));
                print(Integer.toString(min));
                long delayForSchedule= (currentDateHour - hrs)*60 + (min - currentDateMin)*60;
                Date date = new Date(currentDate.getYear(), currentDate.getMonth(),currentDate.getDate(), hrs, min);
                showMessageSchedule(delayForSchedule);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sendMessage(message,phoneNumber);
                    }
                    }, delayForSchedule*1000);


                //sendMessage(message, phoneNumber, time);
               /* //Get Intent
                Intent intent = new Intent(getContext(),TextFragment.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0, intent,0 );
                // Send a message to 9836422454
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber,null, message, pendingIntent, null);
                */
                // Show a toast
                showMessageSchedule(delayForSchedule);
            }
        }
        );
    }

    private void showMessageSchedule(long delayForSchedule){
        Context context= getContext().getApplicationContext();
        Toast toast = Toast.makeText(context, "Message Scheduled after "+delayForSchedule
                +" seconds", Toast.LENGTH_SHORT);
        toast.show();
    }
    private void print (String str)
    {
        Context context= getContext().getApplicationContext();
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.show();
    }
    private void sendMessage(String message, String phoneNumber){
        Intent intent = new Intent(getContext(),TextFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0, intent,0 );
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null, message, pendingIntent, null);

    }
    /*public void textOkClick(View view){
        TextView test = view.findViewById(R.id.textView);
        test.setText("Good Job!!");
    }*/

}