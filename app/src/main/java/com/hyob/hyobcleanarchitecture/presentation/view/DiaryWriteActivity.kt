package com.hyob.hyobcleanarchitecture.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.hyob.hyobcleanarchitecture.R
import com.hyob.hyobcleanarchitecture.base.BaseActivity
import com.hyob.hyobcleanarchitecture.base.string
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModel
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModelFactory
import kotlinx.android.synthetic.main.activity_diary_write.*

class DiaryWriteActivity : BaseActivity<DiaryViewModel>() {

    private val diaryViewModelFactory by lazy {
        DiaryViewModelFactory(this)
    }

    override fun createVM(): DiaryViewModel =
        ViewModelProviders.of(this, diaryViewModelFactory).get(DiaryViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_write)

        setSupportActionBar(toolbar)

        vm.getDiaryCreated().observe({ lifecycle }, {
            startActivity(DiaryActivity::class.java, Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })

        vm.getToastContent().observe({ lifecycle }, {
            toast(it)
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_write, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_create -> {
                vm.createNewDiary(
                    editDiaryTitle.string(),
                    editDiaryContent.string()
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
