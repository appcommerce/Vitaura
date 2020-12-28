package com.example.vitaura.pojo

data class Page(val title: String?, val text: String?, val docs: List<Doc>?)
data class Doc(var title: String?, var url: String?)
