package app.com.example.android.camera_bottom_bar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity  implements AHBottomNavigation.OnTabSelectedListener {


    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = (AHBottomNavigation)findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }
    private void createNavItems(){
        AHBottomNavigationItem home_Item = new AHBottomNavigationItem("Home",R.drawable.ic_home);
        AHBottomNavigationItem notification_Item = new AHBottomNavigationItem("Notifications",R.drawable.ic_notifications);
        AHBottomNavigationItem Account_Item = new AHBottomNavigationItem("Account",R.drawable.ic_account);
        AHBottomNavigationItem Call_Item = new AHBottomNavigationItem("Call Us",R.drawable.ic_call);

        bottomNavigation.addItem(home_Item);
        bottomNavigation.addItem(notification_Item);
        bottomNavigation.addItem(Account_Item);
        bottomNavigation.addItem(Call_Item);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#B39DDB"));

        bottomNavigation.setCurrentItem(0);
    }


    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        if (position == 0){
            home_Fragment frag_Home = new home_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_id,frag_Home)
                    .commit();
        }

        if (position == 1){
            Toast.makeText(this,"yes, its working",Toast.LENGTH_SHORT).show();
        }

        if (position == 2){
            Toast.makeText(this,"yes, its working",Toast.LENGTH_SHORT).show();
        }

        if (position == 3){
           call_Fragment  frag_Call = new call_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_id,frag_Call)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.action_settings){
            Toast.makeText(MainActivity.this,"You just pressed the overflow menu button",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
