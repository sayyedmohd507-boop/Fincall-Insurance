package com.example.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FincallUiState
import com.example.ui.FincallViewModel
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateDark
import com.example.ui.theme.SlateLight
import com.example.ui.theme.SlateMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HealthQuoteSection(
    viewModel: FincallViewModel,
    uiState: FincallUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 4. HEALTH QUOTE (Dedicated Page) Headline
        Column {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = EmeraldGreen.copy(alpha = 0.12f),
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.HealthAndSafety, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        "DEDICATED HEALTH HUB",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                }
            }

            Text(
                "Comprehensive Health Insurance for You & Your Family",
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold,
                color = NavyPrimary,
                lineHeight = 28.sp
            )

            Text(
                "Protect your savings from escalating medical costs. 100% cashless pre-authorization with Fincall's on-ground claims desk.",
                fontSize = 13.sp,
                color = SlateMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Key Pitch Points Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HealthFeaturePill(
                title = "10k+ Cashless Hospitals",
                subtitle = "Direct pre-auth in 30 mins",
                icon = Icons.Default.LocalHospital,
                color = BluePrimary,
                modifier = Modifier.weight(1f)
            )
            HealthFeaturePill(
                title = "Zero-Copay Options",
                subtitle = "100% claim paid by insurer",
                icon = Icons.Default.Percent,
                color = EmeraldGreen,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HealthFeaturePill(
                title = "Critical Illness Cover",
                subtitle = "Cancer, stroke & 32+ conditions",
                icon = Icons.Default.Favorite,
                color = AmberAccent,
                modifier = Modifier.weight(1f)
            )
            HealthFeaturePill(
                title = "Section 80D Tax Relief",
                subtitle = "Save up to ₹75,000/year",
                icon = Icons.Default.ReceiptLong,
                color = NavyPrimary,
                modifier = Modifier.weight(1f)
            )
        }

        // INTERACTIVE PREMIUM CALCULATOR CARD
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Calculate, contentDescription = null, tint = BluePrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Interactive Health Premium Calculator",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                }

                // Micro-copy prompt as strictly specified by user!
                Text(
                    "Calculate premium based on your age and family size.",
                    fontSize = 12.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = BluePrimary,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                Divider()

                // Parameter 1: Age Selector
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Eldest Member's Age:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateDark
                    )
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SlateBackground
                    ) {
                        Text(
                            "${uiState.healthAge} Years",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = BluePrimary
                        )
                    }
                }

                Slider(
                    value = uiState.healthAge.toFloat(),
                    onValueChange = { viewModel.updateHealthAge(it.toInt()) },
                    valueRange = 18f..75f,
                    steps = 57,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("health_age_slider")
                )

                // Parameter 2: Family Size
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Family Members to Cover:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateDark
                )
                Spacer(modifier = Modifier.height(6.dp))

                val families = listOf(
                    "Self",
                    "Couple",
                    "Self + Spouse + 1 Child",
                    "Family (2+ Children)",
                    "Parents (Senior)"
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    families.forEach { fam ->
                        val isSel = uiState.healthFamily == fam
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSel) BluePrimary else SlateBackground,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { viewModel.updateHealthFamily(fam) }
                                .testTag("family_size_${fam.take(5).lowercase()}")
                        ) {
                            Text(
                                fam,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                fontSize = 11.sp,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSel) Color.White else SlateMedium
                            )
                        }
                    }
                }

                // Parameter 3: Sum Insured
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    "Sum Insured Cover:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateDark
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf(5 to "₹5L", 10 to "₹10L", 25 to "₹25L", 50 to "₹50L", 100 to "₹1 Cr").forEach { (amount, label) ->
                        val isSel = uiState.healthSumInsured == amount
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSel) NavyPrimary else SlateBackground,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.updateHealthSumInsured(amount) }
                                .testTag("sum_insured_$amount")
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) Color.White else SlateDark
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Calculator Live Calculation Output Card
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SlateBackground,
                    border = CardDefaults.outlinedCardBorder(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Estimated Premium", fontSize = 12.sp, color = SlateLight)
                                Text(
                                    "₹${uiState.healthEstimatedAnnual}/year",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = EmeraldGreen
                                )
                                Text(
                                    "~ ₹${(uiState.healthEstimatedAnnual / 12)}/month EMI",
                                    fontSize = 11.sp,
                                    color = SlateMedium
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = EmeraldGreen.copy(alpha = 0.14f)
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text("Tax Savings", fontSize = 10.sp, color = EmeraldGreen, fontWeight = FontWeight.Bold)
                                    Text("Up to ₹${uiState.healthTaxSavings}", fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = EmeraldGreen)
                                    Text("u/s 80D", fontSize = 9.sp, color = SlateLight)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { viewModel.toggleAdvisorCallback(true) },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("health_calc_advisor_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.SupportAgent, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Get Exact Quote")
                            }

                            OutlinedButton(
                                onClick = { viewModel.toggleLeadMagnet(true) },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("health_calc_guide_button"),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Read Family Guide")
                            }
                        }
                    }
                }
            }
        }

        // Cashless Hospital Partners Summary
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Premier Cashless Hospitals in Fincall Network",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = NavyPrimary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Apollo Hospitals • Fortis Healthcare • Max Super Speciality • Manipal Hospitals • Medanta The Medicity • Narayana Health",
                    fontSize = 12.sp,
                    color = SlateMedium,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "✓ Instant TPA pre-authorization handled by Fincall Desk with zero co-pay options.",
                    fontSize = 11.sp,
                    color = EmeraldGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun HealthFeaturePill(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = NavyPrimary)
            Text(subtitle, fontSize = 10.sp, color = SlateLight, lineHeight = 14.sp)
        }
    }
}
