package com.pseedk.nononotes.screens.note

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pseedk.nononotes.R
import com.pseedk.nononotes.databinding.FragmentMainBinding
import com.pseedk.nononotes.databinding.FragmentNoteBinding
import com.pseedk.nononotes.models.AppNote
import com.pseedk.nononotes.screens.main.MainAdapter
import com.pseedk.nononotes.screens.main.MainFragmentViewModel
import com.pseedk.nononotes.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        mBinding.apply {
            noteText.text = mCurrentNote.text
            noteName.text = mCurrentNote.name
        }

        mViewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_action_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when (menuItem.itemId) {
                    R.id.btn_delete -> {
                        mViewModel.delete(mCurrentNote) {
                            APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                        }
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}