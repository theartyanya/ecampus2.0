package com.example.thear.ecampus20.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.thear.ecampus20.Constants;
import com.example.thear.ecampus20.R;
import com.example.thear.ecampus20.ui.main.bulletins.BulletinsFragment;
import com.example.thear.ecampus20.ui.main.discipline_choice.DisciplineChoiceFragment;
import com.example.thear.ecampus20.ui.main.main.MainFragment;
import com.example.thear.ecampus20.ui.main.rnp.RNPFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.navView);
        setupDrawerContent(nvDrawer);

    }

    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        String string = null;
        switch (menuItem.getItemId()) {
            case R.id.navMain: {
                fragmentClass = MainFragment.class;
                string = Constants.MAIN_TAG;
                break;
            }
            case R.id.navBulletins: {
                fragmentClass = BulletinsFragment.class;
                string = Constants.BULLETINS_TAG;
                break;
            }
            case R.id.navDisciplineChoice: {
                fragmentClass = DisciplineChoiceFragment.class;
                string = Constants.DISCIPLINE_CHOICE_TAG;
                break;
            }
            case R.id.navRNP: {
                fragmentClass = RNPFragment.class;
                string = Constants.RNP_TAG;
                break;
            }
            case R.id.navExit: {
                exit();
                break;
            }
        }
        if (fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainContentLayout, fragment)
                        .addToBackStack(string)
                        .commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private void exit() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}