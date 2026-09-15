package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.InsuranceType
import com.example.data.SampleData
import com.example.ui.FincallScreen
import com.example.ui.FincallUiState
import com.example.ui.FincallViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.CardBorder
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateDark
import com.example.ui.theme.SlateLight
import com.example.ui.theme.SlateMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: FincallViewModel,
    uiState: FincallUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        // 1. HERO SECTION
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(NavyPrimary, Color(0xFF0F3460))
                    )
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column {
                // Trust Badges Pill
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White.copy(alpha = 0.15f),
                    border = CardDefaults.outlinedCardBorder(),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Verified,
                            contentDescription = null,
                            tint = EmeraldGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "IRDAI Registered Marketing Firm",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Headline
                Text(
                    text = "Protect What Matters Most with Fincall – India’s Trusted Insurance Experts",
                    fontSize = 24.sp,
                    lineHeight = 31.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Sub-headline
                Text(
                    text = "Get tailored Health, Vehicle, and Life insurance advice in under 60 seconds.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFCBD5E1)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Hero Image
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_insurance),
                        contentDescription = "Fincall Family Protection",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Action Points: Get Instant Quote | Talk to an Advisor
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.setScreen(FincallScreen.QUOTE)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("hero_get_instant_quote_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            "Get Instant Quote",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            viewModel.toggleAdvisorCallback(true)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("hero_talk_advisor_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White.copy(alpha = 0.12f),
                            contentColor = Color.White
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.horizontalGradient(listOf(Color.White, Color.LightGray))
                        )
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "Talk to Advisor",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Trust Badges String
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf(
                        "24/7 Claim Assistance" to "⚡",
                        "50,000+ Happy Clients" to "🤝",
                        "Zero Hidden Costs" to "💎"
                    ).forEach { (badge, emoji) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(emoji, fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                badge,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFE2E8F0)
                            )
                        }
                    }
                }
            }
        }

        // QUICK CATEGORY LAUNCHPAD
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                "Tailored Insurance Products",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Health Card
                CategoryCard(
                    title = "Health Cover",
                    subtitle = "10k+ Hospitals",
                    badge = "80D Tax Relief",
                    badgeColor = EmeraldGreen,
                    icon = Icons.Default.HealthAndSafety,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.setSelectedInsuranceType(InsuranceType.HEALTH)
                        viewModel.setScreen(FincallScreen.HEALTH_CALC)
                    }
                )

                // Vehicle Card
                CategoryCard(
                    title = "Motor & Car",
                    subtitle = "Up to 50% NCB",
                    badge = "Zero Dep",
                    badgeColor = BluePrimary,
                    icon = Icons.Default.TwoWheeler,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.setSelectedInsuranceType(InsuranceType.VEHICLE)
                        viewModel.setScreen(FincallScreen.QUOTE)
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Life Card
                CategoryCard(
                    title = "Term Life",
                    subtitle = "1 Cr @ ₹490/mo",
                    badge = "Tax Free",
                    badgeColor = NavyPrimary,
                    icon = Icons.Default.Security,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.setSelectedInsuranceType(InsuranceType.LIFE)
                        viewModel.setScreen(FincallScreen.QUOTE)
                    }
                )

                // Challan Pay Special Driver
                CategoryCard(
                    title = "Challan Pay",
                    subtitle = "Clear Traffic Fines",
                    badge = "Save 50% Motor",
                    badgeColor = AmberAccent,
                    icon = Icons.Default.CreditCard,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.setScreen(FincallScreen.CHALLAN)
                    }
                )
            }
        }

        // 5. TRAFFIC CHALLAN & MOTOR RENEWAL LEAD DRIVER BANNER
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .clickable { viewModel.setScreen(FincallScreen.CHALLAN) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(AmberAccent.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🚦", fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Pay & Clear Traffic Challans Instantly",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = NavyPrimary
                    )
                    Text(
                        "Check pending fines online in seconds. Plus unlock up to 50% discount on motor insurance renewal!",
                        fontSize = 12.sp,
                        color = SlateMedium,
                        lineHeight = 16.sp
                    )
                }
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = AmberAccent,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // 6. WHY CHOOSE FINCALL SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "WHY CHOOSE FINCALL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = BluePrimary,
                letterSpacing = 1.sp
            )
            Text(
                "Why Thousands Trust Fincall for Their Insurance Needs",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = NavyPrimary,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            // Point 1: Unbiased Advisory
            WhyFincallItem(
                emoji = "⚖️",
                title = "Unbiased Advisory",
                description = "We offer comparison across Star Health, HDFC ERGO, ICICI Lombard, Tata AIG & Care. We work for you, not the insurance companies."
            )

            // Point 2: Zero Hidden Costs
            WhyFincallItem(
                emoji = "🏷️",
                title = "Zero Hidden Costs",
                description = "Transparent pricing with maximum policy benefits. What you see is what you pay—no surprise deductions or undisclosed exclusions."
            )

            // Point 3: Dedicated Claims Desk
            WhyFincallItem(
                emoji = "🏥",
                title = "Dedicated Claims Desk",
                description = "We handle the hospital paperwork and surveyor coordination for you during emergencies. Guaranteed 2-hour email response."
            )
        }

        // LEAD MAGNET SECTION: "The Ultimate Guide to Selecting Family Health Cover"
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = NavyPrimary
            )
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "FREE CONSUMER GUIDE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "The Ultimate Guide to Selecting Family Health Cover",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "Avoid catastrophic out-of-pocket costs. Learn how to navigate room rent limits, pre-existing waiting periods, and Section 80D tax deductions.",
                    fontSize = 12.sp,
                    color = Color(0xFFCBD5E1),
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                Button(
                    onClick = { viewModel.toggleLeadMagnet(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_lead_magnet_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Read & Download Free Guide", fontWeight = FontWeight.Bold)
                }
            }
        }

        // CUSTOMER TESTIMONIALS SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "REAL CLIENT EXPERIENCES",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BluePrimary,
                    letterSpacing = 1.sp
                )
                Text(
                    "Real Client Reviews & Settled Claim Stories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(SampleData.sampleTestimonials) { item ->
                    Card(
                        modifier = Modifier
                            .width(280.dp)
                            .padding(start = if (item.id == "t1") 16.dp else 0.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(item.rating) {
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = null,
                                        tint = AmberAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    item.policyType,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = BluePrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "\"${item.review}\"",
                                fontSize = 12.sp,
                                color = SlateMedium,
                                lineHeight = 17.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(item.clientName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NavyPrimary)
                                    Text(item.location, fontSize = 10.sp, color = SlateLight)
                                }
                                item.claimSettledAmount?.let {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = EmeraldGreen.copy(alpha = 0.12f)
                                    ) {
                                        Text(
                                            it,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = EmeraldGreen
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ABOUT US FOOTER SUMMARY
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { viewModel.toggleAboutUsModal(true) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SlateBackground),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = NavyPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Fincall Insurance Marketing Consultants Pvt Ltd",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Your Reliable Partner in Financial & Insurance Security. Dedicated to transparent, IRDAI-compliant insurance solutions for every Indian family.",
                    fontSize = 12.sp,
                    color = SlateLight
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Tap to learn more about our IRDAI compliance & values →",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BluePrimary
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    subtitle: String,
    badge: String,
    badgeColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(badgeColor.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = badgeColor, modifier = Modifier.size(20.dp))
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = badgeColor.copy(alpha = 0.12f)
                ) {
                    Text(
                        badge,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = badgeColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NavyPrimary)
            Text(subtitle, fontSize = 11.sp, color = SlateLight)
        }
    }
}

@Composable
fun WhyFincallItem(emoji: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(SlateBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NavyPrimary)
            Spacer(modifier = Modifier.height(2.dp))
            Text(description, fontSize = 12.sp, color = SlateMedium, lineHeight = 17.sp)
        }
    }
}
