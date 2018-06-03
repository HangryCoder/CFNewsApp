package newsapp.sonia.com.cfnewsapp.utils

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

abstract class OnSwipeToDismissCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean = true

    abstract override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int)
}