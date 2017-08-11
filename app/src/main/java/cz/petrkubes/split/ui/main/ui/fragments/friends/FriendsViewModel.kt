package cz.petrkubes.split.ui.main.ui.fragments.friends

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.model.data.Friend

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class FriendsViewModel : ViewModel() {

    var friends: MutableLiveData<MutableList<Friend>> = MutableLiveData()

    fun loadData() {
        friends.value = mutableListOf()

        for (i in 0..9) {
            friends.value?.add(Friend("asdf"))
        }
    }

}

