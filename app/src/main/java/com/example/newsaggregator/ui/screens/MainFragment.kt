package com.example.newsaggregator.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.FragmentMainBinding
import com.example.newsaggregator.ui.adapters.MainFragmentAdapter
import com.example.newsaggregator.ui.model.DataUi
import com.example.newsaggregator.utils.LoadImage
import com.example.newsaggregator.utils.SearchService
import com.example.newsaggregator.utils.SearchViewListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mAdapter: MainFragmentAdapter
    private val vm by activityViewModels<MainViewModel>()
    private var mList = listOf<DataUi>()
    private val searchService = SearchService.Base()

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

        adapterSetUp()
        menuProviderSetUp()

        vm.newsList.observe(viewLifecycleOwner) { listDataUi ->
            mList = listDataUi
            mAdapter.submitList(listDataUi)
        }

        searchService.mListFilteredLiveData.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    private fun adapterSetUp() {
        mAdapter = MainFragmentAdapter(LoadImage.Base()) {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("from MainFragment")
            )
        }
        binding.rvFragmentMain.adapter = mAdapter
    }

    private fun menuProviderSetUp() {
        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_main_toolbar_menu, menu)
            }

            override fun onPrepareMenu(menu: Menu) {
                val menuItem = menu.findItem(R.id.fragmentMain_search)
                val searchView = menuItem.actionView as SearchView
                searchView.setOnQueryTextListener(SearchViewListener { newText ->
                    searchService.result(mList, newText)
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}