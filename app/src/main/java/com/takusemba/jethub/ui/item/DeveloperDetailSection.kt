package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class DeveloperDetailSection(
  lifecycleOwner: LifecycleOwner,
  searchReposViewModel: DeveloperDetailViewModel
) : Section() {

  init {
    searchReposViewModel.developer.observe(lifecycleOwner, Observer { developer ->
      setHeader(DeveloperDetailHeaderItem(developer))
    })

    searchReposViewModel.developerRepos.observe(lifecycleOwner, Observer { repos ->
      updateResult(repos)
    })
  }

  private fun updateResult(repos: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repos).mapTo(items) { repository -> RepositoryItem(repository) }
    update(items)
  }
}