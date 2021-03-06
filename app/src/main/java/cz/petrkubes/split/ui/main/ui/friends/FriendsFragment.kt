package cz.petrkubes.split.ui.main.ui.friends

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.FragmentRecyclerViewBinding
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.RecyclerViewAdapter
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
class FriendsFragment : Fragment() {

    private lateinit var groupsViewModel: GroupsViewModel
    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

    private var users: MutableList<User> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = activity;

        if (activity != null) {
            friendsViewModel = ViewModelProviders.of(activity, ViewModelFactory(activity.application as App)).get(FriendsViewModel::class.java)
            groupsViewModel = ViewModelProviders.of(activity, ViewModelFactory(activity.application as App)).get(GroupsViewModel::class.java)
        }

        friendsViewModel.getFriendsInGroup(groupsViewModel.currentGroupId).subscribe {
            users.clear()
            users.addAll(it)
            binding.recyclerView.adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)

        // Add data to listview
        val adapter = RecyclerViewAdapter(users, R.layout.item_friend, {})
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}
