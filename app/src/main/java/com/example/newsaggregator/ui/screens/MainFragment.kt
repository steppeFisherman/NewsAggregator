package com.example.newsaggregator.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.FragmentMainBinding
import com.example.newsaggregator.ui.adapters.MainFragmentAdapter
import com.example.newsaggregator.utils.LoadImage
import com.example.newsaggregator.utils.SearchViewListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var menuProvider: MenuProvider
    private lateinit var mAdapter: MainFragmentAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = checkNotNull(_binding) { "FragmentMainBinding is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.fragmentMainToolbar)

        adapterSetUp()
        menuProviderSetUp()

        viewModel.newsList.observe(viewLifecycleOwner) { listDataUi ->
            mAdapter.submitList(listDataUi)
        }

        viewModel.listFiltered.observe(viewLifecycleOwner) { listDataUi ->
            mAdapter.submitList(listDataUi)
        }
    }

    private fun adapterSetUp() {
        mAdapter = MainFragmentAdapter(LoadImage.Base()) {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment(it.title)
            )
        }
        binding.rvFragmentMain.adapter = mAdapter
    }

    private fun menuProviderSetUp() {
        menuProvider = object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_main_toolbar_menu, menu)
                val searchItem = menu.findItem(R.id.fragmentMain_search)
                val searchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(SearchViewListener { newText ->
                    viewModel.searchInList(newText)
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = false
        }

        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().removeMenuProvider(menuProvider)
        _binding = null
    }
}