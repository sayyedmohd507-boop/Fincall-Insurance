package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ChatMessage
import com.example.data.ClaimTicket
import com.example.data.InsurancePlan
import com.example.data.InsuranceType
import com.example.data.SampleData
import com.example.data.TrafficChallan
import com.example.network.GeminiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class FincallScreen(val label: String) {
    HOME("Home"),
    QUOTE("Get Quote"),
    HEALTH_CALC("Health Quote"),
    CHALLAN("Challan Pay"),
    CLAIMS("Claims 24/7"),
    AI_ASSISTANT("Fincall AI")
}

data class FincallUiState(
    val currentScreen: FincallScreen = FincallScreen.HOME,
    
    // Quote Hub
    val selectedType: InsuranceType = InsuranceType.HEALTH,
    val mobileNumber: String = "",
    val pincode: String = "",
    val cityDetected: String = "Delhi NCR",
    val quotesList: List<InsurancePlan> = emptyList(),
    val isSearchingQuotes: Boolean = false,
    
    // Health Calculator
    val healthAge: Int = 32,
    val healthFamily: String = "Self + Spouse + 1 Child",
    val healthSumInsured: Int = 10, // 10 Lakhs
    val healthEstimatedAnnual: Int = 11450,
    val healthTaxSavings: Int = 25000,
    
    // Challan Pay
    val vehicleNumber: String = "DL 01 AB 1234",
    val challans: List<TrafficChallan> = emptyList(),
    val hasSearchedChallan: Boolean = false,
    val paidChallans: Set<String> = emptySet(),
    val showMotorDiscountBanner: Boolean = false,
    val showRenewalQuoteModal: Boolean = false,
    
    // Claims Desk
    val claimTickets: List<ClaimTicket> = SampleData.initialTickets,
    val trackedTicket: ClaimTicket? = null,
    val ticketQuery: String = "",
    val showCreateTicketModal: Boolean = false,
    val newlyCreatedTicketId: String? = null,
    
    // AI Chat
    val chatMessages: List<ChatMessage> = listOf(
        ChatMessage(
            id = "welcome",
            text = "Namaste! I am **Fincall AI**, your 24/7 personal insurance advisor. How can I assist you with your health, vehicle, life, or business insurance today?",
            isUser = false,
            timestamp = "Just now"
        )
    ),
    val isAiThinking: Boolean = false,
    
    // Global Dialogs
    val showLeadMagnetModal: Boolean = false,
    val showAdvisorCallbackModal: Boolean = false,
    val showWhatsAppModal: Boolean = false,
    val showAboutUsModal: Boolean = false,
    val snackbarMessage: String? = null
)

class FincallViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FincallUiState())
    val uiState: StateFlow<FincallUiState> = _uiState.asStateFlow()

    private val geminiService = GeminiService()

    init {
        // Preload default quotes
        filterQuotes(InsuranceType.HEALTH)
        recalculateHealthPremium(32, "Self + Spouse + 1 Child", 10)
    }

    fun setScreen(screen: FincallScreen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun setSelectedInsuranceType(type: InsuranceType) {
        _uiState.value = _uiState.value.copy(selectedType = type)
        filterQuotes(type)
    }

    fun updateMobile(mobile: String) {
        if (mobile.length <= 10 && mobile.all { it.isDigit() }) {
            _uiState.value = _uiState.value.copy(mobileNumber = mobile)
        }
    }

    fun updatePincode(pin: String) {
        if (pin.length <= 6 && pin.all { it.isDigit() }) {
            val city = when {
                pin.startsWith("11") -> "New Delhi, DL"
                pin.startsWith("40") -> "Mumbai, MH"
                pin.startsWith("56") -> "Bengaluru, KA"
                pin.startsWith("60") -> "Chennai, TN"
                pin.startsWith("50") -> "Hyderabad, TS"
                pin.startsWith("70") -> "Kolkata, WB"
                pin.startsWith("41") -> "Pune, MH"
                else -> "India (All Network Hospitals Available)"
            }
            _uiState.value = _uiState.value.copy(pincode = pin, cityDetected = city)
        }
    }

    fun searchQuotes() {
        _uiState.value = _uiState.value.copy(isSearchingQuotes = true)
        filterQuotes(_uiState.value.selectedType)
        _uiState.value = _uiState.value.copy(
            isSearchingQuotes = false,
            snackbarMessage = "Found top recommended IRDAI plans for your location!"
        )
    }

    private fun filterQuotes(type: InsuranceType) {
        val filtered = SampleData.samplePlans.filter { it.type == type }
        _uiState.value = _uiState.value.copy(quotesList = filtered)
    }

    // Health Calculator
    fun updateHealthAge(age: Int) {
        recalculateHealthPremium(age, _uiState.value.healthFamily, _uiState.value.healthSumInsured)
    }

    fun updateHealthFamily(family: String) {
        recalculateHealthPremium(_uiState.value.healthAge, family, _uiState.value.healthSumInsured)
    }

    fun updateHealthSumInsured(sumLakhs: Int) {
        recalculateHealthPremium(_uiState.value.healthAge, _uiState.value.healthFamily, sumLakhs)
    }

    private fun recalculateHealthPremium(age: Int, family: String, sumLakhs: Int) {
        val baseRate = when {
            age < 30 -> 6500
            age < 45 -> 9500
            age < 60 -> 14500
            else -> 23000
        }
        val familyMultiplier = when (family) {
            "Self" -> 1.0
            "Couple" -> 1.6
            "Self + Spouse + 1 Child" -> 1.95
            "Family (2+ Children)" -> 2.3
            else -> 2.8 // Parents
        }
        val sumMultiplier = when (sumLakhs) {
            5 -> 0.75
            10 -> 1.0
            25 -> 1.45
            50 -> 1.85
            else -> 2.4 // 100 Lakhs / 1 Cr
        }
        val estimated = (baseRate * familyMultiplier * sumMultiplier).toInt()
        val taxSave = if (family.contains("Parents")) 75000 else 25000

        _uiState.value = _uiState.value.copy(
            healthAge = age,
            healthFamily = family,
            healthSumInsured = sumLakhs,
            healthEstimatedAnnual = estimated,
            healthTaxSavings = taxSave
        )
    }

    // Challan Pay
    fun updateVehicleNumber(vNo: String) {
        _uiState.value = _uiState.value.copy(vehicleNumber = vNo.uppercase())
    }

    fun checkPendingChallans() {
        val formatted = _uiState.value.vehicleNumber.trim().uppercase()
        val matches = SampleData.sampleChallans.filter { 
            it.vehicleNo.replace(" ", "").equals(formatted.replace(" ", ""), ignoreCase = true)
        }
        val result = if (matches.isNotEmpty()) {
            matches
        } else {
            // Generate standard lookup record for entered plate
            listOf(
                TrafficChallan(
                    challanNo = "DL${(100000..999999).random()}",
                    vehicleNo = if (formatted.isNotBlank()) formatted else "DL 01 AB 1234",
                    offense = "Unauthorized parking / Zebra crossing obstruction",
                    fineAmount = 500,
                    offenseDate = "Recently Detected",
                    location = "Central Commercial District",
                    status = "Pending"
                )
            )
        }

        _uiState.value = _uiState.value.copy(
            challans = result,
            hasSearchedChallan = true,
            showMotorDiscountBanner = true // Crucial high-converting value add!
        )
    }

    fun payChallan(challanNo: String) {
        val updated = _uiState.value.paidChallans + challanNo
        _uiState.value = _uiState.value.copy(
            paidChallans = updated,
            snackbarMessage = "Challan #$challanNo paid & cleared successfully! Receipt generated."
        )
    }

    fun toggleRenewalModal(show: Boolean) {
        _uiState.value = _uiState.value.copy(showRenewalQuoteModal = show)
    }

    // Claims Desk
    fun updateTicketQuery(q: String) {
        _uiState.value = _uiState.value.copy(ticketQuery = q)
    }

    fun trackTicket(ticketIdOrPolicy: String) {
        val q = ticketIdOrPolicy.trim()
        val found = _uiState.value.claimTickets.find {
            it.ticketId.equals(q, ignoreCase = true) || it.policyNo.equals(q, ignoreCase = true)
        } ?: _uiState.value.claimTickets.firstOrNull()

        _uiState.value = _uiState.value.copy(
            trackedTicket = found,
            snackbarMessage = if (found != null) "Found claim status for ${found.ticketId}" else "Ticket not found"
        )
    }

    fun submitNewClaim(
        policyNo: String,
        insurer: String,
        claimant: String,
        mobile: String,
        claimType: String,
        hospital: String
    ) {
        val newId = "FIN-CLM-${(1000..9999).random()}"
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val newTicket = ClaimTicket(
            ticketId = newId,
            policyNo = policyNo.ifBlank { "POL-FIN-9921" },
            insurerName = insurer.ifBlank { "HDFC ERGO" },
            claimantName = claimant.ifBlank { "Policyholder" },
            mobileNumber = mobile.ifBlank { "9876543210" },
            claimType = claimType,
            hospitalOrLocation = hospital.ifBlank { "Network Hospital" },
            dateReported = sdf.format(Date()),
            status = "Reported & Assigned",
            progressStep = 1
        )
        val updated = listOf(newTicket) + _uiState.value.claimTickets
        _uiState.value = _uiState.value.copy(
            claimTickets = updated,
            trackedTicket = newTicket,
            showCreateTicketModal = false,
            newlyCreatedTicketId = newId,
            snackbarMessage = "Claim Ticket $newId generated! Fincall Desk is on it."
        )
    }

    fun toggleCreateTicketModal(show: Boolean) {
        _uiState.value = _uiState.value.copy(showCreateTicketModal = show)
    }

    // AI Chat
    fun sendAiPrompt(promptText: String) {
        if (promptText.isBlank()) return
        val userMsg = ChatMessage(
            id = UUID.randomUUID().toString(),
            text = promptText,
            isUser = true,
            timestamp = "Just now"
        )
        val currentList = _uiState.value.chatMessages + userMsg
        _uiState.value = _uiState.value.copy(
            chatMessages = currentList,
            isAiThinking = true
        )

        viewModelScope.launch {
            val responseText = geminiService.askInsuranceAssistant(promptText)
            val aiMsg = ChatMessage(
                id = UUID.randomUUID().toString(),
                text = responseText,
                isUser = false,
                timestamp = "Just now"
            )
            _uiState.value = _uiState.value.copy(
                chatMessages = _uiState.value.chatMessages + aiMsg,
                isAiThinking = false
            )
        }
    }

    // Modals
    fun toggleLeadMagnet(show: Boolean) {
        _uiState.value = _uiState.value.copy(showLeadMagnetModal = show)
    }

    fun toggleAdvisorCallback(show: Boolean) {
        _uiState.value = _uiState.value.copy(showAdvisorCallbackModal = show)
    }

    fun toggleWhatsAppModal(show: Boolean) {
        _uiState.value = _uiState.value.copy(showWhatsAppModal = show)
    }

    fun toggleAboutUsModal(show: Boolean) {
        _uiState.value = _uiState.value.copy(showAboutUsModal = show)
    }

    fun dismissSnackbar() {
        _uiState.value = _uiState.value.copy(snackbarMessage = null)
    }

    fun showToast(msg: String) {
        _uiState.value = _uiState.value.copy(snackbarMessage = msg)
    }
}
