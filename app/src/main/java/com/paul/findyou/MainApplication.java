package com.paul.findyou;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Path;
import android.os.Environment;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.paul.findyou.location.MyLocationService;

import java.io.File;
import java.util.Random;


/**
 * Created by me on 2015/8/5.
 */
public class MainApplication extends Application {
    public LocationClient mLocationClient;

    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(this);

        try {
            MyLocationService.initLocationService(this.getApplicationContext());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        ApplicationContext.setApplicationContext(this.getApplicationContext());
        int i =new Random().nextInt(100);
        int j = new Random().nextInt(100);
        //test1();
    }

    private void test() {
        Context context = this.getApplicationContext();

        String pname = context.getPackageName();
        try {
            Signature[] sigs = getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            for (Signature sig : sigs) {
                System.out.println(sig);
                String sign = new String(sig.toByteArray(), "GBK");
                //Trace.i("MyApp", "Signature hashcode : " + sig.hashCode());
                String str1 = sig.toCharsString();
                String str2 = sig.toString();
                char[] str3 = sig.toChars();
                System.out.println(sign);
                Log.i("sign", sign);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public boolean test1() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File rootDir = Environment.getExternalStorageDirectory();

            String path =  getApplicationContext().getFilesDir().getPath();
            System.out.println(path);
            File f = new File(path);
            try{
                f.createNewFile();
            }catch(Exception e){
                e.printStackTrace();
            }
            File file1 = new File(rootDir, "findyou");
            try {

                file1.deleteOnExit();
                file1.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
