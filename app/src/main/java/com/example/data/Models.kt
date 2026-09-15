package com.example.data

enum class InsuranceType(val displayName: String, val tagline: String) {
    HEALTH("Health", "10,000+ Cashless Hospitals & 80D Tax Relief"),
    VEHICLE("Vehicle", "Zero Dep & Up to 50% No Claim Bonus Discount"),
    LIFE("Life / Term", "1 Cr Cover starting @ ₹490/mo for Family"),
    BUSINESS("Business", "Fire, Theft & Public Liability Protection")
}

data class InsurancePlan(
    val id: String,
    val provider: String,
    val planName: String,
    val type: InsuranceType,
    val coverAmount: String,
    val annualPremium: Int,
    val monthlyEmi: Int,
    val cashlessHospitalsCount: String,
    val copayPercentage: String,
    val waitingPeriodYears: String,
    val keyFeatures: List<String>,
    val popularTag: String? = null
)

data class TrafficChallan(
    val challanNo: String,
    val vehicleNo: String,
    val offense: String,
    val fineAmount: Int,
    val offenseDate: String,
    val location: String,
    val status: String,
    val courtNotice: Boolean = false
)

data class ClaimTicket(
    val ticketId: String,
    val policyNo: String,
    val insurerName: String,
    val claimantName: String,
    val mobileNumber: String,
    val claimType: String,
    val hospitalOrLocation: String,
    val dateReported: String,
    val status: String, // "Reported", "Document Verification", "Settlement in Progress", "Settled"
    val progressStep: Int // 1, 2, 3
)

data class Testimonial(
    val id: String,
    val clientName: String,
    val location: String,
    val rating: Int,
    val policyType: String,
    val review: String,
    val claimSettledAmount: String? = null
)

data class ChatMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: String
)
