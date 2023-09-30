package com.prplmnstr.invoiceapp.data.database.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.prplmnstr.invoiceapp.model.Invoice
import com.prplmnstr.invoiceapp.utils.Constants

class FirebaseDataSource {

    val firestore = FirestoreSingleton.getInstance()

    private val invoicesCollection = firestore.collection(Constants.INVOICE_COLLECTION)

    suspend fun addInvoiceToFirebase(invoice: Invoice):FirebaseResult {
        try {
            // Convert your Invoice object to a Map
            val invoiceMap = mapOf(
                "invoiceNumber" to invoice.invoiceNumber,
                "businessDetails" to invoice.businessDetails,
                "client" to invoice.client,
                "items" to invoice.items,
                "invoiceDetails" to invoice.invoiceDetails,
                "signatureImageUrl" to invoice.signatureImageUrl,
                "overallDiscount" to invoice.overallDiscount,
                "tax" to invoice.tax
            )

            // Add the invoice to Firebase Firestore
            invoicesCollection.document(invoice.invoiceNumber)
                .set(invoiceMap, SetOptions.merge()) // Merge to update if the document already exists
            return FirebaseResult.Success("Invoice added successfully to Firebase!")

        } catch (e: FirebaseFirestoreException) {
            // Handle exceptions if necessary
            return FirebaseResult.Failure(e)

        }
    }

    suspend fun updateInvoiceInFirebase(invoice: Invoice):FirebaseResult {
        try {
            // Convert your Invoice object to a Map
            val invoiceMap = mapOf(
                "businessDetails" to invoice.businessDetails,
                "client" to invoice.client,
                "items" to invoice.items,
                "invoiceDetails" to invoice.invoiceDetails,
                "signatureImageUrl" to invoice.signatureImageUrl,
                "overallDiscount" to invoice.overallDiscount,
                "tax" to invoice.tax
            )

            // Update the invoice in Firebase Firestore
            invoicesCollection.document(invoice.invoiceNumber)
                .set(invoiceMap, SetOptions.merge()) // Merge to update only the specified fields
            return FirebaseResult.Success("Invoice updated successfully in Firebase!")

        } catch (e: FirebaseFirestoreException) {
            // Handle exceptions if necessary
            return FirebaseResult.Failure(e)
        }
    }

    object FirestoreSingleton {
        private var instance: FirebaseFirestore? = null

        fun getInstance(): FirebaseFirestore {
            if (instance == null) {
                instance = FirebaseFirestore.getInstance()
            }
            return instance!!
        }
    }





}