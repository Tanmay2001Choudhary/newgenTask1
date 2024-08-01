## Setup Instructions

### Prerequisites
- Java JDK 22 or later
- PostgreSQL database
- Maven (or Gradle, depending on your preference)

### Clone the Repository
```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository

## Setup Instructions
1. Database Configuration
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

2. Build and Run the Application

## Postman API Collection

### Overview
This section provides details on the RESTful API endpoints available in this project for managing students and courses. Each API endpoint is documented with its URL, HTTP method, description, and example requests and responses.

### Students API

1. **Get All Students**
   - **URL:** `GET /student`
   - **Description:** Retrieve a list of all students.
   - **Response:** `200 OK`
     ```json
     [
       {
         "rollno": 1,
         "name": "John Doe"
       },
       {
         "rollno": 2,
         "name": "Jane Doe"
       }
     ]
     ```

2. **Get Student by ID**
   - **URL:** `GET /student/{studentId}`
   - **Description:** Retrieve a specific student by their ID.
   - **URL Parameters:** `studentId` (e.g., 1)
   - **Response:** `200 OK`
     ```json
     {
       "rollno": 1,
       "name": "John Doe"
     }
     ```
   - **Error Response:** `404 Not Found` if the student is not found.

3. **Create Student**
   - **URL:** `POST /student`
   - **Description:** Create a new student.
   - **Request Body:**
     ```json
     {
       "name": "John Doe"
     }
     ```
   - **Response:** `201 Created`
     ```json
     {
       "rollno": 1,
       "name": "John Doe"
     }
     ```

4. **Update Student**
   - **URL:** `PUT /student`
   - **Description:** Update an existing student.
   - **Request Body:**
     ```json
     {
       "rollno": 1,
       "name": "John Smith"
     }
     ```
   - **Response:** `200 OK`
     ```json
     {
       "rollno": 1,
       "name": "John Smith"
     }
     ```
   - **Error Response:** `404 Not Found` if the student is not found.

5. **Delete Student**
   - **URL:** `DELETE /student/{studentId}`
   - **Description:** Delete a student by their ID.
   - **URL Parameters:** `studentId` (e.g., 1)
   - **Response:** `200 OK`

6. **Get Courses for Student**
   - **URL:** `GET /student/{id}/courses`
   - **Description:** Retrieve courses associated with a specific student.
   - **URL Parameters:** `id` (e.g., 1)
   - **Response:** `200 OK`
     ```json
     [
       {
         "courseId": 1,
         "name": "Mathematics",
         "description": "A course on Mathematics."
       },
       {
         "courseId": 2,
         "name": "Physics",
         "description": "A course on Physics."
       }
     ]
     ```

### Courses API

1. **Get All Courses**
   - **URL:** `GET /course`
   - **Description:** Retrieve a list of all courses.
   - **Response:** `200 OK`
     ```json
     [
       {
         "courseId": 1,
         "name": "Mathematics",
         "description": "A course on Mathematics."
       },
       {
         "courseId": 2,
         "name": "Physics",
         "description": "A course on Physics."
       }
     ]
     ```

2. **Get Course by ID**
   - **URL:** `GET /course/{courseId}`
   - **Description:** Retrieve a specific course by its ID.
   - **URL Parameters:** `courseId` (e.g., 1)
   - **Response:** `200 OK`
     ```json
     {
       "courseId": 1,
       "name": "Mathematics",
       "description": "A course on Mathematics."
     }
     ```
   - **Error Response:** `404 Not Found` if the course is not found.

3. **Create Course**
   - **URL:** `POST /course`
   - **Description:** Create a new course.
   - **Request Body:**
     ```json
     {
       "name": "Mathematics",
       "description": "A course on Mathematics."
     }
     ```
   - **Response:** `201 Created`
     ```json
     {
       "courseId": 1,
       "name": "Mathematics",
       "description": "A course on Mathematics."
     }
     ```

4. **Update Course**
   - **URL:** `PUT /course/{courseId}`
   - **Description:** Update an existing course.
   - **Request Body:**
     ```json
     {
       "courseId": 1,
       "name": "Advanced Mathematics",
       "description": "An advanced course on Mathematics."
     }
     ```
   - **Response:** `200 OK`
     ```json
     {
       "courseId": 1,
       "name": "Advanced Mathematics",
       "description": "An advanced course on Mathematics."
     }
     ```
   - **Error Response:** `404 Not Found` if the course is not found.

5. **Delete Course**
   - **URL:** `DELETE /course/{courseId}`
   - **Description:** Delete a course by its ID.
   - **URL Parameters:** `courseId` (e.g., 1)
   - **Response:** `200 OK`

6. **Get Students for Course**
   - **URL:** `GET /course/{id}/students`
   - **Description:** Retrieve students enrolled in a specific course.
   - **URL Parameters:** `id` (e.g., 1)
   - **Response:** `200 OK`
     ```json
     [
       {
         "rollno": 1,
         "name": "John Doe"
       },
       {
         "rollno": 2,
         "name": "Jane Doe"
       }
     ]
     ```
