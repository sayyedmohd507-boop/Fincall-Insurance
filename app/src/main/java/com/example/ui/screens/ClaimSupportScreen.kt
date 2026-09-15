package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.ClaimTicket
import com.example.ui.FincallUiState
import com.example.ui.FincallViewModel
import com.example.ui.components.launchWhatsAppIntent
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateDark
import com.example.ui.theme.SlateLight
import com.example.ui.theme.SlateMedium
import com.example.ui.theme.WhatsAppGreen

@Composable
fun ClaimSupportScreen(
    viewModel: FincallViewModel,
    uiState: FincallUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showNewClaimDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // 7. 24/7 CLAIM SUPPORT & MAIL FOCUS SUPPORT Headline
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
                        Icon(Icons.Default.SupportAgent, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "24/7 ON-GROUND CLAIMS DESK",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldGreen
                        )
                    }
                }

                Text(
                    "Fast, Stress-Free Claim Settlement When You Need It Most",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NavyPrimary,
                    lineHeight = 27.sp
                )

                Text(
                    "Never battle with TPAs or insurance adjusters alone. Fincall's specialized desk coordinates hospital approvals and vehicle surveys from start to finish.",
                    fontSize = 13.sp,
                    color = SlateLight,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // EMERGENCY HOTLINE BUTTONS: Call / WhatsApp Support Button (Active 24/7)
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = NavyPrimary),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(EmeraldGreen)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "24/7 EMERGENCY HOTLINE ACTIVE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Hospitalization or Accident Right Now?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "Tap below for immediate cashless desk priority dispatch or WhatsApp coordinator.",
                        fontSize = 12.sp,
                        color = Color(0xFFCBD5E1),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:18002008899"))
                                try {
                                    context.startActivity(dialIntent)
                                } catch (e: Exception) {}
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("claim_hotline_call_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call 24/7 Toll-Free", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = {
                                launchWhatsAppIntent(context, "URGENT CLAIM ASSISTANCE: I need help with hospital pre-authorization / motor accident claim.")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("claim_hotline_whatsapp_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.SupportAgent, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("WhatsApp Desk", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // MAIL SUPPORT FEATURE CARD: Dedicated email ticketing system (claims@fincall.in) with guaranteed response within 2 hours
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(BluePrimary.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Email, contentDescription = null, tint = BluePrimary, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    "Dedicated Mail Ticketing Desk",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = NavyPrimary
                                )
                                Text(
                                    "claims@fincall.in",
                                    fontSize = 12.sp,
                                    color = BluePrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = EmeraldGreen.copy(alpha = 0.12f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Timer, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "< 2 Hr Response",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldGreen
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "Every claim sent to claims@fincall.in receives an official ticket number with guaranteed case-officer assignment within 2 hours.",
                        fontSize = 12.sp,
                        color = SlateMedium,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { showNewClaimDialog = true },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("file_new_claim_ticket_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Create Claim Ticket", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:claims@fincall.in")).apply {
                                    putExtra(Intent.EXTRA_SUBJECT, "Urgent Insurance Claim Assistance Request")
                                }
                                try {
                                    context.startActivity(emailIntent)
                                } catch (e: Exception) {}
                            },
                            modifier = Modifier.weight(0.9f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Send Email", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // PROCESS TRACKER: Simple 3-step visualization (Report Claim → Document Verification → Settlement)
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "3-Step Seamless Claim Process",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = NavyPrimary
                    )
                    Text(
                        "How Fincall settles your claims faster than standard insurer timelines:",
                        fontSize = 12.sp,
                        color = SlateLight,
                        modifier = Modifier.padding(top = 2.dp, bottom = 14.dp)
                    )

                    // Step 1
                    ProcessStepItem(
                        stepNumber = "1",
                        title = "Report Claim",
                        subtitle = "Notify via 24/7 hotline, WhatsApp, or create a ticket with hospital name or incident details.",
                        isCompleted = true,
                        isCurrent = false
                    )

                    // Step 2
                    ProcessStepItem(
                        stepNumber = "2",
                        title = "Document Verification",
                        subtitle = "Fincall desk audits medical discharge summaries, bills & police FIR directly with the hospital or garage.",
                        isCompleted = true,
                        isCurrent = true
                    )

                    // Step 3
                    ProcessStepItem(
                        stepNumber = "3",
                        title = "Settlement",
                        subtitle = "100% cashless pre-authorization or direct NEFT transfer into your bank account within 3 days.",
                        isCompleted = false,
                        isCurrent = false
                    )
                }
            }
        }

        // LIVE TICKET STATUS SEARCH & TRACKER
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = SlateBackground),
                border = CardDefaults.outlinedCardBorder(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Track Active Claim Ticket",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = uiState.ticketQuery,
                            onValueChange = { viewModel.updateTicketQuery(it) },
                            placeholder = { Text("Enter Ticket ID (e.g. FIN-CLM-9041)") },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("claim_ticket_search_input"),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                viewModel.trackTicket(uiState.ticketQuery.ifBlank { "FIN-CLM-9041" })
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("track_ticket_button")
                        ) {
                            Text("Track")
                        }
                    }

                    // Display tracked ticket
                    val ticket = uiState.trackedTicket ?: uiState.claimTickets.firstOrNull()
                    ticket?.let { t ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                            border = CardDefaults.outlinedCardBorder(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        t.ticketId,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = BluePrimary
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = AmberAccent.copy(alpha = 0.14f)
                                    ) {
                                        Text(
                                            t.status,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AmberAccent
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "${t.claimType} • ${t.insurerName}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = NavyPrimary
                                )
                                Text(
                                    "Location: ${t.hospitalOrLocation} • Reported: ${t.dateReported}",
                                    fontSize = 11.sp,
                                    color = SlateLight
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Speed, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Assigned Officer: Priya M. (Fincall Claims Lead)",
                                        fontSize = 11.sp,
                                        color = EmeraldGreen,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }

    // Modal to create new claim ticket
    if (showNewClaimDialog) {
        CreateClaimTicketDialog(
            onDismiss = { showNewClaimDialog = false },
            onSubmit = { pol, ins, name, phone, type, hosp ->
                viewModel.submitNewClaim(pol, ins, name, phone, type, hosp)
                showNewClaimDialog = false
            }
        )
    }
}

@Composable
fun ProcessStepItem(
    stepNumber: String,
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    isCurrent: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isCurrent -> AmberAccent
                        isCompleted -> EmeraldGreen
                        else -> SlateLight.copy(alpha = 0.3f)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stepNumber,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = NavyPrimary
                )
                if (isCurrent) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = AmberAccent.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "IN PROGRESS",
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = AmberAccent
                        )
                    }
                }
            }
            Text(
                subtitle,
                fontSize = 11.sp,
                color = SlateMedium,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun CreateClaimTicketDialog(
    onDismiss: () -> Unit,
    onSubmit: (pol: String, ins: String, name: String, phone: String, type: String, hosp: String) -> Unit
) {
    var policyNo by remember { mutableStateOf("") }
    var insurerName by remember { mutableStateOf("HDFC ERGO") }
    var claimantName by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var claimType by remember { mutableStateOf("Emergency Hospitalization") }
    var hospital by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.MarkEmailRead, contentDescription = null, tint = BluePrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("New Claim Ticket", fontWeight = FontWeight.Bold, color = NavyPrimary)
            }
        },
        text = {
            Column {
                Text(
                    "Submitted tickets are instantly dispatched to claims@fincall.in with automated 2-hour response SLA.",
                    fontSize = 12.sp,
                    color = SlateMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = policyNo,
                    onValueChange = { policyNo = it },
                    label = { Text("Policy Number") },
                    placeholder = { Text("e.g. STAR-HLT-9921") },
                    modifier = Modifier.fillMaxWidth().testTag("claim_dialog_policy_input")
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = claimantName,
                    onValueChange = { claimantName = it },
                    label = { Text("Patient / Vehicle Owner Name") },
                    modifier = Modifier.fillMaxWidth().testTag("claim_dialog_name_input")
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = mobile,
                    onValueChange = { if (it.length <= 10 && it.all { c -> c.isDigit() }) mobile = it },
                    label = { Text("Contact Mobile Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth().testTag("claim_dialog_mobile_input")
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = hospital,
                    onValueChange = { hospital = it },
                    label = { Text("Hospital Name / Garage Location") },
                    placeholder = { Text("e.g. Apollo Hospital / Max Saket") },
                    modifier = Modifier.fillMaxWidth().testTag("claim_dialog_hosp_input")
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSubmit(policyNo, insurerName, claimantName, mobile, claimType, hospital)
                },
                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                modifier = Modifier.testTag("claim_dialog_submit_button")
            ) {
                Text("Generate Ticket")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
