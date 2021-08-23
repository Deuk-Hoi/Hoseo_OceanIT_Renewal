package com.deuksoft.hoseooceanit2.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.UserInfoDTO
import com.deuksoft.hoseooceanit2.ui.login.LoginActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.SaveInfoManager.UserInfo
import com.deuksoft.hoseooceanit2.databinding.ActivityMainBinding
import com.deuksoft.hoseooceanit2.ui.field.FieldFragment
import com.deuksoft.hoseooceanit2.ui.home.HomeFragment
import com.deuksoft.hoseooceanit2.ui.member.MemberFragment
import com.deuksoft.hoseooceanit2.ui.result.ResultFragment
import org.w3c.dom.Text

/*
* @author: 김득회
* @작성일 : 2021-08-13
* */

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

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
        binding.mainLoginBtn.setOnClickListener(this)
        binding.logoutBtn.setOnClickListener(this)
    }


    /*
    * 네비게이션 (서랍상자 컨트롤 하는 부분으로 각 컨텐츠에 따라서 뷰를 다르게 보여주기 위해 설정.
    * */

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment = Fragment()
        when(item.itemId){
            R.id.nav_home ->{
                mainAppbar()
                fragment = HomeFragment()
            }
            R.id.nav_member ->{
                otherContentAppbar(true)
                fragment = MemberFragment()
            }
            R.id.nav_result ->{
                otherContentAppbar(false)
                fragment = ResultFragment()
            }
            R.id.nav_field -> {
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
        var toggle = ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.mainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
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
        var toggle = ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.customToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun changeFragment(flag: Int){
        var fragment = Fragment()
        when(flag){
            0->{
                otherContentAppbar(true)
                fragment = MemberFragment()
            }
            1->{
                otherContentAppbar(false)
                fragment = ResultFragment()
            }
            2->{
                otherContentAppbar(false)
                fragment = FieldFragment()
            }
            3-> {

            }
        }
        fragment.let{
            supportFragmentManager.apply {
                beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).addToBackStack(null).commit()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //fragment stack이 없어서 더이상 보여줄 화면이 없을경우 홈화면에서 보여지는 app bar을 사용한다.
        if(supportFragmentManager.backStackEntryCount==0) mainAppbar()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.mainLoginBtn.id ->{
                startActivityForResult(Intent(this, LoginActivity::class.java), REQUEST)
            }
            binding.logoutBtn.id ->{
                showDialog("로그아웃", "로그아웃 하시겠습니까?")
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 100){
            var userInfo = data!!.getSerializableExtra("response") as UserInfoDTO
            findViewById<ConstraintLayout>(R.id.centerNameCard).isVisible = true
            findViewById<ConstraintLayout>(R.id.centerCardNotLogin).isVisible = false
            findViewById<TextView>(R.id.nameOne).text = userInfo.userName.substring(1,2)
            findViewById<TextView>(R.id.departement).text = "${userInfo.userBelong} ${userInfo.userDepartment}"
            findViewById<TextView>(R.id.userName).text = "${userInfo.userPosition} ${userInfo.userName}"
            binding.apply {
                mainLoginBtn.isVisible = false
                logoutBtn.isVisible = true
            }
            drawerLayout.closeDrawer(Gravity.LEFT)
        }
    }

    fun showDialog(title: String, content : String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                binding.apply {
                    mainLoginBtn.isVisible = true
                    logoutBtn.isVisible = false
                }
                findViewById<TextView>(R.id.nameOne).text = ""
                findViewById<TextView>(R.id.departement).text = ""
                findViewById<TextView>(R.id.userName).text = ""
                findViewById<ConstraintLayout>(R.id.centerNameCard).visibility = View.INVISIBLE
                findViewById<ConstraintLayout>(R.id.centerCardNotLogin).isVisible = true
                drawerLayout.closeDrawer(Gravity.LEFT)
            })
            .setNegativeButton("취소",null)
            .show()
    }


    companion object{
        val REQUEST = 100
    }
}