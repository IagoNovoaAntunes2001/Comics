package com.example.comics.ui.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comics.R
import com.example.comics.databinding.FragmentComicsBinding
import com.example.comics.domain.entity.ItemVO
import com.example.comics.ui.comics.adapter.ComicsAdapter
import com.example.comics.ui.comics.viewmodel.ComicsViewModel
import com.example.comics.util.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsFragment : Fragment(R.layout.fragment_comics) {

    private var _binding: FragmentComicsBinding? = null
    private val binding: FragmentComicsBinding get() = _binding!!

    private val viewModel: ComicsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()
        swipeList()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.comicResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> showSuccessView(it.data)
                is Result.Error -> showError()
            }
        }
    }

    private fun swipeList() = with(binding.swipeRefreshComics) {
        setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        with(binding) {
            swipeRefreshComics.isRefreshing = true
            viewModel.getComics()
        }
    }

    private fun showSuccessView(list: List<ItemVO>) {
        with(binding) {
            errorTVComics.visibility = View.GONE
            listItemComics.visibility = View.VISIBLE
            listItemComics.adapter = ComicsAdapter(list)
            listItemComics.layoutManager = LinearLayoutManager(requireContext())
            swipeRefreshComics.isRefreshing = false
        }
    }

    private fun showError() {
        with(binding) {
            listItemComics.visibility = View.GONE
            errorTVComics.visibility = View.VISIBLE
            swipeRefreshComics.isRefreshing = false
        }
    }
}
