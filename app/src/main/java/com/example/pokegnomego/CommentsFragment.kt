package com.example.pokegnomego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.pokegnomego.com.example.pokegnomego.api.RetrofitInstanceCom
import com.example.pokegnomego.com.example.pokegnomego.api.ApiService
import com.example.pokegnomego.com.example.pokegnomego.api.Comment
import com.example.pokegnomego.databinding.FragmentCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private var gnomeId: Int = 0
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gnomeId = arguments?.getInt("image_id") ?: 0
        loadComments(gnomeId)

        binding.CommentButton.setOnClickListener {
            val commentText = binding.commentEditText.text.toString()
            if (commentText.isNotBlank()) {
                addComment(commentText)
                binding.commentEditText.text.clear()
            }
        }
    }

    private fun loadComments(gnomeId: Int) {
        RetrofitInstanceCom.api.getComments(gnomeId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body()
                    comments?.let { displayComments(it) }
                } else {
                    Toast.makeText(requireContext(), "Nie udało się załadować forum", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(requireContext(), "Nie udało się załadować forum", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayComments(comments: List<Comment>) {
        for (comment in comments) {
            val commentTextView = TextView(requireContext()).apply {
                text = comment.commentText
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }
            binding.commentsContainer.addView(commentTextView)
        }
    }

    private fun addComment(comment: String) {
        val commentTextView = TextView(requireContext()).apply {
            text = comment
            textSize = 16f
            setPadding(0, 8, 0, 8)
        }
        binding.commentsContainer.addView(commentTextView)
        loadComments(gnomeId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}