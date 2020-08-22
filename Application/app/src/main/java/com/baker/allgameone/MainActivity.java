package com.baker.allgameone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {

    FragmentOne fragmentone;
    FragmentTwo fragmenttwo;
    FragmentThree fragmentthree;
    FragmentFour fragmentfour;
    FragmentFive fragmentfive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterFullScreen();

        fragmentone = new FragmentOne();
        fragmenttwo = new FragmentTwo();
        fragmentthree = new FragmentThree();
        fragmentfour = new FragmentFour();
        fragmentfive = new FragmentFive();
        

        final SpaceNavigationView spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.showIconOnly();

        // Change the color of the navigation bar background.
        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.darkNav));

        // Set the center button icon.
        spaceNavigationView.setCentreButtonIcon(R.drawable.game);
        // Set the center button background color.
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.gameButton));

        // Set the active icon color when pressed.
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.white));

        // Set the navigation icons.
        spaceNavigationView.addSpaceItem(new SpaceItem("PROFILE", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("NEWS", R.drawable.news));
        spaceNavigationView.addSpaceItem(new SpaceItem("TWITCH", R.drawable.twitch));
        spaceNavigationView.addSpaceItem(new SpaceItem("PROFILE", R.drawable.profile));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                setFragment(fragmentthree);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        setFragment(fragmentone);
                        Log.d("Debug", "Home button pressed");
                        return;
                    case 1:
                        setFragment(fragmenttwo);
                        Log.d("Debug", "Second button pressed");
                        return;
                    case 2:
                        setFragment(fragmentfour);
                        Log.d("Debug", "forth button pressed");
                        return;
                    case 3:
                        setFragment(fragmentfive);
                        Log.d("Debug", "Twitch button pressed");
                        return;
                    default:
                        setFragment(fragmentone);
                        Log.d("Debug", "Home button pressed");
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }

    // Get the current fragment and use it.
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

    private void enterFullScreen() {
        // Remove the phones navigation bar and enter full screen.
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onResume() {
        super.onResume();
        enterFullScreen();
    }

}