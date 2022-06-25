package com.solomon.topheadlinesnewsapp.ui.screen.articles

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.solomon.topheadlinesnewsapp.R
import com.solomon.topheadlinesnewsapp.model.Article
import com.solomon.topheadlinesnewsapp.model.toModel
import com.solomon.topheadlinesnewsapp.ui.component.Toolbar
import com.solomon.topheadlinesnewsapp.ui.component.ErrorView
import com.solomon.topheadlinesnewsapp.ui.component.LoadingView
import com.solomon.topheadlinesnewsapp.ui.component.ToolbarIcon

import com.solomon.topheadlinesnewsapp.ui.screen.articles.ArticlesListIntent.*
import com.solomon.topheadlinesnewsapp.ui.screen.articles.ArticlesListState.*
import com.solomon.topheadlinesnewsapp.ui.screen.articles.view.ArticlesList
import com.solomon.topheadlinesnewsapp.ui.screen.details.ArticleDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesListActivity : AppCompatActivity() {

    private val viewModel: ArticlesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //postIntent(Fetch)

        setContent {
            MaterialTheme {
                //val state = viewModel.state.observeAsState()

                Column {
                    Toolbar()
                    RenderBody {
                        ArticleDetailsActivity.newIntent(this@ArticlesListActivity, it).apply {
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderBody(article:(Article) -> Unit
        //state: ArticlesListState?
    ) {
        val viewModel by org.koin.androidx.compose.viewModel<ArticlesListViewModel>()
        val loading by viewModel.loading.collectAsState()
        val data by viewModel.data.collectAsState()

        LaunchedEffect(key1 = Unit){
            viewModel.getNews()
        }

        when {
            loading -> LoadingView()
            data.first.isNotEmpty() -> ArticlesList(data.first.toModel()) {
                article(it)
            }
            !data.second.isNullOrEmpty() -> ErrorView(data.second!!) {
                //postIntent(Retry)
            }
        }

//        when (state) {
//            is Loading -> LoadingView()
//            is Success -> ArticlesList(state.articles) { postIntent(ShowDetails(it)) }
//            is Error -> ErrorView(state.errorMessage) { postIntent(Retry) }
//        }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.app_name),
            rightIcon = ToolbarIcon(
                icon = Icons.Default.Refresh,
                onClick = {
                    //postIntent(Refresh)
                }
            )
        )
    }

    private fun postIntent(intent: ArticlesListIntent) {
        //viewModel.processIntent(intent)
    }
}