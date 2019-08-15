package siarhei.luskanau.example.roomrxjava

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.Date
import siarhei.luskanau.example.roomrxjava.persistence.ModelDao
import siarhei.luskanau.example.roomrxjava.persistence.ModelEntity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var modelsTextView: TextView
    private var models: List<ModelEntity> = emptyList()
    private var modelsDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        modelsTextView = findViewById(R.id.models_text_view)

        findViewById<Button?>(R.id.add_model_button)?.setOnClickListener {
            Timber.d("add_model_button clicked")
            addModel()
        }

        findViewById<Button?>(R.id.update_model_button)?.setOnClickListener {
            Timber.d("update_model_button clicked")
            updateModel()
        }

        findViewById<Button?>(R.id.delete_all_models_button)?.setOnClickListener {
            Timber.d("delete_all_models_button clicked")
            deleteAllModels()
        }
    }

    override fun onStart() {
        super.onStart()

        modelsDisposable = getDao().getModels()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { models: List<ModelEntity> ->
                            this.models = models
                            val modelsValue = models.toString()
                            Timber.d(modelsValue)
                            modelsTextView.text = modelsValue
                        },
                        { t: Throwable? -> Timber.e(t) },
                        { Timber.d("getDao().getModels().onComplete") }

                )
    }

    override fun onStop() {
        super.onStop()

        modelsDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        modelsDisposable = null
    }

    private fun addModel() {
        Completable.fromAction {
            getDao().insertModel(ModelEntity(name = Date().toString()))
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Timber.d("addModel.onComplete") },
                        { t: Throwable? -> Timber.e(t) }
                )
    }

    private fun updateModel() {
        Completable.fromAction {
            models.firstOrNull()?.let {
                getDao().updateModel(it.copy(name = Date().toString()))
            }
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Timber.d("updateModel.onComplete") },
                        { t: Throwable? -> Timber.e(t) }
                )
    }

    private fun deleteAllModels() {
        Completable.fromAction {
            getDao().deleteAllModels()
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Timber.d("deleteAllModels.onComplete") },
                        { t: Throwable? -> Timber.e(t) }
                )
    }

    private fun getDao(): ModelDao =
            (application as AppApplication).appDatabase.modelDao()
}
