package com.github.clockworkclyde.newsapp.android.presentation.details

import android.annotation.SuppressLint
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.github.clockworkclyde.androidcore.presentation.fragments.BaseDataBindingFragment
import com.github.clockworkclyde.androidcore.presentation.viewmodels.BaseFlowViewModel
import com.github.clockworkclyde.androidcore.utils.loadRoundedImage
import com.github.clockworkclyde.androidcore.utils.secondsToDate
import com.github.clockworkclyde.newsapp.android.R
import com.github.clockworkclyde.newsapp.android.databinding.FragmentDetailsBinding

class DetailsFragment : BaseDataBindingFragment<FragmentDetailsBinding, BaseFlowViewModel>() {

   override val layoutResId: Int = R.layout.fragment_details

   private val detailsArgs by navArgs<DetailsFragmentArgs>()

   override fun initViews() {
      setUpToolbarView()
      setUpDetailsView()
   }

   private fun setUpToolbarView() {
      binding.toolbar.setNavigationOnClickListener { navController.popBackStack() }
   }

   @SuppressLint("SetTextI18n")
   private fun setUpDetailsView() {
      val radius =
         requireContext().resources.getDimensionPixelOffset(com.github.clockworkclyde.androidcoredesign.R.dimen.radius_image_article)
      val article = detailsArgs.article
      with(binding) {
         articleTitleTV.text = article.name
         articleContentTV.text =
            article.description + article.description + article.description + article.description + article.description
         Glide.with(root).loadRoundedImage(article.image.url, articleIV, radius)
         articleCreatedAtTV.text = article.createdAt.secondsToDate(requireContext())
      }
   }
}