package com.example.newsaggregator.ui.model

import com.example.newsaggregator.domain.model.DataDomain
import javax.inject.Inject

class NewsMapperUi @Inject constructor() {

    fun DataDomain.mapDomainToUi() = DataUi(
        id = this.id,
        title = this.title,
        description = this.description,
        pubDate = this.pubDate,
        image = this.image,
        dcCreator = this.dcCreator,
    )
}
