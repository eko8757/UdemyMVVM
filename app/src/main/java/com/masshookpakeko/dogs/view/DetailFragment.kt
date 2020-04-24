package com.masshookpakeko.dogs.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.masshookpakeko.dogs.R
import com.masshookpakeko.dogs.utils.getProgressDrawable
import com.masshookpakeko.dogs.utils.loadImage
import com.masshookpakeko.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.detailViewModel.observe(this, Observer { dog ->
            dog?.let {
                tv_dog_name_detail.text = dog.name
                tv_dog_purpose_detail.text = dog.bredFor
                tv_dog_temperament_detail.text = dog.temperament
                tv_dog_lifespan_detail.text = dog.lifeSpan
                context?.let { img_dog_detail.loadImage(dog.url, getProgressDrawable(it)) }
            }
        })
    }

}
