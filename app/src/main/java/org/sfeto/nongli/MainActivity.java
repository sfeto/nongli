package org.sfeto.nongli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String last_class = MyService.readConfig(this);
        String className = last_class.substring(last_class.lastIndexOf(".")+1).toLowerCase();
        int res_id;
        if (className.equals("MainActivity".toLowerCase())) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
            return;
        }

        res_id = getResources().getIdentifier(className, "drawable", getPackageName());

        ImageView img_nongli = (ImageView) findViewById(R.id.img_nongli);
        img_nongli.setImageResource(res_id);
    }
    public void start_onclick(View view){
        startActivity(new Intent(this, HomeActivity.class));
    }
    public void change_onclick(View view){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        finish();
    }


}
