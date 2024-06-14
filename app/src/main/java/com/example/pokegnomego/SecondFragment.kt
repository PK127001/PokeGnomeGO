package com.example.pokegnomego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pokegnomego.com.example.pokegnomego.api.RetrofitInstance
import com.example.pokegnomego.com.example.pokegnomego.api.User
import com.example.pokegnomego.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val buttonShake: Button = binding.buttonSecond
        val resultTextView: TextView = binding.resultTextView

        // Set an OnClickListener on the button
        buttonShake.setOnClickListener {
            loadData(resultTextView)
        }
        return binding.root

    }

    private fun loadData(resultTextView: TextView) {
        RetrofitInstance.api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val userText = users.joinToString(separator = "\n") { "${it.login}" } // ZMIENIĆ NA KOORDYNATY LUN NAZWE KRASNALA
                    resultTextView.text = userText
                } else {
                    Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed to load data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}