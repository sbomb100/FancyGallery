package edu.vt.cs5254.fancygallery

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.imageLoader
import edu.vt.cs5254.fancygallery.databinding.FragmentGalleryBinding
import kotlinx.coroutines.launch


private const val TAG = "PhotoGalleryFragment"
class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding){ "FGalleryBinding is null"}

    private val vm: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_gallery, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.reload_menu) {
                    reloadCache()
                    return true
                }
                return false
            }

        }, viewLifecycleOwner)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.galleryItems.collect { items ->
                    binding.photoGrid.adapter = GalleryItemAdapter(items) { photoPageUri ->
                        findNavController().navigate(
                            GalleryFragmentDirections.showPhoto(photoPageUri)
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun reloadCache(){
        requireContext().imageLoader.memoryCache?.clear()
        vm.reloadGalleryItems()
    }
}