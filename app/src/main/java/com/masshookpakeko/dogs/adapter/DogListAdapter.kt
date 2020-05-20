package com.masshookpakeko.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.masshookpakeko.dogs.R
import com.masshookpakeko.dogs.databinding.ItemDogListBinding
import com.masshookpakeko.dogs.model.ResponseDog
import com.masshookpakeko.dogs.utils.ClickListener
import com.masshookpakeko.dogs.view.ListFragmentDirections
import kotlinx.android.synthetic.main.item_dog_list.view.*

class DogListAdapter(val items: ArrayList<ResponseDog>) : RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    fun updateDogList(newItems: List<ResponseDog>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_dog_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    class ViewHolder(var view: ItemDogListBinding) : RecyclerView.ViewHolder(view.root),
        ClickListener {

        override fun onClicked(v: View) {
            val uuid = v.dogId.text.toString().toInt()
            val actions = ListFragmentDirections.actionDetailFragment()
            actions.dogUuid = uuid
            Navigation.findNavController(v).navigate(actions)
        }

        fun bindData(data: ResponseDog) {
            view.dog = data
            view.listener = this
        }
    }
}