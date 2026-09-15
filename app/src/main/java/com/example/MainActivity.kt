package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.FincallScreen
import com.example.ui.FincallViewModel
import com.example.ui.components.AboutUsDialog
import com.example.ui.components.AdvisorCallbackDialog
import com.example.ui.components.FloatingWhatsAppWidget
import com.example.ui.components.LeadMagnetDialog
import com.example.ui.components.WhatsAppQuickChatDialog
import com.example.ui.screens.AiAgentScreen
import com.example.ui.screens.ChallanPayScreen
import com.example.ui.screens.ClaimSupportScreen
import com.example.ui.screens.GetQuoteScreen
import com.example.ui.screens.HealthQuoteSection
import com.example.ui.screens.HomeScreen
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateLight
import com.example.ui.theme.SlateMedium

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                FincallApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FincallApp(
    viewModel: FincallViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.dismissSnackbar()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { viewModel.setScreen(FincallScreen.HOME) }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(BluePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Security,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "FINCALL",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = EmeraldGreen.copy(alpha = 0.25f)
                                ) {
                                    Text(
                                        "IRDAI",
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldGreen
                                    )
                                }
                            }
                            Text(
                                "Insurance Marketing Consultants",
                                fontSize = 10.sp,
                                color = Color(0xFFCBD5E1)
                            )
                        }
                    }
                },
                actions = {
                    // Call Advisor Quick Button
                    IconButton(
                        onClick = { viewModel.toggleAdvisorCallback(true) },
                        modifier = Modifier.testTag("topbar_advisor_call_button")
                    ) {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Talk to Advisor",
                            tint = Color.White
                        )
                    }

                    // About Us Dialog
                    IconButton(
                        onClick = { viewModel.toggleAboutUsModal(true) },
                        modifier = Modifier.testTag("topbar_about_us_button")
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "About Fincall",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyDark,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.HOME,
                    onClick = { viewModel.setScreen(FincallScreen.HOME) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BluePrimary,
                        selectedTextColor = BluePrimary,
                        indicatorColor = BluePrimary.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.testTag("nav_tab_home")
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Description, contentDescription = "Get Quote") },
                    label = { Text("Quote", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.QUOTE,
                    onClick = { viewModel.setScreen(FincallScreen.QUOTE) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BluePrimary,
                        selectedTextColor = BluePrimary,
                        indicatorColor = BluePrimary.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.testTag("nav_tab_quote")
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.HealthAndSafety, contentDescription = "Health Quote") },
                    label = { Text("Health", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.HEALTH_CALC,
                    onClick = { viewModel.setScreen(FincallScreen.HEALTH_CALC) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = EmeraldGreen,
                        selectedTextColor = EmeraldGreen,
                        indicatorColor = EmeraldGreen.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.testTag("nav_tab_health")
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.CreditCard, contentDescription = "Challan Pay") },
                    label = { Text("Challan", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.CHALLAN,
                    onClick = { viewModel.setScreen(FincallScreen.CHALLAN) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AmberAccent,
                        selectedTextColor = AmberAccent,
                        indicatorColor = AmberAccent.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_challan")
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.SupportAgent, contentDescription = "Claims 24/7") },
                    label = { Text("Claims", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.CLAIMS,
                    onClick = { viewModel.setScreen(FincallScreen.CLAIMS) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BluePrimary,
                        selectedTextColor = BluePrimary,
                        indicatorColor = BluePrimary.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.testTag("nav_tab_claims")
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Fincall AI") },
                    label = { Text("AI Agent", fontSize = 9.sp, fontWeight = FontWeight.SemiBold) },
                    selected = uiState.currentScreen == FincallScreen.AI_ASSISTANT,
                    onClick = { viewModel.setScreen(FincallScreen.AI_ASSISTANT) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BluePrimary,
                        selectedTextColor = BluePrimary,
                        indicatorColor = BluePrimary.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier.testTag("nav_tab_ai")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SlateBackground)
        ) {
            // Main Screen Switcher
            when (uiState.currentScreen) {
                FincallScreen.HOME -> HomeScreen(viewModel = viewModel, uiState = uiState)
                FincallScreen.QUOTE -> GetQuoteScreen(viewModel = viewModel, uiState = uiState)
                FincallScreen.HEALTH_CALC -> HealthQuoteSection(viewModel = viewModel, uiState = uiState)
                FincallScreen.CHALLAN -> ChallanPayScreen(viewModel = viewModel, uiState = uiState)
                FincallScreen.CLAIMS -> ClaimSupportScreen(viewModel = viewModel, uiState = uiState)
                FincallScreen.AI_ASSISTANT -> AiAgentScreen(viewModel = viewModel, uiState = uiState)
            }

            // Customer Retention Feature 1: Floating WhatsApp Quick Chat Widget (hidden on chat screen to prevent input field overlap)
            if (uiState.currentScreen != FincallScreen.AI_ASSISTANT) {
                FloatingWhatsAppWidget(
                    onOpenWhatsApp = { viewModel.toggleWhatsAppModal(true) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }

    // Customer Retention Dialogs & Modals
    if (uiState.showWhatsAppModal) {
        WhatsAppQuickChatDialog(
            onDismiss = { viewModel.toggleWhatsAppModal(false) },
            onSendMessage = { prompt ->
                viewModel.sendAiPrompt(prompt)
            }
        )
    }

    if (uiState.showLeadMagnetModal) {
        LeadMagnetDialog(
            onDismiss = { viewModel.toggleLeadMagnet(false) },
            onDownloaded = {
                viewModel.showToast("Free Health Insurance Guide unlocked!")
            }
        )
    }

    if (uiState.showAdvisorCallbackModal) {
        AdvisorCallbackDialog(
            onDismiss = { viewModel.toggleAdvisorCallback(false) },
            onSubmit = { name, phone, type ->
                viewModel.showToast("Callback requested for $name ($phone). An advisor is connecting!")
            }
        )
    }

    if (uiState.showAboutUsModal) {
        AboutUsDialog(
            onDismiss = { viewModel.toggleAboutUsModal(false) }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Fincall Insurance: $name", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme { Greeting("Trusted IRDAI Advisor") }
}
