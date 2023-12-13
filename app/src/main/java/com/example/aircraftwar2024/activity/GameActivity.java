package com.example.aircraftwar2024.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.R;
import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.EasyGame;
import com.example.aircraftwar2024.game.HardGame;
import com.example.aircraftwar2024.game.MediumGame;
import com.example.aircraftwar2024.record.UserRecord;
import com.example.aircraftwar2024.record.UserRecordController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameActivity";

    private int gameType=0;
    public static int screenWidth,screenHeight;
    private List<Map<String, String>> data = null;
    private Handler handler;
    private UserRecordController controller =UserRecordController.getInstance(this);
    private List<Map<String,String>> records = null;

    @Override
    protected void onResume() {

        super.onResume();
        Log.i(TAG,"resume game activity");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"create activity");

        getScreenHW();


        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG,"handleMessage");

                if (msg.what == 1) {
                    setContentView(R.layout.activity_record);
                    Button saveRecBtn = findViewById(R.id.saveRecBtn);
                    ListView list = findViewById(R.id.listview);
                    saveRecBtn.setOnClickListener(GameActivity.this);
                    records = controller.getInitialRecords();
                    UserRecord record = (UserRecord) msg.obj;
                    controller.addUserRecord(record,records);

                    SimpleAdapter adapter = new SimpleAdapter(GameActivity.this,records,R.layout.activity_item,
                            new String[]{"rank","name","score","date"},
                            new int[]{R.id.rank,R.id.name,R.id.score,R.id.date});

                    list.setAdapter(adapter);

                    list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                            builder.setMessage("确定删除第"+(position+1)+"条数据？");
                            builder.setTitle("提示");
                            builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface,int i){
                                    //从数据源中删除选中数据，重新排序
                                    //刷新列表数据
                                    records.remove(position);
                                    //调用接口更新数据源
                                    controller.updateRecords(records);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                            builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface,int i){
                                }
                            });
                            builder.create().show();
                            return false;
                        }
                    });

                }

            }
        };
        if(getIntent() != null){
            gameType = getIntent().getIntExtra("gameType",1);
        }
        BaseGame basGameView;
        Log.d(TAG,"gameType="+gameType);
        if(gameType == 1){
            basGameView = new EasyGame(this,handler);

        }else if(gameType == 2){
            basGameView = new MediumGame(this,handler);
        }else{
            basGameView = new HardGame(this,handler);
        }
        setContentView(basGameView);
    }

    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getDisplay().getRealMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.saveRecBtn){
            //将排行榜数据存回文件
            UserRecordController.getInstance(this).saveUserRecords(records);
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        }
    }
}