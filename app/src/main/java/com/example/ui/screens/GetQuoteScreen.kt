package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.InsurancePlan
import com.example.data.InsuranceType
import com.example.ui.FincallScreen
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

@Composable
fun GetQuoteScreen(
    viewModel: FincallViewModel,
    uiState: FincallUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Main Hub Headline
            Column {
                Text(
                    "FIND THE BEST POLICY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BluePrimary,
                    letterSpacing = 1.sp
                )
                Text(
                    "Find the Best Policy Suited for Your Needs",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NavyPrimary
                )
                Text(
                    "Compare 100% unbiased quotes from top IRDAI insurers in under 60 seconds.",
                    fontSize = 13.sp,
                    color = SlateLight,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // Dedicated Quote Form Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Step 1: Select Insurance Type
                    Text(
                        "1. Select Insurance Type",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            InsuranceType.HEALTH to "Health",
                            InsuranceType.VEHICLE to "Vehicle",
                            InsuranceType.LIFE to "Life",
                            InsuranceType.BUSINESS to "Business"
                        ).forEach { (type, label) ->
                            val isSelected = uiState.selectedType == type
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) BluePrimary else SlateBackground,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { viewModel.setSelectedInsuranceType(type) }
                                    .testTag("quote_type_${type.name.lowercase()}")
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = when (type) {
                                            InsuranceType.HEALTH -> Icons.Default.HealthAndSafety
                                            InsuranceType.VEHICLE -> Icons.Default.DirectionsCar
                                            InsuranceType.LIFE -> Icons.Default.Security
                                            InsuranceType.BUSINESS -> Icons.Default.Business
                                        },
                                        contentDescription = null,
                                        tint = if (isSelected) Color.White else SlateMedium,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        label,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else NavyPrimary
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Step 2: Mobile Number & Pincode
                    Text(
                        "2. Mobile Number & Pincode",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.mobileNumber,
                        onValueChange = { viewModel.updateMobile(it) },
                        label = { Text("Mobile Number (for instant quote link)") },
                        placeholder = { Text("Enter 10-digit mobile") },
                        leadingIcon = {
                            Icon(Icons.Default.Phone, contentDescription = null, tint = SlateLight)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_mobile_input"),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = uiState.pincode,
                        onValueChange = { viewModel.updatePincode(it) },
                        label = { Text("Residential Pincode") },
                        placeholder = { Text("e.g. 110001 or 560001") },
                        leadingIcon = {
                            Icon(Icons.Default.PinDrop, contentDescription = null, tint = SlateLight)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quote_pincode_input"),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true
                    )

                    if (uiState.pincode.length >= 2) {
                        Row(
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                "Coverage Area: ${uiState.cityDetected}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = EmeraldGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Step 3: Compare Plans Now Button
                    Button(
                        onClick = { viewModel.searchQuotes() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("compare_plans_now_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Compare Plans Now", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }

                    // Special Link to Dedicated Health Calculator Page
                    if (uiState.selectedType == InsuranceType.HEALTH) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = SlateBackground,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.setScreen(FincallScreen.HEALTH_CALC) }
                                .testTag("goto_health_calculator_banner")
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        "Want detailed family & age-based calculation?",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NavyPrimary
                                    )
                                    Text(
                                        "Open dedicated Health Quote & Section 80D Tax Calculator →",
                                        fontSize = 11.sp,
                                        color = BluePrimary
                                    )
                                }
                                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = BluePrimary, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }

        // Section Title: Matching Policies
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Recommended Plans (${uiState.quotesList.size})",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = EmeraldGreen.copy(alpha = 0.12f)
                ) {
                    Text(
                        "IRDAI Verified",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                }
            }
        }

        // List of Insurance Plans
        items(uiState.quotesList) { plan ->
            PlanComparisonCard(
                plan = plan,
                onTalkAdvisor = { viewModel.toggleAdvisorCallback(true) },
                onSelectPlan = {
                    viewModel.showToast("Selected ${plan.planName}. An advisor will assist with cashless documentation.")
                    viewModel.toggleAdvisorCallback(true)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun PlanComparisonCard(
    plan: InsurancePlan,
    onTalkAdvisor: () -> Unit,
    onSelectPlan: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header: Provider & Popular Tag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    plan.provider,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = BluePrimary
                )
                plan.popularTag?.let { tag ->
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = AmberAccent
                    ) {
                        Text(
                            tag,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
            }

            Text(
                plan.planName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrimary,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Pricing & Cover Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Cover Amount", fontSize = 11.sp, color = SlateLight)
                    Text(plan.coverAmount, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = NavyPrimary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Starting From", fontSize = 11.sp, color = SlateLight)
                    Text(
                        "₹${plan.annualPremium}/yr",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = EmeraldGreen
                    )
                    Text("or ₹${plan.monthlyEmi}/mo EMI", fontSize = 10.sp, color = SlateLight)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Badges Row (Hospitals, Copay, Waiting)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = SlateBackground,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(6.dp)) {
                        Text("Network", fontSize = 9.sp, color = SlateLight)
                        Text(plan.cashlessHospitalsCount, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SlateDark)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = SlateBackground,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(6.dp)) {
                        Text("Co-Payment", fontSize = 9.sp, color = SlateLight)
                        Text(plan.copayPercentage, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SlateDark)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = SlateBackground,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(6.dp)) {
                        Text("Waiting Period", fontSize = 9.sp, color = SlateLight)
                        Text(plan.waitingPeriodYears, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SlateDark)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Key bullet points
            plan.keyFeatures.forEach { feature ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EmeraldGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(feature, fontSize = 11.sp, color = SlateMedium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onTalkAdvisor,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.SupportAgent, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Free Advice", fontSize = 12.sp)
                }

                Button(
                    onClick = onSelectPlan,
                    modifier = Modifier.weight(1.2f),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Apply Now", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
