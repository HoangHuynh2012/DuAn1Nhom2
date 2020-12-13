package com.example.duan1.customer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duan1.MainActivity;
import com.example.duan1.R;
import com.example.duan1.customer.fragment.TaiKhoanFragment;
import com.example.duan1.customer.fragment.CuaHangFragment;
import com.example.duan1.customer.fragment.MenuFragment;
import com.example.duan1.customer.fragment.TrangChuFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class TrangChuActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    FragmentManager manager;
    Fragment fragment= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        chipNavigationBar = findViewById(R.id.bottomTrangChu);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame1, new TrangChuFragment())
                    .commit();
            chipNavigationBar.setItemSelected(R.id.home, true);
            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

                @Override
                public void onItemSelected(int id) {
                    switch (id) {
                        case R.id.home:
                            fragment = new TrangChuFragment();
                            break;
                        case R.id.menuu:
                            fragment = new MenuFragment();
                            break;
                        case R.id.cuahang:
                            fragment = new CuaHangFragment();
                            break;
                        case R.id.taikhoan:
                            fragment = new TaiKhoanFragment();
                            break;
                    }
                    if (fragment != null) {
                        manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frame1, fragment).commit();
                    } else {
                        Log.e(TAG, "Erro");
                    }

                }
            });
        }


    }   }
//toolbar menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()){
//            case R.id.menu_trangchu :
//                Intent i = new Intent(this,TrangChu.class);
//                startActivity(i);
//                Toast.makeText(TrangChu.this,"Trang chủ",Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.menu_menu :
//                Intent i1 = new Intent(TrangChu.this,Menu.class);
//                startActivity(i1);
//                Toast.makeText(TrangChu.this,"",Toast.LENGTH_LONG).show();
//                return true;
////            case R.id.menu_cuahang :
////                Intent i1 = new Intent(this,Menu.class);
////                startActivity(i1);
////                Toast.makeText(TrangChu.this,"",Toast.LENGTH_LONG).show();
////                return true;
////            case R.id.menu_taikhoan :
////                Intent i1 = new Intent(this,Menu.class);
////                startActivity(i1);
////                Toast.makeText(TrangChu.this,"",Toast.LENGTH_LONG).show();
////                return true;
//            default:
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
