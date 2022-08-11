package com.seventh.calculator.ui.page

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seventh.calculator.data.KeyBean
import com.seventh.calculator.data.KeyType

@Composable
fun CalculatorPage(
    viewModel: CalculatorViewModel = viewModel()
) {
    val viewStates = viewModel.viewStates

    val portraitkeyList:List<KeyBean> = listOf(
        KeyBean("7", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("8", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("9", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("÷", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("4", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("5", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("6", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("×", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("1", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("2", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("3", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("-", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean(".", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("0", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("+", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("＝", KeyType.METHOD, Color(0xFF007DFD), Color(0xFFFFFFFF))
    )

    val landscpeKeyList:List<KeyBean> = listOf(
        KeyBean("1", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("2", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("3", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("4", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("5", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean(".", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("+", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("-", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("6", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("7", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("8", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("9", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("0", KeyType.NUMBER, Color(0xFFF2F2F2)),
        KeyBean("×", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("÷", KeyType.METHOD, Color(0xFFE5F3FE), Color(0xFF027EFA)),
        KeyBean("＝", KeyType.METHOD, Color(0xFF007DFD), Color(0xFFFFFFFF))
    )

    var keyList: List<KeyBean>
    var gridCells = 4
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        gridCells = 8
        keyList = landscpeKeyList
    } else {
        gridCells = 4
        keyList = portraitkeyList
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(5f)) {
            DisplayTextView(viewModel)
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(5f)) {
            KeyboardView(gridCells, keyList, viewModel)
        }
    }
}

@Composable
fun DisplayTextView(
    viewModel: CalculatorViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = viewModel.viewStates.content,
            fontSize = 30.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color(0xFF027EFA),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFE5F3FE))
                .clickable { viewModel.dispatch(CalculatorViewAction.CleanContent) }
        )
    }
}

@Composable
fun KeyboardView(
    gridCells: Int,
    data: List<KeyBean>,
    viewModel: CalculatorViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        val state = rememberLazyGridState()
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridCells),
            state = state
        ){
            itemsIndexed(data) { index, item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(item.backColor)
                        .clickable {
                            Log.e("点击", item.title)
                            viewModel.dispatch(CalculatorViewAction.UpdateContent(item))
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.title,
                        color = item.textColor,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CalculatorTheme {
//        DisplayTextView()
//    }
//}