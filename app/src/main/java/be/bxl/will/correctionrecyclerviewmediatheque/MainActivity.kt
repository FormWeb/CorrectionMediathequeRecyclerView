package be.bxl.will.correctionrecyclerviewmediatheque

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.bxl.will.correctionrecyclerviewmediatheque.adapter.MediaAdapter
import be.bxl.will.correctionrecyclerviewmediatheque.models.Media
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), MediaAdapter.MediaItemListener {

    private lateinit var rvMedia : RecyclerView
    private lateinit var btnAdd : FloatingActionButton

    private lateinit var adapter : MediaAdapter

    private var medias : MutableList<Media> = mutableListOf()

    private val activityResultLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(), this::onActivityResult
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        medias.add(Media("Harry Potter", "Film", "C'est un sorcier", 1234, 1))
        medias.add(Media("Harry Potter 2", "Film", "C'est un sorcier un peu plus fort", 1234, 2))

        btnAdd = findViewById(R.id.btn_add_main)
        btnAdd.setOnClickListener {
            val intent = Intent(this, MediaDetailActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        rvMedia = findViewById(R.id.rv_media_main)
        rvMedia.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MediaAdapter(medias, this)
        rvMedia.adapter = adapter
    }

    private fun onActivityResult(result : ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val newMediaSerializable = result.data?.getSerializableExtra("media")
            val updateMedia = newMediaSerializable as Media?

            updateMedia?.let {
                var update = false
                for (media in medias) {
                    if (media.id == updateMedia.id) {
                        media.type = updateMedia.type
                        media.duration = updateMedia.duration
                        media.title = updateMedia.title
                        media.description = updateMedia.description
                        update = true
                    }
                }
                if (!update){
                    updateMedia.id = medias.size
                    medias.add(updateMedia)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(media: Media) {
        val intent = Intent(this, MediaDetailActivity::class.java)
        intent.putExtra("media", media)
        activityResultLauncher.launch(intent)
    }

    override fun onDelete(media: Media) {
        medias.remove(media)
        adapter.notifyDataSetChanged()
    }
}