package com.masshookpakeko.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.masshookpakeko.dogs.R
import com.masshookpakeko.dogs.model.ResponseDog
import com.masshookpakeko.dogs.utils.getProgressDrawable
import com.masshookpakeko.dogs.utils.loadImage
import com.masshookpakeko.dogs.view.ListFragmentDirections
import kotlinx.android.synthetic.main.item_dog_list.view.*

class DogListAdapter(val items: ArrayList<ResponseDog>) : RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    fun updateDogList(newItems: List<ResponseDog>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dog_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: ResponseDog) {
            itemView.img_list_dog.loadImage(data.url, getProgressDrawable(itemView.context))
            itemView.tv_name_list_dog.text = data.name
            itemView.tv_lifespan_dog_list.text = data.lifeSpan
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
            }
        }
    }
}