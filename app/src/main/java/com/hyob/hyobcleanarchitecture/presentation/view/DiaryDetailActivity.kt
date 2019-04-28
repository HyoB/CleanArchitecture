package com.hyob.hyobcleanarchitecture.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        vm.getDiaryDetail().observe {
            updateDiaryUi(it)
        }
    }

    private fun updateDiaryUi(diaryViewData: DiaryViewData) = with(diaryViewData){
        textDiaryTitle.text = title
        textDiaryContent.text = content
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
