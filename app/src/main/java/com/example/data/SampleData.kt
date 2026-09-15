package com.example.data

object SampleData {
    val samplePlans = listOf(
        InsurancePlan(
            id = "plan_health_star",
            provider = "Star Health & Allied",
            planName = "Comprehensive Family Health Protect",
            type = InsuranceType.HEALTH,
            coverAmount = "₹10,00,000",
            annualPremium = 11450,
            monthlyEmi = 999,
            cashlessHospitalsCount = "14,000+ Hospitals",
            copayPercentage = "0% (Zero Copay)",
            waitingPeriodYears = "1 Year for Specific Illnesses",
            keyFeatures = listOf(
                "100% Cashless Hospitalization",
                "Restore Benefit up to 100%",
                "Day care procedures covered",
                "Tax deduction up to ₹75,000 u/s 80D"
            ),
            popularTag = "BEST SELLER"
        ),
        InsurancePlan(
            id = "plan_health_hdfc",
            provider = "HDFC ERGO",
            planName = "Optima Secure 4X Coverage",
            type = InsuranceType.HEALTH,
            coverAmount = "₹25,00,000",
            annualPremium = 16890,
            monthlyEmi = 1450,
            cashlessHospitalsCount = "12,500+ Hospitals",
            copayPercentage = "0% Copay Across All Ages",
            waitingPeriodYears = "2 Years Pre-existing",
            keyFeatures = listOf(
                "2X cover from Day 1, grows to 4X",
                "Zero deduction on consumables",
                "Free annual health check-up",
                "Guaranteed no claim bonus"
            ),
            popularTag = "MAX VALUE"
        ),
        InsurancePlan(
            id = "plan_health_care",
            provider = "Care Health Insurance",
            planName = "Care Supreme Unlimited Recharge",
            type = InsuranceType.HEALTH,
            coverAmount = "₹50,00,000",
            annualPremium = 22100,
            monthlyEmi = 1890,
            cashlessHospitalsCount = "11,000+ Hospitals",
            copayPercentage = "0% Copay",
            waitingPeriodYears = "3 Years",
            keyFeatures = listOf(
                "Unlimited automatic recharge",
                "No room rent capping",
                "AYUSH treatment covered",
                "Worldwide emergency cover"
            )
        ),
        InsurancePlan(
            id = "plan_motor_tata",
            provider = "Tata AIG General",
            planName = "AutoSecure Comprehensive Motor",
            type = InsuranceType.VEHICLE,
            coverAmount = "IDV ₹6,80,000",
            annualPremium = 6250,
            monthlyEmi = 550,
            cashlessHospitalsCount = "7,500+ Cashless Garages",
            copayPercentage = "Zero Depreciation Included",
            waitingPeriodYears = "Instant Policy Issue",
            keyFeatures = listOf(
                "Up to 50% No Claim Bonus Transfer",
                "24/7 Roadside Assistance & Towing",
                "Engine & Gearbox Protection Add-on",
                "Consumables & Return-to-Invoice"
            ),
            popularTag = "50% OFF SPECIAL"
        ),
        InsurancePlan(
            id = "plan_motor_icici",
            provider = "ICICI Lombard",
            planName = "i-Drive Zero Dep Motor Package",
            type = InsuranceType.VEHICLE,
            coverAmount = "IDV ₹8,50,000",
            annualPremium = 7450,
            monthlyEmi = 650,
            cashlessHospitalsCount = "8,200+ Network Garages",
            copayPercentage = "Bumper to Bumper",
            waitingPeriodYears = "Immediate Activation",
            keyFeatures = listOf(
                "Quick video assessment claims in 30 mins",
                "Personal Accident Cover ₹15 Lakhs",
                "Key and Lock replacement cover",
                "Free doorstep vehicle pickup"
            )
        ),
        InsurancePlan(
            id = "plan_life_max",
            provider = "Max Life Insurance",
            planName = "Smart Total Elite Protection",
            type = InsuranceType.LIFE,
            coverAmount = "₹1,00,00,000 (1 Crore)",
            annualPremium = 7800,
            monthlyEmi = 680,
            cashlessHospitalsCount = "99.51% Claim Paid Ratio",
            copayPercentage = "Tax Free Payout u/s 10(10D)",
            waitingPeriodYears = "Immediate Life Cover",
            keyFeatures = listOf(
                "1 Cr cover from ₹23/day",
                "Critical illness benefit rider",
                "Terminal illness instant payout",
                "Return of premium option available"
            ),
            popularTag = "TOP RATED"
        ),
        InsurancePlan(
            id = "plan_biz_bajaj",
            provider = "Bajaj Allianz",
            planName = "Bharat Sookshma Udyam Security",
            type = InsuranceType.BUSINESS,
            coverAmount = "₹2,00,00,000",
            annualPremium = 9400,
            monthlyEmi = 820,
            cashlessHospitalsCount = "Dedicated Commercial Desk",
            copayPercentage = "Standard Deductible",
            waitingPeriodYears = "Nil",
            keyFeatures = listOf(
                "Comprehensive Fire, Flood & Earthquake cover",
                "Burglary, theft and money-in-transit cover",
                "Machinery breakdown protection",
                "Public liability insurance for visitors"
            )
        )
    )

    val sampleTestimonials = listOf(
        Testimonial(
            id = "t1",
            clientName = "Rajesh Verma",
            location = "New Delhi",
            rating = 5,
            policyType = "Health Insurance",
            review = "When my father was admitted for heart angioplasty, Fincall's 24/7 desk coordinated cashless approval in just 40 minutes at Apollo Hospital. Truly unbiased and supportive!",
            claimSettledAmount = "₹4,20,000 Settled Cashless"
        ),
        Testimonial(
            id = "t2",
            clientName = "Priya Sharma",
            location = "Bengaluru",
            rating = 5,
            policyType = "Car Insurance Renewal",
            review = "Used their Challan Pay to clear a speeding ticket and discovered my motor insurance had expired. Fincall saved me 45% with full NCB transfer and Zero Dep cover!",
            claimSettledAmount = "Saved ₹6,200 on Renewal"
        ),
        Testimonial(
            id = "t3",
            clientName = "Anand Deshmukh",
            location = "Pune",
            rating = 5,
            policyType = "Term Life & Health",
            review = "Fincall consultants explained the actual policy wordings instead of pushing sales targets. Their IRDAI compliance and transparency gave our family complete peace of mind.",
            claimSettledAmount = "₹1 Cr Cover Active"
        )
    )

    val sampleChallans = listOf(
        TrafficChallan(
            challanNo = "DL8492049182",
            vehicleNo = "DL 01 AB 1234",
            offense = "Over-speeding violation (Rule 112 MVA)",
            fineAmount = 2000,
            offenseDate = "12 Aug 2026",
            location = "Outer Ring Road, Near Munirka Flyover",
            status = "Pending"
        ),
        TrafficChallan(
            challanNo = "DL8492049183",
            vehicleNo = "DL 01 AB 1234",
            offense = "Improper lane driving / Signal Jumping",
            fineAmount = 1000,
            offenseDate = "28 Aug 2026",
            location = "IIT Flyover Crossing, New Delhi",
            status = "Pending"
        ),
        TrafficChallan(
            challanNo = "MH02CD567801",
            vehicleNo = "MH 02 CD 5678",
            offense = "Expired Pollution Under Control (PUC) Certificate",
            fineAmount = 1000,
            offenseDate = "04 Sep 2026",
            location = "Western Express Highway, Andheri",
            status = "Pending"
        )
    )

    val initialTickets = listOf(
        ClaimTicket(
            ticketId = "FIN-CLM-9041",
            policyNo = "STAR-HLT-4910283",
            insurerName = "Star Health",
            claimantName = "Rahul Malhotra",
            mobileNumber = "9876543210",
            claimType = "Emergency Cashless Hospitalization",
            hospitalOrLocation = "Max Super Speciality Hospital, Saket",
            dateReported = "10 Sep 2026",
            status = "Document Verification",
            progressStep = 2
        )
    )

    val leadMagnetGuideSections = listOf(
        "1. Sum Insured Guidelines" to "Choose at least 50% to 100% of your family's annual income. In metro cities like Delhi, Mumbai, or Bengaluru, medical inflation runs at 14% annually; a minimum ₹10L - ₹25L family floater is vital.",
        "2. Zero Room Rent Capping" to "Always verify proportionate deduction clauses. If your room rent is capped at 1% of sum insured and you take a deluxe room, the insurer cuts all procedure charges proportionally. Choose plans with NO room rent limit.",
        "3. Cashless Hospital Network" to "Check for premier hospitals in your 10km radius. Fincall partners with insurers having over 10,000+ cashless network hospitals across India for hassle-free admissions.",
        "4. Pre-Existing Waiting Period" to "Standard plans have 2 to 3 years waiting for pre-existing conditions like diabetes or hypertension. We recommend riders or plans that reduce waiting periods to 1 year.",
        "5. Section 80D Tax Benefits" to "Save up to ₹25,000 for self/spouse/kids (<60 yrs) + up to ₹50,000 for senior citizen parents under Section 80D, totalling up to ₹75,000 yearly tax exemption."
    )
}
