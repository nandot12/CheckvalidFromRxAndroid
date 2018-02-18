package id.co.imastudio.checkvalidfromrxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validEdit(username)
        validEdit(password)

    }

    private fun validEdit(editText: EditText) {
        RxTextView.textChanges(editText)
                .map { t: CharSequence -> t.length < 5 && t.length > 0}

                .debounce(1,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: Boolean? ->
                    if(t!!){
                        editText.setError("karakter harus lebih dari 5 ya om")
                    }
                }

    }
}
