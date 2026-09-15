package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.InsuranceType
import com.example.data.TrafficChallan
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
fun ChallanPayScreen(
    viewModel: FincallViewModel,
    uiState: FincallUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // 5. CHALLAN PAY (Traffic & Lead Driver) Headline & Subheadline
            Column {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = AmberAccent.copy(alpha = 0.12f),
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🚦", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "TRAFFIC FINES & MOTOR RENEWAL DESK",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AmberAccent
                        )
                    }
                }

                Text(
                    "Pay & Clear Traffic Challans Instantly",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NavyPrimary
                )

                Text(
                    "Check pending vehicle fines online and pay securely in seconds.",
                    fontSize = 13.sp,
                    color = SlateLight,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // Vehicle Lookup Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Enter Vehicle Registration Number:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.vehicleNumber,
                        onValueChange = { viewModel.updateVehicleNumber(it) },
                        label = { Text("Vehicle Reg No. (e.g. DL 01 AB 1234)") },
                        placeholder = { Text("DL 01 AB 1234 / MH 02 CD 5678") },
                        leadingIcon = {
                            Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = SlateLight)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("challan_vehicle_input"),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf("DL 01 AB 1234", "MH 02 CD 5678", "KA 03 EF 9012").forEach { samplePlate ->
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SlateBackground,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .clickable {
                                        viewModel.updateVehicleNumber(samplePlate)
                                        viewModel.checkPendingChallans()
                                    }
                            ) {
                                Text(
                                    samplePlate,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 10.sp,
                                    color = SlateMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { viewModel.checkPendingChallans() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("check_challans_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Check Pending Challans", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // 5. CRUCIAL VALUE ADD PROMPT BANNER (As specified by the user prompt!)
        // "Once users enter their vehicle registration number to check fines, show a quick prompt:
        // 'Your Motor Insurance expires soon! Get up to 50% discount on renewal today.'"
        if (uiState.showMotorDiscountBanner) {
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFEF3C7) // warm amber gold
                    ),
                    border = BorderStrokeModifier(Color(0xFFF59E0B)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("motor_renewal_discount_banner")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(AmberAccent),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.LocalOffer,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "HIGH VALUE RENEWAL BENEFIT",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF92400E)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Literal user-requested copy:
                        Text(
                            "\"Your Motor Insurance expires soon! Get up to 50% discount on renewal today.\"",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF78350F),
                            lineHeight = 20.sp
                        )

                        Text(
                            "Transfer your full No Claim Bonus (NCB) from Tata AIG, ICICI Lombard, or HDFC ERGO. Zero depreciation & 24/7 roadside assistance included.",
                            fontSize = 12.sp,
                            color = Color(0xFF92400E),
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    viewModel.setSelectedInsuranceType(InsuranceType.VEHICLE)
                                    viewModel.setScreen(FincallScreen.QUOTE)
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("motor_discount_compare_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Compare Renewal Quotes", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = { viewModel.toggleAdvisorCallback(true) },
                                modifier = Modifier
                                    .weight(0.9f)
                                    .testTag("motor_discount_advisor_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Claim 50% NCB", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Challan Results Header
        if (uiState.hasSearchedChallan) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Found Challans for ${uiState.vehicleNumber}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                    Text(
                        "${uiState.challans.size} Records",
                        fontSize = 12.sp,
                        color = SlateLight
                    )
                }
            }

            items(uiState.challans) { challan ->
                val isPaid = uiState.paidChallans.contains(challan.challanNo)
                ChallanItemCard(
                    challan = challan,
                    isPaid = isPaid,
                    onPay = { viewModel.payChallan(challan.challanNo) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun BorderStrokeModifier(color: Color): androidx.compose.foundation.BorderStroke {
    return androidx.compose.foundation.BorderStroke(1.dp, color)
}

@Composable
fun ChallanItemCard(
    challan: TrafficChallan,
    isPaid: Boolean,
    onPay: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Challan #${challan.challanNo}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = NavyPrimary
                )
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = if (isPaid) EmeraldGreen.copy(alpha = 0.12f) else Color(0xFFFEE2E2)
                ) {
                    Text(
                        if (isPaid) "CLEARED & PAID" else "PENDING FINE",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isPaid) EmeraldGreen else Color(0xFFDC2626)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                challan.offense,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = SlateDark
            )

            Text(
                "Date: ${challan.offenseDate} • Location: ${challan.location}",
                fontSize = 11.sp,
                color = SlateLight,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Fine Payable", fontSize = 10.sp, color = SlateLight)
                    Text("₹${challan.fineAmount}", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = if (isPaid) EmeraldGreen else NavyPrimary)
                }

                if (!isPaid) {
                    Button(
                        onClick = onPay,
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("pay_challan_${challan.challanNo}")
                    ) {
                        Icon(Icons.Default.Payment, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Pay Online Instantly", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Cleared on Portal", fontSize = 12.sp, color = EmeraldGreen, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
