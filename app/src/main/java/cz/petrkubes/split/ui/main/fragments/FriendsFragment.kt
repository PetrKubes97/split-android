package cz.petrkubes.split.ui.main.fragments.expenses

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.FragmentRecyclerViewBinding
import cz.petrkubes.split.ui.main.Friend
import cz.petrkubes.split.ui.main.adapters.RecyclerViewAdapter

/**
 * Created by Petr Kubes on 15.7.17.
 */
class FriendsFragment : android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bindings: FragmentRecyclerViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)

        // Add data to listview
        val adapter: RecyclerViewAdapter = RecyclerViewAdapter(listOf(Friend(1,"Petr"), Friend(2, "JIrka")), R.layout.item_friend)
        bindings.recyclerView.layoutManager = LinearLayoutManager(activity)
        bindings.recyclerView.adapter = adapter

        return bindings.root
    }
}
