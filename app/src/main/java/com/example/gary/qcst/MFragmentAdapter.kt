package com.example.gary.qcst



import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
/**
 * Created by Gary on 2017/11/27.
 */
class MFragmentAdapter(fm : FragmentManager, val mFragments : List<Fragment>, val mTitles : List<String>) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        return mFragments[position]
    }

    override fun getCount(): Int {

        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}