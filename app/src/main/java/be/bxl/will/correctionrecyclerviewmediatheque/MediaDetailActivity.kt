package be.bxl.will.correctionrecyclerviewmediatheque

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import be.bxl.will.correctionrecyclerviewmediatheque.models.Media
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MediaDetailActivity : AppCompatActivity() {

    private lateinit var etTitle : EditText
    private lateinit var etDescription : EditText
    private lateinit var etType : EditText
    private lateinit var etDuration : EditText
    private lateinit var btnSave : FloatingActionButton

    private var media : Media? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)

        etTitle = findViewById(R.id.et_title_media_detail)
        etDescription = findViewById(R.id.et_description_media_detail)
        etType = findViewById(R.id.et_type_media_detail)
        etDuration = findViewById(R.id.et_duration_media_detail)
        btnSave = findViewById(R.id.btn_save_media_detail)

        val mediaSerializable = intent.getSerializableExtra("media")
        media = mediaSerializable as Media?

        media?.let {
            etTitle.setText(it.title)
            etDescription.setText(it.description)
            etType.setText(it.type)
            etDuration.setText(it.duration.toString())
        }

        btnSave.setOnClickListener {
            val intent = Intent()
            intent.putExtra("media", Media(
                etTitle.text.toString(),
                etType.text.toString(),
                etDescription.text.toString(),
                etDuration.text.toString().toLong(),
                media?.id
            ))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}