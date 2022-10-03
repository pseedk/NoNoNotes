package com.pseedk.nononotes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pseedk.nononotes.R
import com.pseedk.nononotes.databinding.FragmentStartBinding
import com.pseedk.nononotes.utilits.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()

        mViewModel = ViewModelProvider(this)[StartFragmentViewModel::class.java]

        if (AppPreference.getInitUser()) {
            mViewModel.initDatabase(AppPreference.getTypeDB()) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            initialization()
        }
    }

    private fun initialization() {
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM) {
                AppPreference.setInitUser(true)
                AppPreference.setTypeDB(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        mBinding.apply {
            btnFirebase.setOnClickListener {
                inputEmail.visibility = View.VISIBLE
                inputPassword.visibility = View.VISIBLE
                btnLogin.visibility = View.VISIBLE
                btnLogin.setOnClickListener {
                    val inputEmail = inputEmail.text.toString()
                    val inputPassword = inputPassword.text.toString()
                    if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                        EMAIL = inputEmail
                        PASSWORD = inputPassword
                        mViewModel.initDatabase(TYPE_FIREBASE) {
                            AppPreference.setInitUser(true)
                            AppPreference.setTypeDB(TYPE_FIREBASE)
                            APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                        }
                    } else {
                        showSnackbar(getString(R.string.snackbar_wrong_enter), VIEW_FOR_SNACKBAR)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}