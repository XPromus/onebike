package com.xpromus.onebike_backend.util

import org.springframework.data.domain.Sort

enum class SortDirection {
    ASCENDING, DESCENDING
}

fun SortDirection.toSortDir(): Sort.Direction = when (this) {
    SortDirection.ASCENDING -> Sort.Direction.ASC
    SortDirection.DESCENDING -> Sort.Direction.DESC
}
