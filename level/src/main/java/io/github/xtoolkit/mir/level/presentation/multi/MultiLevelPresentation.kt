package io.github.xtoolkit.mir.level.presentation.multi

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import io.github.xtoolkit.mir.level.core.domain.Level
import io.github.xtoolkit.mir.level.presentation.mirza.LevelMirzaPresentation
import io.github.xtoolkit.mir.level.presentation.single.SingleLevelPresentation
import io.github.xtoolkit.mir.util.presentation.layout.DefaultLayout
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun GetLevels(
    levels: List<Pair<Level, List<Pair<Int, Boolean>>>>,
    onSelectPlayground: (Int) -> Unit
) = DefaultLayout(modifier = Modifier.padding(vertical = 10.dp)) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = levels.size,
        contentPadding = PaddingValues(end = 30.dp, start = 30.dp)
    ) { page ->
        val item = levels[page]
        SingleLevelPresentation(item.first, item.second) { onSelectPlayground(it) }
    }
}

@Composable
fun MultiLevelPresentation(viewModel: MultiLevelViewModel = getViewModel()) {
    SideEffect { viewModel.start() }

    var playgroundId by rememberSaveable { mutableStateOf(0) }

    BackHandler(enabled = playgroundId > 0) {
        viewModel.requestSyncLevel()
        playgroundId = 0
    }

    if (playgroundId > 0) {
        if (viewModel.user != null) LevelMirzaPresentation(playgroundId, viewModel.user!!) {
            viewModel.requestSyncLevel()
            playgroundId = 0
        } else Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) { CircularProgressIndicator() }
    } else GetLevels(viewModel.levels) { playgroundId = it }
}