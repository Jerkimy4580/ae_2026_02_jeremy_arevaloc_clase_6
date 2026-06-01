package com.puceTec.Students.services

import com.puceTec.Students.dto.StudentRequest
import com.puceTec.Students.dto.StudentResponse
import com.puceTec.Students.entities.Student
import com.puceTec.Students.exceptions.EmailAlreadyExistsException
import com.puceTec.Students.mappers.toEntity
import com.puceTec.Students.mappers.toResponse
import com.puceTec.Students.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val repository: StudentRepository
) {
    private val logger = LoggerFactory.getLogger(StudentService::class.java)

    fun createStudent(request: StudentRequest): StudentResponse {
        logger.info("Creando estudiante ${request.name}... verificando email")

        if (repository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException("El email ya existe")
        }

        val studentToSave = request.toEntity()
        val savedStudent = repository.save(studentToSave)
        logger.info("Guardando estudiante con el id: ${savedStudent.id}")

        return savedStudent.toResponse()
    }

    fun getAllStudents(): List<StudentResponse> {
        logger.info("Tomando todos los estudiantes")
        val students = repository.findAll()
        return students.map { miEstudiante: Student ->
            miEstudiante.toResponse()
        }
    }
}
