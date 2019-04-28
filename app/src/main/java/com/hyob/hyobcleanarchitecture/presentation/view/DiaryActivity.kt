package com.hyob.hyobcleanarchitecture.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyob.hyobcleanarchitecture.R
import com.hyob.hyobcleanarchitecture.base.BaseActivity
import com.hyob.hyobcleanarchitecture.base.gone
import com.hyob.hyobcleanarchitecture.presentation.adapter.DiaryListAdapter
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModel
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModelFactory
import com.hyob.hyobcleanarchitecture.util.decorator.DiaryListDecorator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar

class DiaryActivity : BaseActivity<DiaryViewModel>(), DiaryListAdapter.Delegate {

    private val diaryViewModelFactory by lazy {
        DiaryViewModelFactory(this)
    }

    override fun createVM(): DiaryViewModel =
        ViewModelProviders.of(this, diaryViewModelFactory).get(DiaryViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val diaryAdapter = DiaryListAdapter(this)

        diaryRecyclerView.apply {
            adapter = diaryAdapter
            layoutManager = LinearLayoutManager(this@DiaryActivity)
            addItemDecoration(DiaryListDecorator())
        }

        fabCreateDiary.setOnClickListener {
            startActivity(DiaryWriteActivity::class.java)
        }

        vm.getDiaryContents().observe {
            diaryAdapter.submitList(it)
        }

        vm.getDiaryDeleteCheckBox().observe {
            diaryAdapter.updateDiaryDeleteCheckBox(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_delete_mode -> {
                fabCreateDiary.gone()
                vm.showDiaryDeleteCheckBox(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onNewIntent(intent: Intent?) {
        vm.getAllDiaries()
    }

    override fun onDiaryClick(diaryViewData: DiaryViewData) {
        DiaryDetailActivity.start(this, diaryViewData.id)
    }
}
