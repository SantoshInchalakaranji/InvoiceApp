import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveUserLoggedIn(loggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", loggedIn)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun saveUserName(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userName", userName)
        editor.apply()
    }
    fun saveInvoiceNumber(invoiceNumber: Long) {
        val editor = sharedPreferences.edit()
        editor.putString("invoiceNumber", String.format("%04d", invoiceNumber))
        editor.apply()
    }
    fun savequotationNumber(quotationNumber: Long) {
        val editor = sharedPreferences.edit()
        editor.putString("quotationNumber", String.format("%04d", quotationNumber))
        editor.apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("userName", null)
    }
    fun getInvoiceNumber(): String? {
        return sharedPreferences.getString("invoiceNumber", "0")
    }
    fun getQuotationNumber(): String? {
        return sharedPreferences.getString("quotationNumber", "0")
    }

    fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.remove("userName")
        editor.putBoolean("isLoggedIn", false)
        editor.apply()
    }

}
