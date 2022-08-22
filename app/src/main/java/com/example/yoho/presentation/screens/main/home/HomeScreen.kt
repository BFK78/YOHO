package com.example.yoho.presentation.screens.main.home

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yoho.presentation.navigation.BottomNavigationBar
import com.example.yoho.presentation.screens.common.SuccessDialog
import kotlinx.coroutines.delay
import com.example.yoho.R
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.launch


data class MeetingModel(
    val time: String,
    val date: String,
    val meetingId: String,
    val meetingTitle: String,
    val status: String
)

val list = mutableListOf(
    MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),MeetingModel(
        time = "14:00",
        date = "Today,Dec 24 2022",
        meetingId = "176 1831 4896",
        meetingTitle = "UI/UX Design Training",
        status = "completed"
    ),
)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {
    val openDialog = remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    val modalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    //Success Dialog closing
    LaunchedEffect(key1 = true) {

        delay(5000)
        openDialog.value = false
    }

    val search = remember {
        mutableStateOf("")
    }

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetState = modalBottomSheetState,
    ) {
        Scaffold(
            backgroundColor = Color.White,
            bottomBar = {
                BottomNavigationBar(navHostController = navHostController)
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                SuccessDialog(openDialog = openDialog)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    HomeTopBar(
                        greetingText = "Good Morning",
                        name = "Mohammed Basim"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        value = search.value,
                        onValueChange = {
                            search.value = it
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            backgroundColor = Color.Gray.copy(alpha = 0.1f)
                        ),
                        label = {
                            Text(
                                text = "Search",
                                color = Color.Gray
                            )
                        },
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Search icon",
                                    tint = Color.Gray
                                )
                            }
                        },
                        shape = RoundedCornerShape(15.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Card(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = MaterialTheme.colors.primary)
                                    .clickable {
                                        scope.launch {
                                            modalBottomSheetState.show()
                                        }
                                    },
                                backgroundColor = MaterialTheme.colors.primary,
                                elevation = 4.dp,
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ThumbUp,
                                        contentDescription = "new meeting icon",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(32.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "New Meeting",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(100.dp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }

                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Card(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = CircleShape)
                                    .clickable {

                                    },
                                backgroundColor = Color(0xFFFEA72D),
                                elevation = 4.dp,
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.AddCircle,
                                        contentDescription = "new meeting icon",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(32.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Join",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(100.dp),
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )


                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Card(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = MaterialTheme.colors.primary)
                                    .clickable {
                                       navHostController.navigate(Screens.ScheduleMeetingScreen.route)
                                    },
                                backgroundColor = Color(0xFFFF798D),
                                elevation = 4.dp,
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.DateRange,
                                        contentDescription = "Schedule icon",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(32.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Schedule",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        color = Color.Black.copy(
                            alpha = 0.1f
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Meeting History",
                            fontWeight = FontWeight.Bold
                        )

                        ClickableText(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("See All")
                            }
                        }) {

                        }
                    }

                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        color = Color.Black.copy(
                            alpha = 0.1f
                        )
                    )
                    LazyColumn() {
                        items(list) {
                            SingleMeetingHistory(model = it)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeTopBar(
    greetingText: String,
    name: String,
) {

    val image = rememberImagePainter(data = null) {
        crossfade(600)
        error(R.drawable.ic_profile)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Black)
            ) {
//                Image(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    painter = image,
//                    contentDescription = "Profile image"
//                )
            }

            Spacer(modifier =
            Modifier.width(8.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$greetingText \uD83D\uDC4B",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notification icon"
            )
        }
    }

}

@Composable
fun SingleMeetingHistory(
    model: MeetingModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = model.time,
                fontSize = 14.sp
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = model.date,
                    fontSize = 14.sp
                )
                Text(
                    text = model.meetingTitle,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Meeting ID : ${model.meetingId}",
                    fontSize = 14.sp
                )
            }
            Text(
                text = model.status,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp),
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier,
            color = Color.Black.copy(
                alpha = 0.1f
            )
        )
    }
}

@Composable
fun BottomSheetContent() {

    val offVideo = remember {
        mutableStateOf(true)
    }

    val offAudio = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(5.dp)
                .width(30.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.Gray.copy(alpha = 0.5f))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Start New Meeting",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray.copy(alpha = 0.1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Turn OFF My Video",
                fontWeight = FontWeight.SemiBold
            )

            RadioButton(
                selected = offVideo.value,
                onClick = { offVideo.value = !offVideo.value },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.primary,
                    unselectedColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Turn OFF My Audio",
                fontWeight = FontWeight.SemiBold
            )

            RadioButton(
                selected = offAudio.value,
                onClick = { offAudio.value = !offAudio.value },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.primary,
                    unselectedColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray.copy(alpha = 0.1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(25.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray.copy(alpha = .1f),
                    contentColor = MaterialTheme.colors.primary
                ),
                contentPadding = PaddingValues(vertical = 16.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Text(text = "Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(25.dp)),
                contentPadding = PaddingValues(vertical = 16.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Text(text = "Start a meeting")
            }
        }
    }

}