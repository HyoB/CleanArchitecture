package com.hyob.hyobcleanarchitecture.util.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DiaryListDecorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0)
            outRect.set(15 * 4, 15 * 4, 15 * 4, 10 * 4)
        else
            outRect.set(15 * 4, 0, 15 * 4, 10 * 4)
    }
}