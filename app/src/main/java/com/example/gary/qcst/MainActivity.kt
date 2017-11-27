package com.example.gary.qcst


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
class MainActivity : BaseKActivity(), View.OnClickListener{
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    var isOk = true
    var mTabLayout : TabLayout ?= null
    var mViewPager : ViewPager ?= null
    val fragments = ArrayList<Fragment>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(fragments.size-3, true);
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.setCurrentItem(fragments.size-2, true);
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.setCurrentItem(fragments.size-1, true);
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ss = 1
        mViewPager = findViewById(R.id.viewPager) as ViewPager
        mTabLayout = findViewById(R.id.tabs) as TabLayout
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initViewPager()
    }
    /**
     * 自定义函数, : Unit 表示函数没有返回值
     */
    fun initViewPager() : Unit {
        /**
         * 获取初始化数据
         */
        val titles = ViewData().getTitles()

//        /**
//         * as 类似于java中的类型强转
//         */
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)


        /**
         * 通过 in 关键字实现循环遍历
         * 在调用mTabLayou变量的方法时,由于mTabLayout可能为空,所以在调用方法时添加!!
         * titles[] 与 titles.get 方法的功能是一样的
         * titles.indices 获取的是数组的下标
         */
        for (i in titles.indices) {
            mTabLayout!!.addTab(mTabLayout!!.newTab().setText(titles[i]))
        }


        /**
         * 循环遍历添加ViewPager的Fragment
         */
        for (i in titles.indices) {
            val listFragment = MListFragment()
            val bundle = Bundle()
            val sb = StringBuffer()
            for (j in 1..8) {
                sb.append(titles[i]).append(" ")
            }
            bundle.putString("content", sb.toString())
            if (i<titles.size-3){
                listFragment.arguments = bundle
                fragments.add(listFragment)
            }

        }
        val oneFragment = OneFragment()
        val twoFragment = TwoFragment()
        val threeFragment = ThreeFragment()
        val bundle = Bundle()
        bundle.putString("content", "小财")
        oneFragment.arguments = bundle
        twoFragment.arguments = bundle
        threeFragment.arguments = bundle
        fragments.add(oneFragment)
        fragments.add(twoFragment)
        fragments.add(threeFragment)




        val mFragmentAdapteradapter = MFragmentAdapter(supportFragmentManager, fragments, titles)
        mViewPager!!.adapter = mFragmentAdapteradapter
        mViewPager!!.adapter = mFragmentAdapteradapter
        mTabLayout!!.setupWithViewPager(mViewPager)
        mTabLayout!!.setTabsFromPagerAdapter(mFragmentAdapteradapter)


        /**
         * 通过object : TabLayout.OnTabSelectedListener 的方式创建内部匿名类(这里主要是接口)
         */
        mTabLayout!!.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                /**
                 * 控制变量
                 */
                if (isOk) {
                    isOk = false
                    val currentItemIndex = mViewPager!!.currentItem

                    if (Math.abs(currentItemIndex - tab!!.position) > 1) {
                        /**
                         * 向后点击
                         */
                        if (currentItemIndex <= tab!!.position) {
                            mViewPager!!.setCurrentItem(tab.position - 1, false)
                            mViewPager!!.setCurrentItem(tab.position, true)
                        }
                        /**
                         * 向前点击
                         */
                        else {
                            mViewPager!!.setCurrentItem(tab.position + 1, false)
                            mViewPager!!.setCurrentItem(tab.position, true)
                        }
                    } else {
                        mViewPager!!.setCurrentItem(tab.position, true)
                    }

                    isOk = true
                }
            }
        })

    }




}

/**
 * Created by aaron on 16/9/14.
 * 主要用于保存界面ViewPager数据
 */
class ViewData {

    /**
     * 该方法用于获取ViewPager TAB 显示数据
     */
    fun getTitles() : ArrayList<String> {
        /**
         * 通过类名创建该类的对象,这里直接调用java中的集合框架
         */
        val titles = ArrayList<String>()

        titles.clear()
        titles.add("推荐")
        titles.add("视频")
        titles.add("热点")
        titles.add("娱乐")
        titles.add("体育")
        titles.add("北京")
        titles.add("财经")
        titles.add("科技")
        titles.add("汽车")
        titles.add("社会")
        titles.add("搞笑")
        titles.add("军事")
        titles.add("历史")
        titles.add("涨知识")
        titles.add("NBA")
        titles.add("两性")
        titles.add("one")
        titles.add("two")
        titles.add("three")

        return titles
    }

}
