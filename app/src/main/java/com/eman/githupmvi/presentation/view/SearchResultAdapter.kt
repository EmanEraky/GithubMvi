package com.eman.githupmvi.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eman.githupmvi.R
import com.eman.githupmvi.models.GithubOwner
import com.squareup.picasso.Picasso

class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var repoName = view.findViewById(R.id.repoName) as TextView
    var ownerName = view.findViewById(R.id.ownerName) as TextView
    var avatar = view.findViewById(R.id.avatar) as ImageView

}

class SearchResultAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

    var items: List<GithubOwner>? = null

    override fun onBindViewHolder(vh: SearchResultViewHolder, pos: Int) {
        val repo = items!![pos]
        vh.repoName.text = repo.name
        Picasso.get().load(repo.avatar).placeholder(
            R.color.colorAccent
        ).fit().into(vh.avatar)
    }


    override fun getItemCount(): Int = if (items == null) 0 else items!!.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = layoutInflater.inflate(R.layout.item_result, parent, false)
        return SearchResultViewHolder(view)
    }
}