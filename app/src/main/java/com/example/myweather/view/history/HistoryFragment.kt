package com.example.myweather.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.databinding.FragmentHistoryBinding
import com.example.myweather.view.main.HistoryFragmentAdapter
import com.example.myweather.viewmodel.AppState
import com.example.myweather.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }
    private val adapter: HistoryFragmentAdapter by lazy { HistoryFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyFragmentRecyclerview.adapter = adapter
        viewModel.liveDataToObserver.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAll()
    }

    private fun renderData(data: AppState) {
        when (data) {

            is AppState.Error -> {

            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.historyFragmentRecyclerview.visibility = View.VISIBLE
                adapter.setWeather(data.weatherData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}