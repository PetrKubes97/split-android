package cz.petrkubes.split.ui.main.ui.fragments.friends

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.FragmentRecyclerViewBinding
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.RecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
class FriendsFragment : LifecycleFragment() {

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

    private var users: MutableList<User> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        friendsViewModel = ViewModelProviders.of(this, ViewModelFactory(activity.application as App)).get(FriendsViewModel::class.java)

        friendsViewModel.observableFriends()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { observedFriends: List<User> ->
                    users.clear()
                    users.addAll(observedFriends)
                    binding.recyclerView.adapter.notifyDataSetChanged()
                }


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)

        // Add data to listview
        val adapter = RecyclerViewAdapter(users, R.layout.item_friend)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}
