package com.puceTec.Students.mappers

import com.puceTec.Students.dto.StudentRequest
import com.puceTec.Students.dto.StudentResponse
import com.puceTec.Students.entities.Student

fun StudentRequest.toEntity(): Student {
    return Student(
        name = name,
        email = email
    )
}

fun Student.toResponse(): StudentResponse {
    return StudentResponse(
        id = id ?: 0,
        name = name,
        email = email ?: ""
    )
}
