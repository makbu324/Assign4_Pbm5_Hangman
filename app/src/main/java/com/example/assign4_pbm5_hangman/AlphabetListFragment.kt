package com.example.assign4_pbm5_hangman

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assign4_pbm5_hangman.databinding.FragmentAlphabetListBinding

//notifies which element to remove
private const val TAG = "CrimeListFragment"

class AlphabetListFragment : Fragment() {

    private var _binding: FragmentAlphabetListBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val crimeListViewModel: AlphabetListViewModel by viewModels()

    lateinit var dataPasser: OnDataPass

    lateinit var fragTrans: FragmentTransaction

    private var remCount = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(data: String){
        dataPasser.onDataPass(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.a_list.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlphabetListBinding.inflate(inflater, container, false)

        binding.alphabetRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val crimes = crimeListViewModel.a_list

        val adapter = AlphabetListAdapter(crimes, this, dataPasser)
        binding.alphabetRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun rem(s: String) {
        remCount++
        crimeListViewModel.a_list.set(crimeListViewModel.a_list.indexOf(s), "#")
    }

    fun giveCrimes(): Int {
        return remCount
    }


}