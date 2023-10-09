package com.example.assign4_pbm5_hangman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign4_pbm5_hangman.databinding.AlphabetListBinding

class CrimeHolder(
    private val binding: AlphabetListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(alphabet: String, alphabetListFragment: AlphabetListFragment, onData : OnDataPass) {
        binding.alphabetButton.text = alphabet
        binding.root.setOnClickListener{
            binding.alphabetButton.text = "#"
            binding.root.isFocusable = false

            alphabetListFragment.rem(alphabet)
            onData.onDataPass(alphabet)
        }
    }
}

class AlphabetListAdapter(
    private var alphabets: MutableList<String>,
    private val alphabetListFragment: AlphabetListFragment,
    private val onData : OnDataPass
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlphabetListBinding.inflate(inflater, parent, false)
        return (CrimeHolder(binding))
    }
    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val alphabet = alphabets[(position) % 26]
        holder.bind(alphabet, alphabetListFragment, onData)
    }

    override fun getItemCount() = alphabets.size

}
