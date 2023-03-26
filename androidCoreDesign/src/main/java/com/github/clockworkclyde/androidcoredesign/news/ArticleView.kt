package com.github.clockworkclyde.androidcoredesign.news

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.clockworkclyde.androidcore.utils.inflateBindingLayout
import com.github.clockworkclyde.androidcore.utils.loadRoundedImage
import com.github.clockworkclyde.androidcoredesign.R
import com.github.clockworkclyde.androidcoredesign.databinding.LayoutArticleViewBinding

class ArticleView @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

   private val binding = LayoutInflater.from(context)
      .inflateBindingLayout<LayoutArticleViewBinding>(
         R.layout.layout_article_view, this@ArticleView, true
      )

   var title: CharSequence? = null
      get() = binding.articleTitleTV.text
      set(value) {
         field = value
         binding.articleTitleTV.text = value
      }

   var content: CharSequence? = null
      get() = binding.articleContentTV.text
      set(value) {
         field = value
         binding.articleContentTV.text = value
      }

   var createdAt: CharSequence? = null
      get() = binding.articleCreatedAtTV.text
      set(value) {
         field = value
         binding.articleCreatedAtTV.text = value
      }

   val image: ImageView
      get() = binding.articleIV

   private var articleImageRadius =
      context.resources.getDimensionPixelOffset(R.dimen.radius_image_article)

   fun setUpView(article: String) {
      title = ""
      content = ""
      createdAt = ""
      setRoundedArticleImage("", articleImageRadius)
   }

   fun setRoundedArticleImage(url: String, cornerRadius: Int = 0) {
      Glide.with(binding.root)
         .loadRoundedImage(url, binding.articleIV, cornerRadius)
   }

}