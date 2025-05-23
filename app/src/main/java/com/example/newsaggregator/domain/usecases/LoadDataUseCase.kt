package com.example.newsaggregator.domain.usecases

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: NewsRepository){

    operator fun invoke() = repository.loadData()

}