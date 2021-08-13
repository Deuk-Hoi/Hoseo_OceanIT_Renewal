package com.deuksoft.hoseooceanit2

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.deuksoft.hoseooceanit2.databinding.ActivityMainBinding
import com.deuksoft.hoseooceanit2.ui.field.FieldFragment
import com.deuksoft.hoseooceanit2.ui.home.HomeFragment
import com.deuksoft.hoseooceanit2.ui.member.MemberFragment
import com.deuksoft.hoseooceanit2.ui.result.ResultFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navView: NavigationView = binding.navView
        drawerLayout = binding.drawerLayout

        mainAppbar()
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
    }


    /*
    * 네비게이션 (서랍상자 컨트롤 하는 부분으로 각 컨텐츠에 따라서 뷰를 다르게 보여주기 위해 설정.
    * */

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment = Fragment()
        when(item.itemId){
            R.id.nav_home->{
                mainAppbar()
                fragment = HomeFragment()
            }
            R.id.nav_member->{
                otherContentAppbar(true)
                fragment = MemberFragment()
            }
            R.id.nav_result->{
                otherContentAppbar(false)
                fragment = ResultFragment()
            }
            R.id.nav_field-> {
                otherContentAppbar(false)
                fragment = FieldFragment()
            }

        }
        fragment.let{
            supportFragmentManager.apply {
                beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).addToBackStack(null).commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    /*
    * 메인과 다른 프라그먼트의 차이는 해양IT 텍스트의 유무이다. 다른 프라그먼트와 메인의 뷰는 다르므로 함수를 2개로 나누어 제어한다.
    * setSupportActionBar를 통해 새로운 toolbar로 교체를 해주는 작업과 동기화를 하는 작업을 진행한다.
    * */
    fun mainAppbar(){
        binding.appBarMain.apply {
            mainToolbar.isVisible = true
            customToolbar.isVisible = false
        }
        setSupportActionBar(binding.appBarMain.mainToolbar)
        //supportActionBar!!.setDisplayShowTitleEnabled(false)
        var toggle = ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.mainToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    /*
    * member의 경우 spinner가 필요없으므로 매개변수를 통해 제어를 한다.
    * */
    fun otherContentAppbar(isMember:Boolean){
        findViewById<Spinner>(R.id.contentList).isVisible = !isMember
        binding.appBarMain.apply {
            mainToolbar.isVisible = false
            customToolbar.isVisible = true
        }
        setSupportActionBar(binding.appBarMain.customToolbar)
        //supportActionBar!!.setDisplayShowTitleEnabled(true)
        var toggle = ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.customToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //fragment stack이 없어서 더이상 보여줄 화면이 없을경우 홈화면에서 보여지는 app bar을 사용한다.
        if(supportFragmentManager.backStackEntryCount==0) mainAppbar()
    }
}