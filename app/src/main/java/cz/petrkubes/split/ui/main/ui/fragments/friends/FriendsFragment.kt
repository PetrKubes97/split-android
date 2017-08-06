package cz.petrkubes.split.ui.main.ui.fragments.friends

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.FragmentRecyclerViewBinding
import cz.petrkubes.split.ui.main.model.data.Friend
import cz.petrkubes.split.ui.main.ui.adapters.RecyclerViewAdapter


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
class FriendsFragment : LifecycleFragment() {

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

    private var friends: MutableList<Friend> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        friendsViewModel = ViewModelProviders.of(this).get(FriendsViewModel::class.java)

        friendsViewModel.friends.observe(this, Observer<MutableList<Friend>> {
            observedFriends ->
                if (observedFriends != null) {
                    friends.clear()
                    friends.addAll(observedFriends)
                }

                binding.recyclerView.adapter.notifyDataSetChanged()
        })

        friendsViewModel.loadData()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)

        // Add data to listview
        val adapter: RecyclerViewAdapter = RecyclerViewAdapter(friends, R.layout.item_friend)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}