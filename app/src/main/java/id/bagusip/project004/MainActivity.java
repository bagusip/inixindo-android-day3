package id.bagusip.project004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import id.bagusip.project004.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Fragment fragment = null;

    //buat toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //select toolbar with fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_contact_us:
                fragment = new ContactUsFragment();
                getSupportActionBar().setTitle("Contact Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_about_us:
                fragment = new AboutUsFragment();
                getSupportActionBar().setTitle("About Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle("Profile");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
            case R.id.profile_picture:
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle("Profile");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();

    }

    private void initView() {
        // custom toolbar
        setSupportActionBar(binding.toolbar);

        //default fragment yang dibuka pertama kali. (menu home muncul pertama kali)
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new HomeFragment())
                .commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        //membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // sinkronisasi drawer
        toggle.syncState();

        //salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            //option selected draw
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_contact_us:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Contact Us");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_about_us:
                        fragment = new AboutUsFragment();
                        getSupportActionBar().setTitle("About Us");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        getSupportActionBar().setTitle("Profile");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                }
                return true;
            }
        });

        NavigationView navigationView = findViewById(R.id.navView);
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_layout, navigationView, false);
        navigationView.addHeaderView(headerView);


        Button btn_go_to_web = headerView.findViewById(R.id.btn_go_web);
        btn_go_to_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://bagusindiarto.com"));
                startActivity(intent);
            }
        });

    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        transaction.replace(R.id.frame_layout, fragment);
        //menunya bergantian
        transaction.addToBackStack(null);
        transaction.commit();
    }
}