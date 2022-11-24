package be.bxl.will.correctionrecyclerviewmediatheque.models

data class Media (
    var title : String,
    var type : String,
    var description : String,
    var duration : Long,
    var id : Int?
    ) : java.io.Serializable