package com.masshookpakeko.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masshookpakeko.dogs.R
import com.masshookpakeko.dogs.adapter.DogListAdapter
import com.masshookpakeko.dogs.utils.gone
import com.masshookpakeko.dogs.utils.visible
import com.masshookpakeko.dogs.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_dog_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogAdapter
        }

        swipeRefresh_list.setOnRefreshListener {
            rv_dog_list.gone()
            tv_error_load_data.gone()
            progress_dog_list.visible()

            viewModel.refresh()
            swipeRefresh_list.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.dogs.observe(this, Observer { dogs ->
            dogs?.let {
                rv_dog_list.visible()
                dogAdapter.updateDogList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(this, Observer { error ->
            error?.let {
                tv_error_load_data.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                progress_dog_list.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    tv_error_load_data.gone()
                    rv_dog_list.gone()
                }
            }
        })
    }
}
