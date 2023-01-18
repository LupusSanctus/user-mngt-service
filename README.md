# Device Management Service
DropWizard user management service provides CRUD operations for the users.

## Getting Started
Follow the instructions to run the project on your local machine for testing and/ or development.

### Prerequisites
The service was implemented using the following basic stack:
1. JVM: corretto-17.0.5
2. Gradle: 7.4.2
3. Flyway: 0.7.0-1
4. KotlinJpsPluginSettings: 1.7.21

### Starting the Application
* ``gradle build``
* ``gradle run``

If there were no errors in the previous steps, the user service will listen on 8080 port for the requests.
For example:
``http://localhost:<PORT_NUMBER>/users``

In order to use Swagger UI representation, navigate to the following link:
``http://localhost:<PORT_NUMBER>/swagger``,
where ``PORT_NUMBER`` is ``8080`` by default.

### Endpoints
**1. POST** `/users`
* Request includes: email, first name, last name fields.
* Response returns a `User` model:
    * id
    * email
    * first name
    * last name
    * created at
    * deleted at

**2. GET** `/users/{user-id}`
* Response: `User` model or `404 (NOT_FOUND)` error code.

**3. GET** `/users`
* List of the query parameters:
    * `limit`  maximum number of users to return (min - 1, max - 100, default - 25)
    * `offset` the number of the users to skip before putting in the response (min - 0, default - 0)
    * `sort`   sort the users by the given conditions, in order. Accepts one of the following values: `id`, `email`, `first_name`, `last_name`, `created_at`, `deleted_at`, where `id` is default.
    * `order`  the sorting order of the resulting list. Accepts: `ASC` or `DESC`, where `ASC` is default.
    * `show_deleted` whether or not to return deleted users with this request. `false` by default.
* Response: `User` model or `404 (NOT_FOUND)` error code.

**4. PUT** `/users/{id}`
* Request includes the first and last name fields.
* Response: `User` model or `404 (NOT_FOUND)` error code.

**5. DELETE** `/users/{id}`
* Response: `User` model or `404 (NOT_FOUND)` error code.