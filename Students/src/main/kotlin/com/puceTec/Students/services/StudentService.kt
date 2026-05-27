package com.puceTec.Students.services

import com.puceTec.Students.dto.StudentRequest
import com.puceTec.Students.dto.StudentResponse
import com.puceTec.Students.entities.Student
import com.puceTec.Students.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {

    private val logger = LoggerFactory.getLogger(StudentService::class.java)

    fun createStudent(request: StudentRequest): StudentResponse {
        logger.info("Creating student ${request.name}")

        val studentEntity = Student(
            name = request.name,
            email = request.email
        )

        val savedStudent = studentRepository.save(studentEntity)

        return StudentResponse(
            id = savedStudent.id!!,
            name = savedStudent.name,
            email = savedStudent.email ?: ""
        )
    }

    fun getAllStudents(): List<StudentResponse> {
        logger.info("Getting all students")

        val savedStudents = studentRepository.findAll()

        return savedStudents.map { student ->
            StudentResponse(
                id = student.id!!,
                name = student.name,
                email = student.email ?: ""
            )
        }
    }
}