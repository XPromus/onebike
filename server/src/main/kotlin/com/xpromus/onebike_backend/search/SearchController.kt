package com.xpromus.onebike_backend.search

import com.xpromus.onebike_backend.search.dto.GetSearchDto
import com.xpromus.onebike_backend.search.dto.PostSearchDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/search")
class SearchController(
    private val searchService: SearchService
) {

    @PostMapping
    fun search(
        @RequestBody postSearchDto: PostSearchDto
    ): GetSearchDto {
        return searchService.search(postSearchDto)
    }

}
