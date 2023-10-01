package com.prplmnstr.invoiceapp.pdf_templetes

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import com.itextpdf.text.PageSize
//import com.itextpdf.text.PageSize
import com.prplmnstr.invoiceapp.R
import com.prplmnstr.invoiceapp.model.Invoice
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Templete1 {

    fun createPdf(context: Context, activity: Activity, invoice: Invoice){

        var height = PageSize.A4.height.toInt()

        val width = PageSize.A4.width.toInt()
        val REQUEST_STORAGE_PERMISSION = 1

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_STORAGE_PERMISSION
            )
        } else {
            var fileName ="invoice"
            val stringFilePath =
                Environment.getExternalStorageDirectory().path + "/Download/$fileName.pdf"
            val file = File(stringFilePath)

            val pdfDocument = PdfDocument()

            val titlePaint = Paint().apply {
                textAlign = Paint.Align.CENTER
                textSize = 24f
                typeface = ResourcesCompat.getFont(
                    context, R.font.segeo_bold
                )
            }
            val headingPaint = Paint().apply {
                textAlign = Paint.Align.LEFT
                textSize = 14f
                typeface = ResourcesCompat.getFont(
                    context, R.font.segeo_semi_bold
                )
            }

            val myPageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
            val page1 = pdfDocument.startPage(myPageInfo)
            val canvas = page1.canvas

            // Load the image to be placed at the top-left corner
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.from
            )

            // Draw the image at the top-left corner
            val imageRect = Rect(0, 0, 100, 100)
            canvas.drawBitmap(bitmap, null, imageRect, null)


            // Define paint styles
            val textPaint = Paint()
            textPaint.color = Color.BLACK
            textPaint.textSize = 16f



            val margin = 30f
            val lineSpacing = 20f

            // Define the invoice details
            val invoiceTitle = "Invoice"
            val invoiceNumber = "Invoice Number: ${invoice.invoiceNumber}"
            val invoiceDate = "Invoice Date: ${invoice.invoiceDetails.creationDate}"
            val dueDate = "Due Date: ${invoice.invoiceDetails.dueDate}"

            // Draw the invoice details on the canvas
            canvas.drawText(invoiceTitle, margin*2, imageRect.bottom + lineSpacing, titlePaint)
            canvas.drawText(invoiceNumber, margin, imageRect.bottom + 2 * lineSpacing, textPaint)
            canvas.drawText(invoiceDate, margin, imageRect.bottom + 3 * lineSpacing, textPaint)
            canvas.drawText(dueDate, margin, imageRect.bottom + 4 * lineSpacing, textPaint)

            // Draw other invoice details like client details, item list, etc., similarly


            //pdf writing end

            pdfDocument.finishPage(page1)

            try {
                FileOutputStream(file).use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            pdfDocument.close()

            Toast.makeText(context, "Downloaded in /Downloads folder.", Toast.LENGTH_LONG).show()


            val intent = Intent(Intent.ACTION_VIEW)
            var uri: Uri = Uri.fromFile(file)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(
                    context,
                    "${context.applicationContext.packageName}.provider",
                    file
                )
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            intent.setDataAndType(uri, "application/pdf")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                activity.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No application found to open the PDF file.",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }


}