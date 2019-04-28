package com.hyob.hyobcleanarchitecture.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.hyob.hyobcleanarchitecture.R
import com.hyob.hyobcleanarchitecture.base.BaseActivity
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryContentViewModel
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryContentViewModelFactory
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModel
import com.hyob.hyobcleanarchitecture.presentation.vm.DiaryViewModelFactory
import kotlinx.android.synthetic.main.activity_diary_detail.*

class DiaryDetailActivity : BaseActivity<DiaryContentViewModel>() {

    private val diaryViewModelFactory by lazy {
        DiaryContentViewModelFactory(this)
    }

    override fun createVM(): DiaryContentViewModel =
        ViewModelProviders.of(this, diaryViewModelFactory).get(DiaryContentViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_detail)
        setSupportActionBar(toolbar)

        vm.getDiaryDetail().observe {
            updateDiaryUi(it)
        }

        vm.getDiaryDeleted().observe {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.action_diary_delete -> {
                showDeleteConfirmDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    fun updateDiaryUi(diaryViewData: DiaryViewData) = with(diaryViewData){
        textDiaryTitle.text = title
        textDiaryContent.text = content
    }

    fun showDeleteConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("다이어리 삭제")
            .setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                vm.deleteDiary()
            }
            .setNegativeButton("취소") { _, _ ->}
            .show()
    }

    companion object {
        val KEY_DIARY_ID = "key_diary_id"

        fun start(context: Context, id: Long) = with(context) {
            startActivity(
                Intent(this, DiaryDetailActivity::class.java).apply {
                    putExtra(KEY_DIARY_ID, id)
                }
            )
        }
    }
}
