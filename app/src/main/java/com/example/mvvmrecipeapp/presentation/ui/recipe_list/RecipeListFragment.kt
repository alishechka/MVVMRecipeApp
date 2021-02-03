package com.example.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmrecipeapp.presentation.BaseApplication
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication
    val viewModel: RecipeListViewModel by activityViewModels()

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
//                val isShowing = remember { mutableStateOf(false) }
                val snackbarHostState = remember { SnackbarHostState() }

                Column {
                    Button(
                        onClick = {
                            lifecycleScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Hey look a snackbar",
                                    actionLabel = "Hide",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    ) {
                        Text("Show snackbar")
                    }
                    DecoupledSnackbar(snackbarHostState = snackbarHostState)
                }

//                    SimpleSnackBarDemo(
//                        onHideSnackbar = { isShowing.value = false },
//                        isShowing = isShowing.value
//                    )

            }

//                AppTheme(darkTheme = application.isDark.value) {
//                    val recipes = viewModel.recipes.value
//                    val query = viewModel.query.value
//                    val selectedCategory = viewModel.selectedCategory.value
//                    val loading = viewModel.loading.value
//                    Scaffold(
//                        topBar = {
//                            SearchAppBar(
//                                query = query,
//                                onQueryChanged = viewModel::onQueryChanged,
//                                onExecuteSearch = viewModel::newSearch,
//                                scrollPosition = viewModel.categoryScrollPosition,
//                                selectedCategory = selectedCategory,
//                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
//                                onChangedCategoryScrollPosition = viewModel::onChangedCategoryScrollPosition,
//                                onToggleTheme = {
//                                    application.toggleTheme()
//                                }
//                            )
//                        },
//                        bottomBar = { MuBottomBar() },
//                        drawerContent = { MyDrawer() }
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .background(color = MaterialTheme.colors.background)
//                        ) {
//                            if (loading) {
//                                LoadingRecipeListShimmer(imageHeight = 250.dp)
//                            } else {
//                                LazyColumn {
//                                    itemsIndexed(
//                                        items = recipes
//                                    ) { index, recipe ->
//                                        RecipeCard(
//                                            recipe = recipe,
//                                            onClick = { }
//                                        )
//                                    }
//                                }
//                                CircularIndeterminateProgressBar(isDisplayed = loading)
//                            }
//                        }
//                    }
//
//                }

        }
    }
}


@Composable
fun MuBottomBar() {
    BottomNavigation(elevation = 12.dp) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.BrokenImage) },
            selected = false,
            onClick = { })
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AccountBalanceWallet) },
            selected = true,
            onClick = { })
    }
}

@Composable
fun MyDrawer() {
    Column {
        Text(text = "Item1")
        Text(text = "Item2")
        Text(text = "Item3")
        Text(text = "Item4")
        Text(text = "Item5")
    }
}

@ExperimentalMaterialApi
@Composable
fun DecoupledSnackbar(
    snackbarHostState: SnackbarHostState
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val snackbar = createRef()
        SnackbarHost(
            modifier = Modifier.constrainAs(snackbar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = { snackbarHostState.currentSnackbarData?.dismiss() }
                        ) {
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel ?: "Hide",
                                style = TextStyle(color = Color.White)
                            )
                        }

                    }
                ) {
                    Text(
                        text = snackbarHostState.currentSnackbarData?.message
                            ?: "Hey look a SnackBar"
                    )

                }
            }
        )
    }

}

@Composable
fun SimpleSnackBarDemo(
    isShowing: Boolean,
    onHideSnackbar: () -> Unit
) {
    if (isShowing) {

    }
}






