package com.example.ui.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.SampleData
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.BluePrimary
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateLight
import com.example.ui.theme.SlateMedium
import com.example.ui.theme.WhatsAppGreen

@Composable
fun FloatingWhatsAppWidget(
    onOpenWhatsApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onOpenWhatsApp,
        containerColor = WhatsAppGreen,
        contentColor = Color.White,
        shape = CircleShape,
        modifier = modifier
            .testTag("floating_whatsapp_button")
            .size(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "WhatsApp Quick Chat",
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
fun WhatsAppQuickChatDialog(
    onDismiss: () -> Unit,
    onSendMessage: (String) -> Unit
) {
    val context = LocalContext.current
    var customQuery by remember { mutableStateOf("") }
    val quickOptions = listOf(
        "I need a quote for Family Health Cover",
        "How do I clear my traffic challan?",
        "Help with emergency hospitalization claim",
        "Compare car insurance renewal discounts"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(WhatsAppGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        "WhatsApp Quick Desk",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = NavyPrimary
                    )
                    Text(
                        "Replies typically in < 5 mins • 24/7 Active",
                        fontSize = 11.sp,
                        color = EmeraldGreen,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    "Chat instantly with an IRDAI-certified Fincall consultant on WhatsApp (+91 98765 43210):",
                    fontSize = 13.sp,
                    color = SlateMedium
                )
                Spacer(modifier = Modifier.height(10.dp))

                quickOptions.forEach { opt ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onSendMessage(opt)
                                launchWhatsAppIntent(context, opt)
                                onDismiss()
                            },
                        color = SlateBackground,
                        shape = RoundedCornerShape(8.dp),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("💬", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(opt, fontSize = 12.sp, color = SlateMedium, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = customQuery,
                    onValueChange = { customQuery = it },
                    label = { Text("Or write your question...") },
                    placeholder = { Text("e.g. Need policy renewal support") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("whatsapp_custom_query_input"),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val msg = customQuery.ifBlank { "Hello Fincall Insurance team, I need insurance consultation." }
                    onSendMessage(msg)
                    launchWhatsAppIntent(context, msg)
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                modifier = Modifier.testTag("whatsapp_start_chat_button")
            ) {
                Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Open WhatsApp Chat", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

fun launchWhatsAppIntent(context: android.content.Context, message: String) {
    try {
        val encoded = Uri.encode(message)
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=919876543210&text=$encoded")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (e: Exception) {
        // WhatsApp not installed or error
    }
}

@Composable
fun LeadMagnetDialog(
    onDismiss: () -> Unit,
    onDownloaded: () -> Unit
) {
    var emailInput by remember { mutableStateOf("") }
    var phoneInput by remember { mutableStateOf("") }
    var isUnlocked by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("📘", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "FREE LEAD MAGNET GUIDE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BluePrimary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Text(
                    "The Ultimate Guide to Selecting Family Health Cover",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )
                Text(
                    "5 Insider secrets insurers won't tell you about room rent caps, cashless networks, & Section 80D tax deductions.",
                    fontSize = 12.sp,
                    color = SlateLight,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Divider(modifier = Modifier.padding(vertical = 10.dp))

                if (!isUnlocked) {
                    Text(
                        "Enter your details to instantly view and download your free copy:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = SlateMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = phoneInput,
                        onValueChange = { if (it.length <= 10 && it.all { c -> c.isDigit() }) phoneInput = it },
                        label = { Text("Mobile Number") },
                        placeholder = { Text("10-digit number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("lead_magnet_phone_input")
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = emailInput,
                        onValueChange = { emailInput = it },
                        label = { Text("Email Address") },
                        placeholder = { Text("e.g. name@example.com") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("lead_magnet_email_input")
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = {
                            isUnlocked = true
                            onDownloaded()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("lead_magnet_unlock_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Unlock & Read Full Guide Now")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(EmeraldGreen.copy(alpha = 0.12f))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Guide Unlocked! Sent to your email & available below:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    SampleData.leadMagnetGuideSections.forEach { (heading, content) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = SlateBackground),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(heading, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NavyPrimary)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(content, fontSize = 12.sp, color = SlateMedium, lineHeight = 18.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
                    ) {
                        Text("Done")
                    }
                }
            }
        }
    }
}

@Composable
fun AdvisorCallbackDialog(
    onDismiss: () -> Unit,
    onSubmit: (name: String, phone: String, type: String) -> Unit
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Health Insurance") }
    var isSubmitted by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = null, tint = BluePrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Talk to an Advisor", fontWeight = FontWeight.Bold, color = NavyPrimary)
            }
        },
        text = {
            if (!isSubmitted) {
                Column {
                    Text(
                        "Get unbiased, customized advice from an IRDAI-registered Fincall specialist in under 60 seconds.",
                        fontSize = 13.sp,
                        color = SlateMedium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Your Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("advisor_callback_name_input")
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { if (it.length <= 10 && it.all { c -> c.isDigit() }) phone = it },
                        label = { Text("Mobile Number") },
                        placeholder = { Text("10-digit number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("advisor_callback_phone_input")
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Select Area of Advice:", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = SlateLight)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        listOf("Health", "Vehicle", "Life").forEach { t ->
                            val isSel = selectedType.startsWith(t)
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (isSel) BluePrimary else SlateBackground,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { selectedType = "$t Insurance" }
                            ) {
                                Text(
                                    t,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                    fontSize = 12.sp,
                                    color = if (isSel) Color.White else SlateMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EmeraldGreen,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Callback Requested!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = NavyPrimary
                    )
                    Text(
                        "An authorized Fincall Consultant will call $phone in under 60 seconds. You can also dial our 24/7 toll-free hotline directly.",
                        fontSize = 12.sp,
                        color = SlateMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            if (!isSubmitted) {
                Button(
                    onClick = {
                        isSubmitted = true
                        onSubmit(name, phone, selectedType)
                    },
                    modifier = Modifier.testTag("submit_callback_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                ) {
                    Text("Call Me in 60s")
                }
            } else {
                Button(
                    onClick = {
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:18002008899"))
                        try {
                            context.startActivity(dialIntent)
                        } catch (e: Exception) {}
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Dial 1800-FINCALL Now")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(if (isSubmitted) "Close" else "Cancel")
            }
        }
    )
}

@Composable
fun AboutUsDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, contentDescription = null, tint = BluePrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "ABOUT FINCALL",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = BluePrimary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Text(
                    "Fincall Insurance Marketing Consultants Pvt Ltd",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrimary
                )
                Text(
                    "Your Reliable Partner in Financial & Insurance Security",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = EmeraldGreen,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Divider(modifier = Modifier.padding(vertical = 10.dp))

                Text(
                    "Fincall Insurance Marketing Consultants' mission is to make insurance simple, transparent, and universally accessible for every Indian household and business. We emphasize strict IRDAI compliance, 100% unbiased consultations, and customer-first values.",
                    fontSize = 13.sp,
                    color = SlateMedium,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                listOf(
                    "IRDAI Registered Firm" to "Certified insurance marketing consultants holding valid credentials under Insurance Regulatory and Development Authority of India.",
                    "Unbiased Advisory" to "We partner with all leading insurers (Star, HDFC, ICICI, Tata AIG, Care, Max) to advocate solely for the policyholder.",
                    "Zero Hidden Costs" to "Complete transparency with zero processing surcharges or hidden exclusions.",
                    "Dedicated Claims Desk" to "From hospital admission to final discharge, our 24/7 claims desk takes charge of all documentation."
                ).forEach { (title, desc) ->
                    Row(modifier = Modifier.padding(vertical = 6.dp)) {
                        Icon(
                            Icons.Default.Verified,
                            contentDescription = null,
                            tint = BluePrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NavyPrimary)
                            Text(desc, fontSize = 12.sp, color = SlateLight, lineHeight = 16.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SlateBackground)
                        .padding(12.dp)
                ) {
                    Column {
                        Text("Head Office & Support:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SlateLight)
                        Text("Fincall Tower, Financial District, New Delhi - 110001", fontSize = 12.sp, color = SlateMedium)
                        Text("Email: claims@fincall.in | info@fincall.in", fontSize = 12.sp, color = BluePrimary)
                        Text("24/7 Helpline: 1800-FINCALL (1800-200-8899)", fontSize = 12.sp, color = EmeraldGreen, fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
                ) {
                    Text("Close")
                }
            }
        }
    }
}
