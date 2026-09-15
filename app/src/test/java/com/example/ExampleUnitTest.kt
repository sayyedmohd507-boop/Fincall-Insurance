package com.example

import com.example.data.InsuranceType
import com.example.data.SampleData
import com.example.ui.FincallScreen
import com.example.ui.FincallViewModel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun sampleData_containsExpectedPlansAndChallans() {
        assertTrue(SampleData.samplePlans.isNotEmpty())
        assertTrue(SampleData.sampleChallans.isNotEmpty())
        assertTrue(SampleData.sampleTestimonials.isNotEmpty())
        assertTrue(SampleData.leadMagnetGuideSections.isNotEmpty())
    }

    @Test
    fun healthPremiumCalculation_respondsToAgeAndFamily() {
        val viewModel = FincallViewModel()
        val initialPremium = viewModel.uiState.value.healthEstimatedAnnual
        assertTrue(initialPremium > 0)

        // Change age to senior
        viewModel.updateHealthAge(65)
        val seniorPremium = viewModel.uiState.value.healthEstimatedAnnual
        assertTrue("Senior premium should be higher than young adult", seniorPremium > initialPremium)

        // Check tax savings under 80D
        viewModel.updateHealthFamily("Parents (Senior)")
        assertEquals(75000, viewModel.uiState.value.healthTaxSavings)
    }

    @Test
    fun challanPay_clearsFinesAndEnablesRenewalDiscount() {
        val viewModel = FincallViewModel()
        viewModel.updateVehicleNumber("DL 01 AB 1234")
        viewModel.checkPendingChallans()

        val state = viewModel.uiState.value
        assertTrue(state.hasSearchedChallan)
        assertTrue(state.showMotorDiscountBanner)
        assertTrue(state.challans.isNotEmpty())

        val challanNo = state.challans.first().challanNo
        viewModel.payChallan(challanNo)
        assertTrue(viewModel.uiState.value.paidChallans.contains(challanNo))
    }

    @Test
    fun claimTicketCreation_generatesTicketWithTracking() {
        val viewModel = FincallViewModel()
        val initialCount = viewModel.uiState.value.claimTickets.size

        viewModel.submitNewClaim(
            policyNo = "POL-TEST-1234",
            insurer = "Star Health",
            claimant = "John Doe",
            mobile = "9876543210",
            claimType = "Emergency Cashless",
            hospital = "Apollo Delhi"
        )

        val updatedState = viewModel.uiState.value
        assertEquals(initialCount + 1, updatedState.claimTickets.size)
        assertNotNull(updatedState.trackedTicket)
        assertEquals("John Doe", updatedState.trackedTicket?.claimantName)
    }
}
