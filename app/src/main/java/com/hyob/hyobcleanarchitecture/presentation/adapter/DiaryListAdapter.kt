package com.hyob.hyobcleanarchitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyob.hyobcleanarchitecture.R
import com.hyob.hyobcleanarchitecture.base.gone
import com.hyob.hyobcleanarchitecture.base.visible
import com.hyob.hyobcleanarchitecture.entity.Diary
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData
import kotlinx.android.synthetic.main.item_diary_list.view.*

class DiaryListAdapter(private val delegate: Delegate): ListAdapter<DiaryViewData, DiaryListAdapter.DiaryListHolder>(COMPARATOR) {

    interface Delegate {
        fun onDiaryClick(diaryViewData: DiaryViewData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_diary_list, parent, false).let {
            DiaryListHolder(it)
        }

    override fun onBindViewHolder(holder: DiaryListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateDiaryDeleteCheckBox(show: Boolean) {
        for(idx in 0 .. itemCount - 1){
            getItem(idx).showDiaryDeleteCheckBox = show
            notifyItemChanged(idx)
        }
    }

    inner class DiaryListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(diaryViewData: DiaryViewData) = with(itemView){
            diaryViewData.run {
                tvDiaryTitle.text = title
                checkDeleteDiary.setOnCheckedChangeListener { _, isChecked ->
                    selectedForDelete = isChecked
                }
                if (showDiaryDeleteCheckBox){
                    checkDeleteDiary.visible()
                    setOnClickListener {
                        checkDeleteDiary.isChecked = !selectedForDelete
                    }
                } else {
                    checkDeleteDiary.gone()
                    setOnClickListener {
                        delegate.onDiaryClick(diaryViewData)
                    }
                }
            }
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<DiaryViewData>() {
            override fun areItemsTheSame(oldItem: DiaryViewData, newItem: DiaryViewData): Boolean = false

            override fun areContentsTheSame(oldItem: DiaryViewData, newItem: DiaryViewData): Boolean = false


        }
    }

}