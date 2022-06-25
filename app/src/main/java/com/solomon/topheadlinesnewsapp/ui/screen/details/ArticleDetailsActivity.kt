package com.solomon.topheadlinesnewsapp.ui.screen.details

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.solomon.topheadlinesnewsapp.R
import com.solomon.topheadlinesnewsapp.model.Article
import com.solomon.topheadlinesnewsapp.ui.component.LoadingView
import com.solomon.topheadlinesnewsapp.ui.component.Toolbar
import com.solomon.topheadlinesnewsapp.ui.component.ToolbarIcon
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsIntent.*
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsState.*
import com.solomon.topheadlinesnewsapp.ui.screen.details.view.ArticleDetailsView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailsActivity : AppCompatActivity() {

    //private val viewModel: ArticleDetailsViewModel by viewModel()
    //private val viewModel: ArticleDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //postIntent(Show(extractArticle()))
        val article = extractArticle()

        setContent {
            //val state = viewModel.state.observeAsState()

            Column {
                Toolbar()
                RenderBody(article) {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK
                        data = Uri.parse(it)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    @Composable
    private fun RenderBody(state: Article, newsUrl: (String) -> Unit) {
        ArticleDetailsView(state) { newsUrl(it) }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.article_details),
            navigationIcon = ToolbarIcon(Icons.Default.ArrowBack) { onBackPressed() }
        )
    }

//    private fun postIntent(intent: ArticleDetailsIntent) {
//        viewModel.processIntent(intent)
//    }

    private fun extractArticle(): Article {
        return intent.extras?.getParcelable(EXTRA_ARTICLE)
            ?: throw IllegalStateException("Extra article can't be null")
    }

    companion object {
        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        fun newIntent(context: Context, article: Article) =
            Intent(context, ArticleDetailsActivity::class.java).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
                putExtra(EXTRA_ARTICLE, article)
            }
    }
}