package com.example.assign4_pbm5_hangman

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign4_pbm5_hangman.databinding.AlphabetListBinding
import com.example.assign4_pbm5_hangman.databinding.FragmentAlphabetListBinding

class CrimeHolder(
    private val binding: AlphabetListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(alphabet: String, alphabetListFragment: AlphabetListFragment, onData : OnDataPass) {
        binding.alphabetButton.text = alphabet
        binding.root.setOnClickListener{
            if (binding.alphabetButton.text != "#") {
                binding.alphabetButton.text = "#"
                binding.root.isFocusable = false

                alphabetListFragment.rem(alphabet)
                onData.onDataPass(alphabet)
            }
        }
    }
}

class AlphabetListAdapter(
    private var alphabets: MutableList<String>,
    private val alphabetListFragment: AlphabetListFragment,
    private val onData : OnDataPass,
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
        if (onData.shouldRemHalfOrNot() && alphabetListFragment.giveCrimes() < 14) {
            for (g in alphabets) {
                if (!(g[0] in onData.getAnswer()) && mutableListOf<Boolean>(true, false).random()) {
                    alphabetListFragment.rem(g)
                }
                if (alphabetListFragment.giveCrimes() > 13) break
            }
            if ((alphabetListFragment.giveCrimes() > 13))
                onData.remhalfSuccess()
        }
    }

    override fun getItemCount() = alphabets.size

}
