package com.example.administrator.nativeproject.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.nativeproject.R;
import com.example.administrator.nativeproject.bean.LogLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LogService extends Service {


    private WindowManager wManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性
    private ListView listview;
    private LinkedList<LogLine> logList = new LinkedList<>();
    private LogAdapter mAdapter;
    private final int MAX_LINE = 500;
    private SimpleDateFormat LOGCAT_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    private Thread readLog;
    private boolean isAllowReadLog = false;

    private boolean flag = true;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("zuofei","服务启动");
//Utility.LOG_TAG 为自定义的logString，service会读取此log

        readLog = new Thread(new LogReaderThread("ZF"));
        readLog.start();
        createSystemWindow();
        isAllowReadLog = true;
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        removeSystemWindow();
        isAllowReadLog = false;
        super.onDestroy();
    }

    private void createSystemWindow() {
        wManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//焦点
        mParams.width = 490;
        mParams.height = 160;
        mParams.x = 0;
        mParams.y = 0;
//        mParams.alpha = 0.1f;

        // lp.gravity=Gravity.LEFT|Gravity.TOP; //调整悬浮窗口至左上角
        // 以屏幕左上角为原点，设置x、y初始化
        // lp.x=0;
        // lp.y=0;
        final LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listview = (ListView) inflator.inflate(R.layout.log_window, null);
        logList = new LinkedList<>();
        mAdapter = new LogAdapter(this, logList);
        listview.setAdapter(mAdapter);
        if (isAllowReadLog) {
            if(flag){
                flag = false;
                wManager.addView(listview, mParams);
            }

        }

    }

    private void removeSystemWindow() {
        if (listview != null && listview.getParent() != null) {
            wManager.removeView(listview);
        }
    }

    class LogAdapter extends ArrayAdapter<LogLine> {

        private LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        public LogAdapter(Context context, List<LogLine> objects) {
            super(context, 0, objects);
        }

        public void add(LogLine line) {
            logList.add(line);
            notifyDataSetChanged();
        }

        @Override
        public LogLine getItem(int position) {
            return logList.get(position);
        }

        @Override
        public int getCount() {
            return logList.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LogLine line = getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflator.inflate(R.layout.log_line, parent, false);
                holder.time =  convertView.findViewById(R.id.log_time);
                holder.content = convertView.findViewById(R.id.log_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.time.setText(line.time);
            holder.content.setText(line.content);
            if (line.color != 0) {
                holder.content.setTextColor(line.color);
            } else {
                holder.content.setTextColor(getResources().getColor(android.R.color.white));
            }
            return convertView;
        }

    }

    class ViewHolder {
        public TextView time;
        public TextView content;
    }

    class LogReaderThread implements Runnable {
        private String filter;

        public LogReaderThread(String filter) {
            this.filter = filter;
        }

        @Override
        public void run() {
            Log.i("zuofei","线程启动");
            Process mLogcatProc = null;
            BufferedReader reader = null;
            try {
                mLogcatProc = Runtime.getRuntime().exec(new String[]{"logcat", filter + " *:S"});
                reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
                String line;

                while (isAllowReadLog) {
                    if ((line = reader.readLine()) != null) {
                        Message msg = new Message();
                        msg.obj = line;
                        handler.sendMessage(msg);
                        Log.i("zuofei",msg.obj.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buildLogLine(String line) {
        LogLine log = new LogLine();
        log.time = LOGCAT_TIME_FORMAT.format(new Date()) + ": ";
        if (line.startsWith("I")) {
            log.color = Color.parseColor("#008f86");
        } else if (line.startsWith("V")) {
            log.color = Color.parseColor("#fd7c00");
        } else if (line.startsWith("D")) {
            log.color = Color.parseColor("#8f3aa3");
        } else if (line.startsWith("E")) {
            log.color = Color.parseColor("#fe2b00");
        }
        if (line.contains(")")) {
            line = line.substring(line.indexOf(")") + 1, line.length());
        }
        log.content = line;

        while (logList.size() > MAX_LINE) {
            logList.remove();
        }
        mAdapter.add(log);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            buildLogLine(msg.obj.toString());
        }

        ;
    };

}