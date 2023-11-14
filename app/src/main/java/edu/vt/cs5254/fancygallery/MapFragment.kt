package edu.vt.cs5254.fancygallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.vt.cs5254.fancygallery.databinding.FragmentGalleryBinding
import edu.vt.cs5254.fancygallery.databinding.FragmentMapBinding

class MapFragment: Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding
        get() = checkNotNull(_binding){ "FMapBinding is null"}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}