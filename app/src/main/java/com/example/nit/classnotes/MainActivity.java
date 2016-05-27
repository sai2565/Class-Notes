package com.example.nit.classnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {


    android.support.v4.app.Fragment fragment =null ;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static String APP_ID= "B65B1F1E-6196-1D5A-FFB5-FE042EC7CF00";
    public static String SECRET_KEY = "A51DBC04-E0E0-C86D-FF30-F22B2CBE9B00";
    public static String VERSION = "v1";
    ListView listView;
    String[] title = {"Hydrolysis Process","Decardoxidation process of Sodium Carbonate using..."};
    String[] description = {"Hydrolysis is a reaction involving the breaking of a bond in a molecule using water.","Decarboxylation is a chemical reaction that removes a carboxyl group and releases carbon dioxide (CO2)."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);
        //startActivity(new Intent(MainActivity.this,Main2Activity.class));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.todo);
        tabLayout.getTabAt(2).setIcon(R.drawable.assessment);

/*listView = (ListView)findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(this,title,description);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);*/


   /*     Bitmap bm =  BitmapFactory.decodeResource(getResources(), R.drawable.likea);
        ImageView iv = (ImageView)findViewById(R.id.imageView3);

//iv.setImageBitmap(bm);
        String s= BitMapToString(bm);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
Bitmap b=StringToBitMap(s);
        iv.setImageBitmap(b);
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()) {
            case R.id.action_settingsw:

                return true;
        }
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.savednotes) {
            // Handle the camera action
        } else if (id == R.id.makenotes) {
            drawer.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this,ScrollingActivity.class));

        } else if (id == R.id.sharenotes) {

        } else if (id == R.id.findnotes) {


        } else if (id == R.id.asknotes) {


        }else if (id == R.id.postassess) {


        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if(id == R.id.login){startActivity(new Intent(MainActivity.this,LoginActivity.class));}


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
  /*  public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }*/

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

         /*   switch (position) {

                case 0:
                    fragment = new BlankFragment();
                case 1:

                case 2:


            }

                if(position == 0){return new ItemFragment();}
          if(position == 0){return new BlankFragment();}
            else if(position == 1){return null;}
            else {return null;}*/

            if(position == 0){return new Home();}else if(position == 1) {return new ToDo();}
            else{return new Assessment();}

        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

       /* @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "ToDo";
                case 2:
                    return "Assessment";
            }
            return null;
        }*/
    }

}
