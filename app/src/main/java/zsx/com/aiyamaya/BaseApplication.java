package zsx.com.aiyamaya;

import android.app.Activity;
import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import zsx.com.aiyamaya.item.EmojiItem;
import zsx.com.aiyamaya.item.UserItem;
import zsx.com.aiyamaya.util.MyUtil;
import zsx.com.aiyamaya.util.SpfUtil;

/**
 * Created by moram on 2016/12/23.
 */

public class BaseApplication extends Application{

    private static BaseApplication instance;

    public static BaseApplication getAPPInstance(){
        return instance;
    }

    private UserItem mUser;

    private static List<EmojiItem> emojiItemList=new ArrayList<>();

    {
        PlatformConfig.setWeixin("wxf4b8773580a65693", "5dd54d261fe64389eb3c79b4de4b9319");
        PlatformConfig.setSinaWeibo("1862317283", "dc5e758fd07b84055f949875ad001ffb","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1106050992", "dBN4CCRba77fxp7k");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        //初始化缓存
        SpfUtil.init(this);
        Config.DEBUG = true;
        UMShareAPI.get(this);
    }


    public static void getEmoji(final Activity activity){
        emojiItemList.clear();
        MyUtil.runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream=activity.getAssets().open("emoji.txt");
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    String strLine;
                    while((strLine=bufferedReader.readLine())!=null){
                        String[] arr=strLine.split(",");
                        String[] namearr=arr[0].split("\\.");
                        emojiItemList.add(new EmojiItem(arr[1],namearr[0]));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static List<EmojiItem> getEmojiItemList(){
        return emojiItemList;
    }

    public UserItem getmUser() {
        return mUser;
    }

    public void setmUser(UserItem mUser) {
        this.mUser = mUser;
    }
}
