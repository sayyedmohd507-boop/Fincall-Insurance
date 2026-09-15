package com.example.network

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val systemInstruction = """
        You are Fincall AI, the friendly, professional, 24/7 AI insurance consultant representing 'Fincall Insurance Marketing Consultants Pvt Ltd' (India's trusted IRDAI registered insurance marketing firm).
        Provide clear, unbiased, transparent insurance guidance on Health, Motor (Vehicle), Term Life, and Business insurance.
        Highlight Fincall's key advantages:
        - Unbiased multi-insurer comparisons (Star Health, HDFC ERGO, ICICI Lombard, Tata AIG, Care, Max Life)
        - 100% cashless hospital assistance & dedicated claims desk
        - Zero hidden fees and transparent policy wordings
        - Traffic challan clearing service with up to 50% discount on vehicle insurance renewal
        - Tax savings under Section 80D (up to ₹75,000)
        - 24/7 emergency hotline & claims@fincall.in ticketing desk
        Keep answers concise, bulleted, reassuring, and actionable.
    """.trimIndent()

    suspend fun askInsuranceAssistant(prompt: String): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Throwable) {
            ""
        }

        if (apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY") {
            try {
                val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

                val rootJson = JSONObject().apply {
                    val contentsArr = JSONArray().apply {
                        val userObj = JSONObject().apply {
                            put("role", "user")
                            val partsArr = JSONArray().apply {
                                put(JSONObject().put("text", prompt))
                            }
                            put("parts", partsArr)
                        }
                        put(userObj)
                    }
                    put("contents", contentsArr)

                    val sysObj = JSONObject().apply {
                        val partsArr = JSONArray().apply {
                            put(JSONObject().put("text", systemInstruction))
                        }
                        put("parts", partsArr)
                    }
                    put("systemInstruction", sysObj)
                }

                val body = rootJson.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string() ?: ""
                        val parsed = JSONObject(responseBody)
                        val candidates = parsed.optJSONArray("candidates")
                        if (candidates != null && candidates.length() > 0) {
                            val firstCand = candidates.getJSONObject(0)
                            val content = firstCand.optJSONObject("content")
                            val parts = content?.optJSONArray("parts")
                            if (parts != null && parts.length() > 0) {
                                val text = parts.getJSONObject(0).optString("text")
                                if (text.isNotBlank()) return@withContext text
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                // Fall back to rule-based insurance intelligence engine
            }
        }

        // Smart Knowledge Base Fallback
        return@withContext generateExpertInsuranceAnswer(prompt)
    }

    private fun generateExpertInsuranceAnswer(query: String): String {
        val lower = query.lowercase()
        return when {
            lower.contains("parent") || lower.contains("senior") || (lower.contains("health") && lower.contains("pick")) -> {
                """
                🛡️ **Recommended Health Coverage for Parents:**
                
                1. **No Room Rent Capping:** Choose plans like *HDFC ERGO Optima Secure* or *Star Health Senior Red Carpet* to prevent proportionate deduction penalties.
                2. **Shorter Waiting Periods:** Look for policies with 1–2 year waiting periods for pre-existing conditions like hypertension or diabetes (instead of the typical 4 years).
                3. **Zero Co-Pay Options:** Aim for 0% co-pay so seniors don't pay out of pocket at discharge.
                4. **Tax Benefit u/s 80D:** You can claim an additional tax deduction of up to **₹50,000/year** for parents aged 60+.
                
                💡 *Fincall's dedicated claims desk handles all hospital pre-authorization paperwork for your family.*
                """.trimIndent()
            }
            lower.contains("motor") || lower.contains("car") || lower.contains("document") || lower.contains("file") && lower.contains("claim") -> {
                """
                🚗 **Essential Documents for Motor Insurance Claim:**
                
                1. **Claim Form:** Duly signed (Fincall can pre-fill this for you).
                2. **Vehicle Registration Certificate (RC):** Copy of original.
                3. **Driver's License:** Copy of the person driving at the time of the incident.
                4. **Policy Copy:** Your current active insurance policy schedule.
                5. **Incident Photos / Video:** Clear photos of damages before vehicle is moved.
                6. **FIR Copy:** Required only in case of third-party bodily injury, property damage, or vehicle theft.
                
                📞 *Fincall Desk will coordinate cashless towing and survey at one of our 7,500+ network garages!*
                """.trimIndent()
            }
            lower.contains("discount") || lower.contains("renewal") || lower.contains("ncb") || lower.contains("challan") -> {
                """
                💰 **Maximizing Your Vehicle Policy Renewal Discount:**
                
                1. **No Claim Bonus (NCB) Transfer:** Carry forward up to **50% discount** from your previous insurer even if you switch providers.
                2. **Voluntary Deductible:** Opting for a small voluntary deductible can lower your OD (Own Damage) premium by 15–20%.
                3. **Anti-theft Device Discount:** ARAI-approved GPS trackers yield an additional 2.5% discount.
                4. **Fincall Challan Bonus:** Renew your policy after clearing a challan on Fincall to unlock exclusive bundled benefits.
                
                👉 Tap **"Get Renewal Quote"** to see instant discounted quotes from Tata AIG, ICICI Lombard & HDFC ERGO.
                """.trimIndent()
            }
            lower.contains("tax") || lower.contains("80d") -> {
                """
                📑 **Tax Deductions under Section 80D:**
                
                - **Self, Spouse & Dependent Children:** Up to ₹25,000 per financial year.
                - **Parents (below 60 years):** Additional ₹25,000 deduction.
                - **Senior Citizen Parents (60+ years):** Additional ₹50,000 deduction.
                - **Preventive Health Check-up:** Up to ₹5,000 included within the limits.
                
                🎯 **Total Maximum Tax Deduction:** Up to **₹75,000 - ₹1,00,000** annually!
                """.trimIndent()
            }
            else -> {
                """
                Hello! I am your **Fincall AI Insurance Assistant**.
                
                I can help you with:
                • Tailored plan recommendations (Health, Motor, Life & Business)
                • Instant premium estimation and Section 80D tax calculations
                • Step-by-step guidance on filing cashless claims & required paperwork
                • Traffic challan clearing & vehicle renewal discounts (up to 50% NCB)
                
                Feel free to ask questions like:
                - *"Help me pick the best health insurance for my parents"*
                - *"What documents do I need to file a motor claim?"*
                - *"Calculate my policy renewal discount"*
                """.trimIndent()
            }
        }
    }
}
