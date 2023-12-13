package com.example.aircraftwar2024.record;

import android.content.Context;
import android.util.Log;

import com.example.aircraftwar2024.activity.GameActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRecordController {
    private Context context;
    //private List<UserRecord> data;
    private List<Map<String, String>> data;

    public UserRecordController(Context context) {
        this.context = context;
    }

    public static UserRecordController getInstance(Context context){
        UserRecordController instance=null;

        if(instance == null){
            instance = new UserRecordController(context);
        }
        return instance;
    }
    public void updateRecords(List<Map<String, String>> data){
        sortData(data);
        setRank(data);
    }
    public List<Map<String,String>> getInitialRecords(){
        data = new ArrayList<>();
        Comparator<UserRecord> scoreComparator = Comparator.comparing(UserRecord::getScore);
        FileInputStream in = null;
        ObjectInputStream ois = null;
        //打开排行榜页面时读文件
        try {
            in = context.openFileInput("records.txt");
            ois = new ObjectInputStream(in);

            while(true){
                UserRecord record = (UserRecord) ois.readObject();
                data.add(transferRecord(record));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(in !=null)
                    in.close();
                if(ois != null)
                    ois.close();
               // if(data != null)
                    //data.sort(scoreComparator);
                Log.i("Controller","get data is " + data.size());
                return data;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<Map<String,String>> getCurrentRecords(){
        Log.i("Controller","get current record is " + data.size());
        return data;
    }

    private Map<String, String> transferRecord(UserRecord record){
        Map<String, String> map = new HashMap<>();
        map.put("rank",String.valueOf(record.getRank()));
        map.put("name","test");
        map.put("score",String.valueOf(record.getScore()));
        map.put("date",record.getDate());
        return map;
    }

    private UserRecord transferToReocrd(Map<String, String> map){
        UserRecord record = new UserRecord("test",Integer.valueOf(map.get("score")),map.get("date"));
        return record;
    }

    public void addUserRecord(UserRecord record,List<Map<String,String>> data){
        //找到对应的rank

        if(data != null){
            Map<String,String> newRecord = transferRecord(record);
            data.add(newRecord);
        }
        sortData(data);
        setRank(data);
        Log.i("Controller","add data is " + data.size());
    }

    private void setRank(List<Map<String,String>> data){
        int index = 0;
        for(Map<String,String> record:data){
            index++;
            record.put("rank",String.valueOf(index));
        }
    }
    private void sortData(List<Map<String,String>> data){
        /*
        List<Map<String,String>> data1 = new ArrayList<>();
        Map<String, String> rec1 = new HashMap<String, String>() ;
        rec1.put("score", "30");
        Map<String, String> rec2 = new HashMap<String, String>() ;
        rec2.put("score", "10");
        Map<String, String> rec3 = new HashMap<String, String>() ;
        rec3.put("score", "20");

        data1.add(rec1);
        data1.add(rec2);
        data1.add(rec3);
        //data1.sort((num1,num2)->{return Integer.valueOf(num1.get("score")).compareTo(Integer.valueOf((num2.get("score"))));});
        */

        Collections.sort(data, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String > o1, Map<String, String> o2) {
                Log.i("controller","score1 is " + o1.get("score"));
                Integer one = Integer.valueOf(o1.get("score"));
                Integer two = Integer.valueOf(o2.get("score"));

                return two.compareTo(one);  //one.compareTo(two)为升序，two.compareTo(one)为降序
            }
        });

        Log.i("controller", "data1 is " + data.toString());
    }

    public void saveUserRecords(List<Map<String,String>> data){
        //离开排行榜页面时
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            out = context.openFileOutput("records.txt", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(out);
            Log.i("Controller","save data is " + data.size());
            for(Map<String, String> map:data){
                UserRecord record = transferToReocrd(map);
                oos.writeObject(record);
            }
            if(oos!=null)
                oos.close();
            if(out!=null)
                out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
