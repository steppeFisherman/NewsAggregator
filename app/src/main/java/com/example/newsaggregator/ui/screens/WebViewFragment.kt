package com.example.newsaggregator.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsaggregator.databinding.FragmentMainBinding
import com.example.newsaggregator.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private val args by navArgs<WebViewFragmentArgs>()

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding
        get() = checkNotNull(_binding){"FragmentWebViewBinding is null"}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.test.text = args.url

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}