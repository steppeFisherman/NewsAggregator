package com.example.newsaggregator.ui.model

import com.example.newsaggregator.domain.model.DataDomain
import javax.inject.Inject

class NewsMapperUi @Inject constructor() {

    fun mapDomainToUi(dataDomain: DataDomain) = DataUi(
        id = dataDomain.id,
        title = dataDomain.title,
        description = dataDomain.description,
        pubDate = dataDomain.pubDate,
        image = dataDomain.image,
        dcCreator = dataDomain.dcCreator,
    )
}
