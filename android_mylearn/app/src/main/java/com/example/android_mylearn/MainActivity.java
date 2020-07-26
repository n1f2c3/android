package com.example.android_mylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MainActivity extends AppCompatActivity {
    private EditText edit_userName;
    private EditText edit_passwd;
    private Button btn_register;
    private Button btn_diag;
    private Button btn_three_permission;
    private Button seconde_activiy;
    private Button four_dy_promission_activiy;
    private Button service_button;
    private static Context context;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        setTitle(R.string.unregister);
//------------------------删除图标
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//---------------------------
//------------------------------------
//        PackageManager p = getPackageManager();
//        ComponentName componentName = new ComponentName(this, MainActivity.class);
//        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
//-----------------------------

        Intent intent=new Intent(this,MyService.class);
        startService(intent);

        builder = new AlertDialog.Builder(this);

        edit_userName = (EditText)findViewById(R.id.edit_username);
        edit_passwd = (EditText)findViewById(R.id.edit_passwd);
        btn_register = (Button)findViewById(R.id.button_register);
        btn_diag=(Button)findViewById(R.id.button);
        seconde_activiy=(Button)findViewById(R.id.button2);
        btn_three_permission=(Button)findViewById(R.id.button3);
        four_dy_promission_activiy=(Button)findViewById(R.id.button4);
        service_button=(Button)findViewById(R.id.button5);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(((TextUtils.isEmpty(edit_userName.getText().toString().trim())))||((TextUtils.isEmpty(edit_passwd.getText().toString().trim())))) {
                    //点击之后，失去焦点才会触发，没特殊要求就不必要使用了
                    //mEdit.setShakeAnimation();

                    Toast.makeText(MainActivity.this, R.string.nulltype, Toast.LENGTH_SHORT).show();


                }else if(check(edit_userName.getText().toString().trim(),edit_passwd.getText().toString().trim())){
                    // 验证成功后弹出注册成功的 Toast
                    Toast.makeText(MainActivity.this,
                            R.string.successed,
                            Toast.LENGTH_SHORT).show();
                    // 设置按钮不可点击
                    btn_register.setEnabled(false);
                    // 设置标题
                    setTitle(R.string.registered);


                }
                else{

                    Toast.makeText(MainActivity.this,
                            R.string.unsuccessed,
                            Toast.LENGTH_SHORT).show();
                    edit_userName.setText("");
                    edit_passwd.setText("");

                }
            }
        });
        btn_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("您現在已輸入賬號密碼")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();


            }
        });
        seconde_activiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShortcut(context);
//                delectShortCut();
                delectShortCut();
                try
                {
//                    startActivity(new Intent("com.example.app_moudle.secend_activity"));//隐式intent
                    Intent intent = new Intent(getApplicationContext(), Second_Activity.class);//显示intent
                    intent.putExtra("username",edit_userName.getText().toString().trim());
                    Bundle b=new Bundle();
                    b.putString("usernamesss",edit_userName.getText().toString().trim());
                    intent.putExtras(b);
                    startActivity(intent);

                }
                catch (Exception ex)
                {
//显示异常信息
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();


                }

            }
        });

        btn_three_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
//                    startActivity(new Intent("com.example.app_moudle.secend_activity"));//隐式intent
                    Intent intent = new Intent(getApplicationContext(), three_permission.class);//显示intent

                    startActivity(intent);

                }
                catch (Exception ex)
                {
//显示异常信息
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();


                }

            }
        });



        four_dy_promission_activiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Four_dyn_promission_Activity.class);//显示intent

                startActivity(intent);
            }
        });


        service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), service1_activit.class);//显示intent

                startActivity(intent);
            }
        });

    }





    public static void addShortcut(Context cx) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        Toast.makeText(cx,"CCCCCCCCCCCCC",
                Toast.LENGTH_SHORT).show();

        Intent shortcutIntent = cx.getPackageManager()
                .getLaunchIntentForPackage(cx.getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 获取当前应用名称
        String title = "XXXXX";
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(cx.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建（不一定有效）
        shortcut.putExtra("duplicate", false);
        // 快捷方式的图标
        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(cx,
                R.drawable.bg_dialog);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        cx.sendBroadcast(shortcut);
    }
//    public static void delShortcut(Context context) {
//        Intent shortcut = new Intent(
//                "com.android.launcher.action.UNINSTALL_SHORTCUT");
//
//        Toast.makeText(context,"CCCCCCCCCCCCC",
//                Toast.LENGTH_SHORT).show();
//        // 获取当前应用名称
//        String title = null;
//        try {
//            final PackageManager pm = context.getPackageManager();
//            title = pm.getApplicationLabel(
//                    pm.getApplicationInfo(context.getPackageName(),
//                            PackageManager.GET_META_DATA)).toString();
//        } catch (Exception e) {
//        }
//        // 快捷方式名称
//        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
//        Intent shortcutIntent = context.getPackageManager()
//                .getLaunchIntentForPackage(context.getPackageName());
//        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
//        context.sendBroadcast(shortcut);
//    }
private void delectShortCut() {
    Log.i("TAG", "删除快捷方式");
    Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));

    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, getShortCutIntent());
    sendBroadcast(shortcut);

}
    private Intent getShortCutIntent() {
        Intent intent = new Intent();
        intent.setClass(this, this.getClass());
        /*以下两句是为了在卸载应用的时候同时删除桌面快捷方式*/
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        return intent;
    }


    public boolean check(String username,String password){
        if ((username=="test") &&( password=="test")){

            return true;
        }else {
            return false;
        }

    }


}
